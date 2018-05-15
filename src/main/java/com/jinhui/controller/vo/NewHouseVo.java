package com.jinhui.controller.vo;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2018/4/9.
 */
public class NewHouseVo {
    @ApiModelProperty(value = "房子ID", required = true)
    private String id;
    //小区名称
    @ApiModelProperty(value = "小区名称")
    private String community;
    //地址-省
    @ApiModelProperty(value = "省")
    private String province;
    //地址-市
    @ApiModelProperty(value = "市")
    private String city;
    //地址-区
    @ApiModelProperty(value = "区")
    private String district;
    //地址-路
    @ApiModelProperty(value = "路")
    private String road;
    //地址-号
    @ApiModelProperty(value = "街道号")
    private String doorNo;
    //具体地址
    @ApiModelProperty(value = "详细地址")
    private String detailAddress;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    //房产证（图）,文件ID字符串
    @ApiModelProperty(value = "房屋证明图片，逗号分隔的文件id字符串")
    private String houseCredential;
    //建筑面积
    @ApiModelProperty(value = "建筑面积")
    private Double area;
    //实用面积
    @ApiModelProperty(value = "实际可用面积")
    private Double realArea;
    //使用率
    @ApiModelProperty(value = "使用率")
    private Double usageRatio;
    //朝向
    @ApiModelProperty(value = "朝向")
    private String direction;
    //楼
    @ApiModelProperty(value = "楼层")
    private String floor;
    // 室-厅-厕
    @ApiModelProperty(value = "户型")
    private String construction;
    //是否托管
    @ApiModelProperty(value = "是否托管[1:托管， 0:未托管]")
    private Integer isTrusteeship;
    //房源图片, 逗号分隔的文件id字符串
    @ApiModelProperty(value = "房源图片, 逗号分隔的文件id字符串")
    private String images;
    @ApiModelProperty(value = "租约", required = true, dataType = "com.jinhui.controller.vo.NewContractVo")
    private NewContractVo newContractVo;
    @ApiModelProperty(value = "托管", required = true, dataType = "com.jinhui.controller.vo.NewTrusteeship")
    private NewTrusteeship newTrusteeship;
    @ApiModelProperty(value = "修改记录", required = true, dataType = "com.jinhui.controller.vo.NewModifiedRecord")
    private NewModifiedRecord newModifiedRecord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHouseCredential() {
        return houseCredential;
    }

    public void setHouseCredential(String houseCredential) {
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public NewContractVo getNewContractVo() {
        return newContractVo;
    }

    public void setNewContractVo(NewContractVo newContractVo) {
        this.newContractVo = newContractVo;
    }

    public NewTrusteeship getNewTrusteeship() {
        return newTrusteeship;
    }

    public void setNewTrusteeship(NewTrusteeship newTrusteeship) {
        this.newTrusteeship = newTrusteeship;
    }

    public NewModifiedRecord getNewModifiedRecord() {
        return newModifiedRecord;
    }

    public void setNewModifiedRecord(NewModifiedRecord newModifiedRecord) {
        this.newModifiedRecord = newModifiedRecord;
    }

    @Override
    public String toString() {
        return "NewHouseVo{" +
                "id='" + id + '\'' +
                ", community='" + community + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", road='" + road + '\'' +
                ", doorNo='" + doorNo + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", houseCredential='" + houseCredential + '\'' +
                ", area=" + area +
                ", realArea=" + realArea +
                ", usageRatio=" + usageRatio +
                ", direction='" + direction + '\'' +
                ", floor='" + floor + '\'' +
                ", construction='" + construction + '\'' +
                ", isTrusteeship=" + isTrusteeship +
                ", images='" + images + '\'' +
                ", newContractVo=" + newContractVo +
                ", newTrusteeship=" + newTrusteeship +
                ", newModifiedRecord=" + newModifiedRecord +
                '}';
    }
}
