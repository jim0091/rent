package com.jinhui.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by jinhui on 2018/1/17.
 */
public class User {

    private Long id;
    private String openid;
    private String name;
    private String password;
    private String identity;
    private String identityImage;
    private String cellphone;
    private String TCPAccountId;
    private String TCCAccountId;
    private BigDecimal TCPBalance;
    private BigDecimal TCCBalance;
    private Long points;
    private Date lastLoginTime;
    private Role role;
    private List<House> lastModifiedHouses;

    public enum Role implements OperationReward {
        Renter("房东"), Agent("中介"), Tenant("租客"), Other("其他");
        private String text;
        Role(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static Role codeOf(String code){
            for(Role role : Role.values()){
                if(role.toString().toLowerCase().equals(code.toLowerCase())){
                    return role;
                }
            }
            throw new IllegalArgumentException("未知角色");
        }
        public Long reward(ModifiedRecord.OperationField of) {
            switch (this) {
                case Renter:
                    switch (of) {
                        case UploadContract:
                        case UploadTrusteeship:
                            return 200L;
                        case UploadHouseCredential:
                            return 500L;
                        case TakeAction:
                            return 200L;
                    }
                case Tenant:
                    switch (of) {
                        case UploadContract:
                        case UploadTrusteeship:
                            return 200L;
                        case UploadHouseCredential:
                            return 0L;
                        case TakeAction:
                            return 200L;
                    }
                case Agent:
                    switch (of) {
                        case UploadContract:
                            return 200L;
                        case UploadTrusteeship:
                        case UploadHouseCredential:
                            return 0L;
                        case TakeAction:
                            return 200L;
                    }
                case Other:
                    switch (of) {
                        case UploadContract:
                        case UploadTrusteeship:
                        case UploadHouseCredential:
                            return 0L;
                        case TakeAction:
                            return 100L;
                    }
            }
            return 0L;
        }
    }

    public static void main(String[] strs) {
        System.out.println(Role.Renter.reward(ModifiedRecord.OperationField.UploadHouseCredential));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPoint(Long point) {
        this.points += point;
    }

    public Long getPoints() {
        return points;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<House> getLastModifiedHouses() {
        if(lastModifiedHouses == null)
            return Collections.EMPTY_LIST;
        return lastModifiedHouses;
    }

    public void setLastModifiedHouses(List<House> lastModifiedHouses) {
        this.lastModifiedHouses = lastModifiedHouses;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
