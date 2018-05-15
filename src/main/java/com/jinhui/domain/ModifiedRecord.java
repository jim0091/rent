package com.jinhui.domain;

import java.util.List;

/**
 * Created by jinhui on 2018/4/3.
 */
public class ModifiedRecord {
    private Long modifiedId;
    //房子ID
    private String houseId;
    //用户ID
    private Long userId;
    private String userName;
    //角色
    private String userRole;
    //附言
    private String comment;
    //动作
    private String operationType;
    private String gmtCreated;
    private List<OperationField> operationFields;

    public enum OperationType {
        Created("创建"), Modified("修改"), Approved("赞同");
        private String text;
        OperationType(String text) {
            this.text = text;
        }
    }

    public enum OperationField {
        UploadTrusteeship("托管合同"), UploadContract("租赁合同"), UploadHouseCredential("房产证"), TakeAction("提交");
        private String text;
        OperationField(String text) {
            this.text = text;
        }
        public String getText() {return this.text;}
        public static OperationField codeOf(String ofStr){
            for(OperationField of : OperationField.values()){
                if(of.toString().toLowerCase().equals(ofStr.toLowerCase())){
                    return of;
                }
            }
            throw new IllegalArgumentException("未知操作字段");
        }
    }

    public List<OperationField> getOperationFields() {
        return operationFields;
    }

    public void setOperationFields(List<OperationField> operationFields) {
        this.operationFields = operationFields;
    }

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
