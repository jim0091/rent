package com.jinhui.controller.vo;


import com.jinhui.domain.Attachment;

import java.util.List;

/**
 * Created by jinhui on 2018/4/9.
 */
public class HouseVo {
    private Long modifiedId;
    private String id;
    private String communityId;
    //小区名称
    private String community;
    //地址-省
    private String province;
    //地址-市
    private String city;
    //地址-区
    private String district;
    //地址-路
    private String road;
    //地址-号
    private String doorNo;
    //具体地址
    private String detailAddress;
    private Long userId;
    private String username;
    //房产证（图）,文件ID字符串
    private List<AttachmentVo> houseCredential;
    //建筑面积
    private Double area;
    //实用面积
    private Double realArea;
    //使用率
    private Double usageRatio;
    //朝向
    private String direction;
    //楼
    private String floor;
    // 室-厅-厕
    private String construction;
    //是否托管
    private Integer isTrusteeship;
    //房源图片, 文件id字符串
    //private List<String> images;
    private List<AttachmentVo> images;
    private ContractVo contractVo;
    private TrusteeshipVo trusteeshipVo;
    private ModifiedRecordVo modifiedRecordVo;

    public Long getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AttachmentVo> getHouseCredential() {
        return houseCredential;
    }

    public void setHouseCredential(List<AttachmentVo> houseCredential) {
        this.houseCredential = houseCredential;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getRealArea() {
        return realArea;
    }

    public void setRealArea(Double realArea) {
        this.realArea = realArea;
    }

    public Double getUsageRatio() {
        return usageRatio;
    }

    public void setUsageRatio(Double usageRatio) {
        this.usageRatio = usageRatio;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public Integer getIsTrusteeship() {
        return isTrusteeship;
    }

    public void setIsTrusteeship(Integer isTrusteeship) {
        this.isTrusteeship = isTrusteeship;
    }

    public List<AttachmentVo> getImages() {
        return images;
    }

    public void setImages(List<AttachmentVo> images) {
        this.images = images;
    }

    public ContractVo getContractVo() {
        return contractVo;
    }

    public void setContractVo(ContractVo contractVo) {
        this.contractVo = contractVo;
    }

    public TrusteeshipVo getTrusteeshipVo() {
        return trusteeshipVo;
    }

    public void setTrusteeshipVo(TrusteeshipVo trusteeshipVo) {
        this.trusteeshipVo = trusteeshipVo;
    }

    public ModifiedRecordVo getModifiedRecordVo() {
        return modifiedRecordVo;
    }

    public void setModifiedRecordVo(ModifiedRecordVo modifiedRecordVo) {
        this.modifiedRecordVo = modifiedRecordVo;
    }
}
