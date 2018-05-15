package com.jinhui.controller;



import com.jinhui.controller.base.WebResult;
import com.jinhui.wechat.WechatAuthorizeManager;
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
public class WechatController {
	Logger logger = LoggerFactory.getLogger(WechatController.class);

	/*@RequestMapping("/refreshToken")
	@ResponseBody
	public String refreshToken(){
		WebResult result = WebResult.refreshTokenResult("刷新token");
		return result.toJson();
	}*/



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

	public static void main(String[] strs) {
		String imageUri = "abc/1234";
		String imageName = imageUri.substring(imageUri.lastIndexOf("/")+1);
		System.out.println(imageName);
	}

}
