package com.jinhui.controller.vo;


import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;

/**
 * Created by jinhui on 2018/4/9.
 */
public class NewModifiedRecord {
    private Long modifiedId;
    //房子ID
    @ApiModelProperty(value = "房子ID", required = true)
    private String houseId;
    //用户ID
    @ApiModelProperty(value = "操作用户", required = true)
    private Long userId;
    //角色
    @ApiModelProperty(value = "用户角色[Renter(\"房东\"), Agent(\"中介\"), Tenant(\"租客\"), Other(\"其他\")]", required = true)
    private String userRole;
    //附言
    private String comment;
    //动作
    @ApiModelProperty(value = "Created(\"创建\"), Modified(\"修改\"), Approved(\"赞同\")", required = true)
    private String operationType;
    @ApiModelProperty(value = "UploadTrusteeship(\"托管合同\"), UploadContract(\"租赁合同\"), UploadHouseCredential(\"房产证\")", required = true)
    private List<String> operationFields;

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

    public List<String> getOperationFields() {
        return operationFields == null ?
                Collections.EMPTY_LIST : operationFields;
    }

    public void setOperationFields(List<String> operationFields) {
        this.operationFields = operationFields;
    }

    @Override
    public String toString() {
        return "NewModifiedRecord{" +
                "modifiedId=" + modifiedId +
                ", houseId='" + houseId + '\'' +
                ", userId=" + userId +
                ", userRole='" + userRole + '\'' +
                ", comment='" + comment + '\'' +
                ", operationType='" + operationType + '\'' +
                ", operationFields=" + operationFields +
                '}';
    }
}
