package com.jinhui.controller;


import com.jinhui.controller.assembler.ObjectAssembler;
import com.jinhui.controller.base.WebConstants;
import com.jinhui.controller.base.WebResult;
import com.jinhui.controller.result.*;
import com.jinhui.controller.vo.*;
import com.jinhui.domain.Attachment;
import com.jinhui.domain.BatchImportHouse;
import com.jinhui.domain.House;
import com.jinhui.domain.User;
import com.jinhui.interceptor.SessionUtil;
import com.jinhui.service.RentService;
import com.jinhui.service.batch.DefaultBatchService;
import com.jinhui.service.batch.HouseExcelRowReader;
import com.jinhui.service.batch.ItemWriter;
import com.jinhui.wechat.PageSignature;
import com.jinhui.wechat.SignatureParameter;
import com.jinhui.wechat.WechatAuthorizeManager;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by jinhui on 2018/1/18.
 */
@RestController
@RequestMapping(value = "/rent")
@Api(value = "rent", description = "TCP相关接口")
public class RentController {

    public final static String AttachmentUrl = "rent/attachment/";

    private static Logger logger = LoggerFactory.getLogger(RentController.class);

    @Value("${attachment.dir}")
    private String AttachmentsDir;

    @Value("${upload.file.dir}")
    private String uploadBatchDir;

    @Autowired
    private RentService rentService;

    @Qualifier("HouseDBWriter")
    @Autowired
    private ItemWriter<BatchImportHouse> itemWriter;

    @ApiOperation(value = "获取房子ID", response = HouseIdResult.class, httpMethod = "GET")
    @ResponseBody
    @RequestMapping(value = "/fetchHouseId", method = GET)
    public HouseIdResult fetchHouseId() {
        if(logger.isInfoEnabled()) {
            logger.info("获取房子ID");
        }
        HouseIdResult result = new HouseIdResult();
        try {
            String houseId = rentService.fetchHouseId();
            result.setHouseId(houseId);
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
        } catch (Exception ex) {
            logger.warn("获取房子ID: {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/signIn")
    @ApiOperation(value = "用户登入", response = UserVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "cellphone", dataType = "String", required = true, value = "手机号"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "密码")
    })
    @ResponseBody
    public WebTemplateResult<UserVo> signIn(String cellphone, String password, HttpServletRequest request) {
        if(logger.isInfoEnabled()) {
            logger.info("用户登入cellphone={}", cellphone);
        }
        WebTemplateResult<UserVo> result = new WebTemplateResult<>();
        try {
            User user = rentService.findUser(cellphone);
            if(user != null) {
                if(!user.getPassword().equals(ObjectAssembler.encodePassword(password))) {
                    throw new IllegalArgumentException("密码错误");
                }
                //update session
                SessionUtil.putCellphone(request, cellphone);
                if (WechatAuthorizeManager.fromWechatRequest(request)) {
                    //微信首次登录
                    String openid = WechatAuthorizeManager.getOpenId(request);
                    User wechatUser = rentService.findUserByOpenid(openid);
                    if(wechatUser == null) {
                        user.setOpenid(openid);
                        rentService.modifyUser(user);
                    }
                }

                UserVo userVo = ObjectAssembler.toUserVo(user, rentService);
                loginReward(user, userVo);
                result.setCode(WebConstants.RESULT_SUCCESS_CODE);
                result.setResult(userVo);
            } else {
                throw new IllegalArgumentException("未注册用户");
            }
        } catch (Exception ex) {
            logger.warn("用户登入错误 : {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("用户登入错误:" + ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/findUserInfo")
    @ApiOperation(value = "查询登录用户信息", response = UserVo.class, httpMethod = "GET")
    @ResponseBody
    public WebTemplateResult<UserVo> findUserInfo(HttpServletRequest request) {
        WebTemplateResult<UserVo> result = new WebTemplateResult<>();
        try {
            String cellphone = SessionUtil.getCellphone(request);
            if(logger.isInfoEnabled()) {
                logger.info("登入用户cellphone={}", cellphone);
            }
            if(StringUtils.isEmpty(cellphone)) {
                result.setCode(WebConstants.REFRESH_SESSION_CODE);
                result.setMessage("重新登录");
                return result;
            }
            User user = rentService.findUser(cellphone);
            UserVo userVo = ObjectAssembler.toUserVo(user, rentService);
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setResult(userVo);
        } catch (Exception ex) {
            logger.warn("用户登入错误 : {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("用户登入错误:" + ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/refreshSession")
    @ApiOperation(value = "刷新登录状态", response = UserVo.class, httpMethod = "GET")
    @ResponseBody
    public WebResult refreshSession() {
        if(logger.isInfoEnabled()) {
            logger.info("刷新登录状态");
        }
        return WebResult.refreshSessionResult("重新登录");
    }

    @RequestMapping("/wechatSignIn")
    @ApiOperation(value = "微信绑定登入", response = UserVo.class, httpMethod = "GET")
    @ResponseBody
    public WebTemplateResult<UserVo> wechatSignIn(HttpServletRequest request) {
        if(logger.isInfoEnabled()) {
            logger.info("微信登入");
        }
        WebTemplateResult<UserVo> result = new WebTemplateResult<>();
        try {
            if (WechatAuthorizeManager.fromWechatRequest(request)) {
                //微信登录
                //return WechatAuthorizeManager.wechatDispatcher(request, response);
                String openid = WechatAuthorizeManager.getOpenId(request);
                if(!StringUtils.isEmpty(openid)) {
                    User user = rentService.findUserByOpenid(openid);
                    if (user != null) {
                        //update session
                        SessionUtil.putCellphone(request, user.getCellphone());
                        UserVo userVo = ObjectAssembler.toUserVo(user, rentService);
                        loginReward(user, userVo);
                        result.setCode(WebConstants.RESULT_SUCCESS_CODE);
                        result.setResult(userVo);
                    } else {
                        result.setCode(WebConstants.RESULT_UNBOUND_CODE);
                        result.setMessage("未绑定用户");
                    }
                } else {
                    result.setCode(WebConstants.RESULT_NULL_OPENID_CODE);
                    result.setMessage("openid为空");
                }
            } else {
                result.setCode(WebConstants.RESULT_FAIL_CODE);
                result.setMessage("不是微信请求");
            }
        } catch (Exception ex) {
            logger.warn("用户登入错误 : {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("用户登入错误:" + ex.getMessage());
        }
        return result;
    }

    private void loginReward(User user, UserVo userVo) {
        List<PointVo> pointVos = ObjectAssembler.loginReward(user);
        double sum = 0.0;
        for(PointVo vo : pointVos) {
            sum += vo.getPoint();
        }
        user.addPoint(sum);
        user.setLastLoginTime(new Date());
        rentService.modifyUser(user);
        userVo.setPoints(user.getPoints());
        userVo.setPointVos(pointVos);
    }

    @RequestMapping("/findHouseModifiedList")
    @ApiOperation(value = "查询房子信息", response = HouseVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "houseId", dataType = "String", required = true, value = "房子ID")
    })
    @ResponseBody
    public WebTemplateResult<List<HouseVo>> findHouseModifiedList(String houseId) {
        if(logger.isInfoEnabled()) {
            logger.info("查询房子信息houseId={}", houseId);
        }
        WebTemplateResult<List<HouseVo>> result = new WebTemplateResult<>();
        try {
            List<House> houses = rentService.findHouseModifiedList(houseId);
            List<HouseVo> houseVos = new ArrayList<>(houses.size());
            for(House house:houses) {
                HouseVo houseVo = ObjectAssembler.toHouseVo(house, rentService);
                houseVos.add(houseVo);
            }
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setResult(houseVos);
        } catch (Exception ex) {
            logger.warn("获取用户信息错误 : {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("获取用户信息错误:" + ex.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/takeAction", method = POST)
    public WebTemplateResult takeAction(@ApiParam(value = "记录房产信息", required = true)
                                  @RequestBody NewHouseVo houseVo) {
        if(logger.isInfoEnabled()) {
            logger.info("记录房产信息: {}", houseVo);
        }
        WebTemplateResult<Double> result = new WebTemplateResult<>();
        try {
            House house = ObjectAssembler.toHouse(houseVo);
            Double points = rentService.takeAction(house);
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setResult(points);
        } catch (Exception ex) {
            logger.warn("记录房产信息 : {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("记录房产信息错误:" + ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "上传附件", response = UploadAttachmentResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "multipartFile", dataType = "file", required = true, value = "文件对象"),
            @ApiImplicitParam(paramType = "form", name = "userId", dataType = "long", required = true, value = "企业ID"),
            @ApiImplicitParam(paramType = "form", name = "type", dataType = "string", required = true,
                    value = "文件类型[House(\"房屋证明\"), Contract(\"合同证明\"), Trusteeship(\"托管证明\"), Image(\"房源图片\"), IdentityFront(\"身份证正面\"), IdentityBack(\"身份证反面\"), Others(\"其它\")]")
    })
    @ResponseBody
    @RequestMapping(value = "/uploadAttachment", method = POST)
    public UploadAttachmentResult uploadAttachment(MultipartFile multipartFile, Long userId, String type) {
        if(logger.isInfoEnabled()) {
            logger.info("上传附件========");
        }
        UploadAttachmentResult result = new UploadAttachmentResult();
        try {
            String srcFilename = multipartFile.getOriginalFilename();
            String name = Attachment.toStoreFilename(srcFilename);
            Attachment attachment = ObjectAssembler.toAttachment(userId, name, type);
            String location = attachment.fileLocation(AttachmentsDir);
            attachment.setLocation(location);
            File destFile = new File(location);
            //保存文件
            multipartFile.transferTo(destFile);
            rentService.uploadAttachment(attachment);
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
            result.setUrl(AttachmentUrl+attachment.toUrl());
            result.setAttachmentId(attachment.getId());
            result.setName(srcFilename.substring(0, srcFilename.lastIndexOf(".")));
        } catch (Exception e) {
            logger.warn("上传失败 : {}", e);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }

    @ApiOperation(value = "批量导入数据", response = UploadAttachmentResult.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "multipartFile", dataType = "file", required = true, value = "文件对象"),
            @ApiImplicitParam(paramType = "form", name = "userId", dataType = "long", required = true, value = "企业ID")
    })
    @ResponseBody
    @RequestMapping(value = "/batchImport", method = POST)
    public WebResult batchImport(MultipartFile multipartFile, Long userId) {
        if(logger.isInfoEnabled()) {
            logger.info("上传批量数据文件========");
        }
        try {
            String srcFilename = multipartFile.getOriginalFilename();
            String name = Attachment.toStoreFilename(srcFilename);
            Attachment attachment = ObjectAssembler.toAttachment(userId, name,
                    Attachment.Type.BatchImport.toString());
            String location = attachment.fileLocation(uploadBatchDir);
            attachment.setLocation(location);
            File destFile = new File(location);
            //保存文件
            multipartFile.transferTo(destFile);
            rentService.uploadAttachment(attachment);
            DefaultBatchService.excelExecute(destFile, new HouseExcelRowReader(), itemWriter, 10);
        } catch (Exception e) {
            logger.warn("上传失败 : {}", e);
            return WebResult.failureResult(e.getMessage());
        }
        return WebResult.successResult();
    }

    @RequestMapping(value = "/attachment/{type}/{filename}", method = GET)
    @ApiOperation(value = "查看附件", httpMethod = "GET")
    public void attachment(@PathVariable String type, @PathVariable String filename,
                           HttpServletRequest request, HttpServletResponse response) {
        InputStream in = null;
        OutputStream ot = null;
        try {
            //检查图片是否存在
            String noSuffixName = AttachmentsDir + type + File.separator + filename;
            File file = new File(noSuffixName);
            if(!file.exists()) {
                throw new RuntimeException(filename+" : 文件不存在");
            }
            response.setCharacterEncoding("utf-8");
            String agent = request.getHeader("User-Agent");
            boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
            /*if (isMSIE) {
                response.addHeader("Content-Disposition", "attachment;filename=\""+ URLEncoder
                        .encode(noSuffixName+".pdf", "UTF8") + "\"");
            } else {
                response.addHeader("Content-Disposition", "attachment;filename=\""+URLEncoder
                        .encode(noSuffixName+".pdf", "ISO-8859-1") + "\"");
            }*/
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    +filename + "\"");
            response.setContentType("application/octet-stream;charset=utf-8");
            in = new FileInputStream(file);
            ot = response.getOutputStream();
            IOUtils.copy(in,ot);
        } catch (IOException e) {
            logger.warn("获取文件异常:{}", e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(ot);
        }
    }

    @ResponseBody
    @ApiOperation(value = "新增用户", response = UserVo.class, httpMethod = "POST")
    @RequestMapping(value = "/addUser", method = POST)
    public WebTemplateResult<UserVo> addUser(@ApiParam(value = "添加用户", required = true)
                                @RequestBody NewUserVo newUserVo, HttpServletRequest request) {
        if(logger.isInfoEnabled()) {
            logger.info("添加用户 : {}", newUserVo);
        }
        WebTemplateResult<UserVo> result = new WebTemplateResult<>();
        try {
            if(StringUtils.isEmpty(newUserVo.getCellphone())){
                throw new IllegalArgumentException("手机号不能为空");
            }
            User user = ObjectAssembler.toUser(newUserVo);
            rentService.addUser(user);
            SessionUtil.putCellphone(request, user.getCellphone());
            UserVo userVo = ObjectAssembler.toUserVo(user, rentService);
            result.setResult(userVo);
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
        } catch (Exception ex) {
            logger.warn("添加用户错误 : {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyUser", method = POST)
    public WebResult modifyUser(@ApiParam(value = "修改用户", required = true)
                             @RequestBody NewUserVo userVo) {
        if(logger.isInfoEnabled()) {
            logger.info("修改用户 : {}", userVo);
        }
        try {
            User user = ObjectAssembler.toUser(userVo);
            rentService.modifyUser(user);
        } catch (Exception ex) {
            logger.warn("修改用户 : {}", ex);
            return WebResult.failureResult(ex.getMessage());
        }
        return WebResult.successResult();
    }

    /*@RequestMapping("/shareReward")
    @ApiOperation(value = "shareReward", response = WechatResult.class, httpMethod = "GET")
    public WebResult shareReward() {
        if(logger.isInfoEnabled()) {
            logger.info("分享奖励积分");
        }
        try {
            User user = ObjectAssembler.toUser(userVo);
            rentService.modifyUser(user);
        } catch (Exception ex) {
            logger.warn("修改用户 : {}", ex);
            return WebResult.failureResult(ex.getMessage());
        }
        return WebResult.successResult();
    }*/

    @RequestMapping("/openid")
    @ApiOperation(value = "openid", response = WechatResult.class, httpMethod = "GET")
    public WechatResult openid(HttpServletRequest req) {
        if(logger.isInfoEnabled()) {
            logger.info("查询openid");
        }
        WechatResult result = new WechatResult();
        try {
            String uid = WechatAuthorizeManager.getOpenId(req);
            result.setOpenid(uid);
            if(StringUtils.isEmpty(uid)) {
                result.setMessage("微信授权失败");
                result.setCode(WebConstants.RESULT_FAIL_CODE);
            } else {
                result.setCode(WebConstants.RESULT_SUCCESS_CODE);
            }
        } catch (Exception ex) {
            logger.warn("回应异常: {}", ex);
            result.setCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage(ex.getMessage());
        }
        return result;
    }
    @ApiOperation(value = "页面签名", response = SignatureResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/signature", method = POST)
    public SignatureResult signature(@ApiParam(value = "页面签名", required = true)
                                     @RequestBody NewSignature signature) {
        if(logger.isInfoEnabled()) {
            logger.info("signature : {}", signature);
        }
        SignatureResult result = new SignatureResult();
        try {
            result.setCode(WebConstants.RESULT_SUCCESS_CODE);
            String timestamp = System.currentTimeMillis()/1000+"";
            String noncestr = UUID.randomUUID().toString();
            SignatureParameter signatureParameter = new SignatureParameter();
            signatureParameter.setLink(signature.getUrl());
            signatureParameter.setTimestamp(timestamp);
            signatureParameter.setNonceStr(noncestr);
            PageSignature.fetchSignature(signatureParameter);
            //set result
            SignatureVo vo = new SignatureVo();
            vo.setNonceStr(signatureParameter.nonceStr().value());
            vo.setTimestamp(signatureParameter.timestamp().value());
            vo.setSignature(signatureParameter.signature().value());
            vo.setTicket(signatureParameter.ticket().value());
            result.setSignatureVo(vo);
            if(logger.isInfoEnabled()) {
                logger.info("signature result : {}", result);
            }
        } catch (Exception ex) {
            logger.warn("回应异常: {}", ex);
            result.setMessage(ex.getMessage());
            result.setCode(WebConstants.RESULT_FAIL_CODE);
        }
        return result;
    }

}
