package com.jinhui.controller.result;

import com.jinhui.controller.base.WebResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/1/20.
 */
public class WechatResult extends WebResult {

    @ApiModelProperty(value="openid")
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
