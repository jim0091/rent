package com.jinhui.controller.vo;


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
    private String TCPAccountId;
    private String TCCAccountId;
    private BigDecimal TCPBalance;
    private BigDecimal TCCBalance;
    private Long points;
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

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public List<PointVo> getPointVos() {
        return pointVos;
    }

    public void setPointVos(List<PointVo> pointVos) {
        this.pointVos = pointVos;
    }
}
