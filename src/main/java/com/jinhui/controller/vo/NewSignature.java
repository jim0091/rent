package com.jinhui.controller.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/1/28.
 */
public class NewSignature {
    @ApiModelProperty(value="签名url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewSignature{" +
                "url='" + url + '\'' +
                '}';
    }
}
