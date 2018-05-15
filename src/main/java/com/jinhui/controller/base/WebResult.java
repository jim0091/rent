package com.jinhui.controller.base;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by jinhui on 2017/9/18.
 */
public abstract class WebResult implements Serializable{

    @ApiModelProperty(value="状态码=[0:成功, 1:错误]")
    private long code = 0;
    private String message;

    public long getCode() {
        return code;
    }

    public static WebResult failureResult(String msg){
        WebResult result = new WebResult(){};
        result.setCode(WebConstants.RESULT_FAIL_CODE);
        result.setMessage(msg);
        return result;
    }

    public static WebResult refreshSessionResult(String msg){
        WebResult result = new WebResult(){};
        result.setCode(WebConstants.REFRESH_SESSION_CODE);
        result.setMessage(msg);
        return result;
    }

    public static WebResult successResult(){
        WebResult result = new WebResult(){};
        result.setCode(WebConstants.RESULT_SUCCESS_CODE);
        return result;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
