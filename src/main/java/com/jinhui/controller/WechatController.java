package com.jinhui.controller;



import com.jinhui.controller.base.WebResult;
import com.jinhui.wechat.WechatAuthorizeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/wechat")
@Api(value = "rent", description = "微信授权相关接口")
public class WechatController {
	private Logger logger = LoggerFactory.getLogger(WechatController.class);

	@RequestMapping(WechatAuthorizeManager.WechatAuthCodeUrl)
	@ApiOperation(value = "微信授权code回调", response = String.class, httpMethod = "GET")
	@ResponseBody
	public String wechatCode(String code, HttpServletRequest request, HttpServletResponse response){
		try {
			WechatAuthorizeManager.requestWechat(request,code);
			WechatAuthorizeManager.redirectRequest(request, response);
			return null;
		} catch (Exception e) {
			logger.warn("获取微信授权token错误:{}", e);
			WebResult result = WebResult.failureResult(e.getMessage());
			return result.toJson();
		}
	}

}
