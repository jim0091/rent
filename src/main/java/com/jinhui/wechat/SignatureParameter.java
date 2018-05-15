package com.jinhui.wechat;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 使用pojo而不是map来封装配置型参数，提高业务代码可读性;
 * 使用Builder模式来适应多样的参数配置.
 */
public class SignatureParameter {

    private Parameter<String> timestamp;//: $timestamp,
    private Parameter<String> nonceStr;//: '$nonceStr',
    private Parameter<String> signature;//: '$signature',
    private Parameter<String> link;//: '$link'
    private Parameter<String> ticket;//'$jsapi_ticket'

    /*public SharedInfoParameter(String timestamp, String nonceStr, String signature,
                               String title, String link) {
        this.timestamp = new ParameterImpl("$timestamp",timestamp);
        this.nonceStr = new ParameterImpl("$nonceStr", nonceStr);
        this.signature = new ParameterImpl("$signature", signature);
        this.title = new ParameterImpl("$title", title);
        this.link = new ParameterImpl("$link", link);
    }*/

    public SignatureParameter() {
    }

    public Parameter<String> timestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = new Parameter.ParameterImpl("$timestamp",timestamp);
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = new Parameter.ParameterImpl("$nonceStr", nonceStr);
    }

    public void setSignature(String signature) {
        this.signature = new Parameter.ParameterImpl("$signature", signature);
    }

    public void setLink(String link) {
        this.link = new Parameter.ParameterImpl("$link", link);
    }

    public void setTicket(String ticket) {
        this.ticket = new Parameter.ParameterImpl("$jsapi_ticket", ticket);
    }

    public Parameter<String> nonceStr() {
        return nonceStr;
    }

    public Parameter<String> signature() {
        return signature;
    }

    public Parameter<String> link() {
        return link;
    }

    public Parameter<String> ticket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "SharedInfoParameter{" +
                "timestamp=" + timestamp +
                ", nonceStr=" + nonceStr +
                ", signature=" + signature +
                ", link=" + link +
                ", ticket=" + ticket +
                '}';
    }
}
