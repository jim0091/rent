package com.jinhui.wechat;

import com.alibaba.fastjson.JSONObject;
import com.jinhui.domain.User;
import com.jinhui.service.RentService;
import com.jinhui.util.SpringContextHolder;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.io.RuntimeIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jinhui on 2017/9/29.
 */
public class OAuth {

    private static Logger logger = LoggerFactory.getLogger(OAuth.class);

    private static final String OpenIdKey = "openid";
    private static final String AccessTokenKey = "access_token";
    private static final String Nickname = "nickname";
    private static final String Headimgurl = "headimgurl";

    private static final String OpenIdUri = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid="+WechatAuthorizeManager.appid+"&" +
            "secret=" +WechatAuthorizeManager.secret+ "&" +
            "code=$arg_code&" +
            "grant_type=authorization_code#wechat_redirect";
    private static final String UserInfoUri = "https://api.weixin.qq.com/sns/userinfo?" +
            "access_token=$access_token&openid=$openid";
    //获取code uri
    private static final String RedirectUri = "http%3a%2f%2fhouse.jinfeibiao.com" +
            "%2frent%2fwechat%2fauthentication%2fcode";
    private static final String CodeUri = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=" +WechatAuthorizeManager.appid+ "&" +
            "redirect_uri=" + RedirectUri + "&" +
            "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    private OAuth() {
    }

    /**
     * 检查当前回话是否获取到了openid；
     * 如果没有openid，则通过OAuth2从微信服务器获取;
     * 如果已有openid，则使用{@param Homepage}响应302;
     * 只有用户第一次通过微信进入以及session过期时，
     * 会通过微信浏览器获取openid，随后的访问的session都是有openid的。
     * 总之，前端通过微信访问的页面，只有两种请求会进入后台：
     * 1，用户刚进入网站；2，已获取openid时，前端页面通过微信浏览器刷新。
     * @param request
     * @param response
     * @return
     */
    public static boolean existsOpenid(HttpServletRequest request, HttpServletResponse response){
        String openid = getOpenIdFromSession(request);
        if(logger.isInfoEnabled()) {
            logger.info("openid ==> {}", openid);
        }
        if(StringUtils.isEmpty(openid)) {
            redirectCodeUri(response);
            return false;
        }
        return true;
    }

    public static String getOpenIdFromSession(HttpServletRequest request){
        String openid = (String) request.getSession().getAttribute(OpenIdKey);
        if(logger.isInfoEnabled()) {
            logger.info("从session获取openid = {}", openid);
        }
        return openid;
    }

    /**
     * 重定向回微信客户端，向微信服务器请求授权code;
     * 微信客户端会拿到微信服务器重定向回来的{@param OAuth$RedirectUri},其中携带了code值。
     * @param response
     */
    private static void redirectCodeUri(HttpServletResponse response){
        try {
            if(logger.isInfoEnabled())
                logger.info("重定向微信授权code: {}", CodeUri);
            response.sendRedirect(CodeUri);
        } catch (IOException e) {
            logger.warn("重定向微信授权错误:{}", e);
        }
    }

    /**
     * 利用{@param OAuth$RedirectUri}携带的code值，
     * 向微信服务器请求openid，并存入session
     * @param request
     * @param code
     */
    public static void requestWechat(final HttpServletRequest request, final String code){
        CompositionHttpClient.httpsRequest(new CompositionHttpClient.Action() {
            @Override
            public void doAction(HttpClient httpClient)
                    throws InterruptedException, ExecutionException, TimeoutException {
                /**
                 * 请求登录token
                 */
                ContentResponse wechatResp = httpClient.GET(OpenIdUri.replace("$arg_code",code));
                if(logger.isInfoEnabled())
                    logger.info("微信授权登录返回信息: {}", wechatResp.getContentAsString());
                JSONObject jsonObject = JSONObject.parseObject(wechatResp.getContentAsString());
                String openid = jsonObject.getString(OpenIdKey);
                String accessToken = jsonObject.getString(AccessTokenKey);
                if(StringUtils.isEmpty(openid)) {
                    throw new IllegalArgumentException("获取微信授权token错误");
                }
                RentService rentService = SpringContextHolder.getBean("RentService");
                /*if(!rentService.hasUser(openid)) {
                    // FIXME: 2018/4/3 
                    //获取userinfo
                    wechatResp = httpClient.GET(UserInfoUri
                            .replace("$access_token", accessToken)
                            .replace("$openid", openid));
                    if (logger.isInfoEnabled())
                        logger.info("微信用户信息返回: {}", wechatResp.getContentAsString());
                    jsonObject = JSONObject.parseObject(wechatResp.getContentAsString());
                    String nickname = jsonObject.getString(Nickname);
                    String headImageUrl = jsonObject.getString(Headimgurl);
                    String imageFile = StringUtils.isEmpty(headImageUrl) ? null
                            : rentService.iconPath() + openid;
                    User user = null;//new User(openid, nickname, openid);
                    //confessionService.saveUser(user);
                    try {
                        ImageIO.write(ImageIO.read(new URL(headImageUrl)), "jpg", new File(imageFile));
                    } catch (IOException e) {
                        logger.warn("微信获取头像异常:{}", e);
                        throw new RuntimeIOException(e);
                    }
                }*/
                request.getSession().setAttribute(OpenIdKey, openid);
                request.getSession().setAttribute(AccessTokenKey, accessToken);
            }
        });
    }
}
