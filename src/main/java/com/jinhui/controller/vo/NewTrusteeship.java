package com.jinhui.controller.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2018/4/9.
 */
public class NewTrusteeship {
    //房屋ID
    private String houseId;
    //出租人
    @ApiModelProperty(value = "业主")
    private String renter;
    //出租人ID
    @ApiModelProperty(value = "业主ID")
    private Long renterId;
    //营运方
    @ApiModelProperty(value = "运营方")
    private String agent;
    //租金
    @ApiModelProperty(value = "租金")
    private BigDecimal amount;
    //租期起
    @ApiModelProperty(value = "起始租期格式[yyyy-mm-dd]")
    private String startDate;
    //        租期止
    @ApiModelProperty(value = "终止租期格式[yyyy-mm-dd]")
    private String endDate;
    //押金
    @ApiModelProperty(value = "押金")
    private BigDecimal deposit;
    //租约状态
    @ApiModelProperty(value = "租约状态[Enable, Disable]")
    private String status;
    //托管合同ID
    @ApiModelProperty(value = "托管合同附件ids")
    private String files;

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

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "NewTrusteeship{" +
                "houseId='" + houseId + '\'' +
                ", renter='" + renter + '\'' +
                ", renterId=" + renterId +
                ", agent='" + agent + '\'' +
                ", amount=" + amount +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", deposit=" + deposit +
                ", status='" + status + '\'' +
                ", files='" + files + '\'' +
                '}';
    }
}
