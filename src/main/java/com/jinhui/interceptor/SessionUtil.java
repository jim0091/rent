package com.jinhui.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jinhui on 2018/5/9.
 */
public class SessionUtil {

    private static Logger logger = LoggerFactory.getLogger(SessionUtil.class);
    private static final String Cellphone = "cellphone";

    public static String getCellphone(HttpServletRequest req) {
       return (String) req.getSession().getAttribute(Cellphone);
    }
    public static void putCellphone(HttpServletRequest req, String cellphone) {
       if(logger.isInfoEnabled()) {
           logger.info("session保存cellphone={}", cellphone);
       }
       req.getSession().setAttribute(Cellphone, cellphone);
    }
    public static boolean isLogin(HttpServletRequest req) {
        return StringUtils.isEmpty(getCellphone(req));
    }
}
