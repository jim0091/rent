package com.jinhui.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jinhui.wechat.WechatAuthorizeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        if(logger.isInfoEnabled()) {
            logger.info("请求url ========> {}", url);
        }
        if (WechatAuthorizeManager.isWechatRequest(request)) {
            return WechatAuthorizeManager.wechatDispatcher(request, response);
        }
        /*if (!SessionUtil.isLogin(request)) {
            request.getRequestDispatcher("/rent/refreshSession").forward(request, response);
            return false;
        }*/
        return true;
    }


}
