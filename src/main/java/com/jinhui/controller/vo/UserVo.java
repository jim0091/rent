package com.jinhui.controller.vo;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jinhui on 2017/9/14.
 */
public class UserVo {

    private Long id;
    private String openid;
    private String name;
    private String identity;
    private List<AttachmentVo> identityImage;
    private String cellphone;
    @ApiModelProperty(value="TCP帐户ID")
    private String TCPAccountId;
    @ApiModelProperty(value="TCC帐户ID")
    private String TCCAccountId;
    @ApiModelProperty(value="TCP余额")
    private BigDecimal TCPBalance;
    @ApiModelProperty(value="TCC余额")
    private BigDecimal TCCBalance;
    @ApiModelProperty(value="积分数")
    private Double points;
    private List<HouseVo> houseVos;
    private List<PointVo> pointVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<AttachmentVo> getIdentityImage() {
        return identityImage;
    }

    public void setIdentityImage(List<AttachmentVo> identityImage) {
        this.identityImage = identityImage;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTCPAccountId() {
        return TCPAccountId;
    }

    public void setTCPAccountId(String TCPAccountId) {
        this.TCPAccountId = TCPAccountId;
    }

    public String getTCCAccountId() {
        return TCCAccountId;
    }

    public void setTCCAccountId(String TCCAccountId) {
        this.TCCAccountId = TCCAccountId;
    }

    public BigDecimal getTCPBalance() {
        return TCPBalance;
    }

    public void setTCPBalance(BigDecimal TCPBalance) {
        this.TCPBalance = TCPBalance;
    }

    public BigDecimal getTCCBalance() {
        return TCCBalance;
    }

    public void setTCCBalance(BigDecimal TCCBalance) {
        this.TCCBalance = TCCBalance;
    }

    public List<HouseVo> getHouseVos() {
        return houseVos;
    }

    public void setHouseVos(List<HouseVo> houseVos) {
        this.houseVos = houseVos;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public List<PointVo> getPointVos() {
        return pointVos;
    }

    public void setPointVos(List<PointVo> pointVos) {
        this.pointVos = pointVos;
    }
}
