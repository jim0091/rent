package com.jinhui.controller.vo;


import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jinhui on 2018/4/9.
 */
public class ContractVo {
    //租约ID
    private Long id;
    //        房屋ID
    private String houseId;
    //业主
    private String renter;
    //        出租人ID
    private Long renterId;
    //营运方
    private String agent;
    //        承租人
    private String tenant;
    //承租人ID
    private Long tenantId;
    //        租金
    private BigDecimal amount;
    //租期起
    private String startDate;
    //租期止
    private String endDate;
    //押金
    private BigDecimal deposit;
    //租约状态[Enable, Disable]
    private String status;
    //租赁合同
    private List<AttachmentVo> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AttachmentVo> getFiles() {
        return files;
    }

    public void setFiles(List<AttachmentVo> files) {
        this.files = files;
    }
}
