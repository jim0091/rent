package com.jinhui.controller.vo;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/4/9.
 */
public class ModifiedRecordVo {
    private Long modifiedId;
    //房子ID
    private String houseId;
    //用户ID
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    //角色
    @ApiModelProperty(value = "用户角色[Renter(\"房东\"), Agent(\"中介\"), Tenant(\"租客\"), Other(\"其他\")]")
    private String userRole;
    //附言
    @ApiModelProperty(value = "附言")
    private String comment;
    //动作
    @ApiModelProperty(value = "操作类型")
    private String operationType;
    @ApiModelProperty(value = "记录创建时间")
    private String gmtCreated;

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
