package com.jinhui.controller.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/5/14.
 */
public class PointVo {
    private Integer index;
    @ApiModelProperty(value = "积分数")
    private Double point;
    @ApiModelProperty(value = "随机值")
    private Double nonce;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Double getNonce() {
        return nonce;
    }

    public void setNonce(Double nonce) {
        this.nonce = nonce;
    }
}
