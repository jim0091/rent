package com.jinhui.controller.vo;

/**
 * Created by jinhui on 2018/1/28.
 */
public class SignatureVo {

    private  String timestamp;
    private String nonceStr;
    private String signature;
    private String ticket;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "SignatureVo{" +
                "timestamp='" + timestamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", signature='" + signature + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
