package com.jinhui.controller.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/4/9.
 */
public class NewUserVo {

    private Long id;
    private String name;
    private String identity;
    private String identityImage;
    @ApiModelProperty(value = "手机号", required = true)
    private String cellphone;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIdentityImage() {
        return identityImage;
    }

    public void setIdentityImage(String identityImage) {
        this.identityImage = identityImage;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUserVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", identityImage='" + identityImage + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
