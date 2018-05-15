package com.jinhui.domain;

/**
 * Created by jinhui on 2018/5/3.
 */
public class BatchImportHouse_V4 {
    public final TenantInfo tenantInfo;
    public final RenterInfo renterInfo;
    public final ManagementInfo managementInfo;
    public final RentalInfo rentalInfo;
    public final UsableInfo usableInfo;
    public final DecorationInfo decorationInfo;
    public final HouseType houseType;
    public final BaseInfo baseInfo;
    public final TrafficInfo trafficInfo;

    public BatchImportHouse_V4() {
        tenantInfo = new TenantInfo();
        renterInfo = new RenterInfo();
        managementInfo = new ManagementInfo();
        rentalInfo = new RentalInfo();
        usableInfo = new UsableInfo();
        decorationInfo = new DecorationInfo();
        houseType = new HouseType();
        baseInfo = new BaseInfo();
        trafficInfo = new TrafficInfo();
    }

    public class TenantInfo {
        //年龄	性别	职业	收入	婚姻状况	子女状况
        private String age;
        private String gender;
        private String profession;
        private String marital;
        private String hasChild;
        //央行征信报告	其他信用评级	司法诉讼记录	房产	占总资产比例	其他金融资产占总资产比例
        private String bankCreditReport;
        private String otherCreditReport;
        private String judicalRecords;
        private String estateRatio;
        private String otherPropertyRatio;

        public void setAge(String age) {
            this.age = age;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public void setMarital(String marital) {
            this.marital = marital;
        }

        public void setHasChild(String hasChild) {
            this.hasChild = hasChild;
        }

        public void setBankCreditReport(String bankCreditReport) {
            this.bankCreditReport = bankCreditReport;
        }

        public void setOtherCreditReport(String otherCreditReport) {
            this.otherCreditReport = otherCreditReport;
        }

        public void setJudicalRecords(String judicalRecords) {
            this.judicalRecords = judicalRecords;
        }

        public void setEstateRatio(String estateRatio) {
            this.estateRatio = estateRatio;
        }

        public void setOtherPropertyRatio(String otherPropertyRatio) {
            this.otherPropertyRatio = otherPropertyRatio;
        }

        public String getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public String getProfession() {
            return profession;
        }

        public String getMarital() {
            return marital;
        }

        public String getHasChild() {
            return hasChild;
        }

        public String getBankCreditReport() {
            return bankCreditReport;
        }

        public String getOtherCreditReport() {
            return otherCreditReport;
        }

        public String getJudicalRecords() {
            return judicalRecords;
        }

        public String getEstateRatio() {
            return estateRatio;
        }

        public String getOtherPropertyRatio() {
            return otherPropertyRatio;
        }
    }
//===========================================================
    public class RenterInfo {
        //年龄	性别	职业	收入	婚姻状况	子女状况
        private String age;
        private String gender;
        private String income;
        private String marital;
        private String hasChild;
        //央行征信报告	其他信用评级	司法诉讼记录	房产	占总资产比例	其他金融资产占总资产比例
        private String bankCreditReport;
        private String otherCreditReport;
        private String judicalRecords;
        private String estateRatio;
        private String otherPropertyRatio;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getBankCreditReport() {
        return bankCreditReport;
    }

    public void setBankCreditReport(String bankCreditReport) {
        this.bankCreditReport = bankCreditReport;
    }

    public String getOtherCreditReport() {
        return otherCreditReport;
    }

    public void setOtherCreditReport(String otherCreditReport) {
        this.otherCreditReport = otherCreditReport;
    }

    public String getJudicalRecords() {
        return judicalRecords;
    }

    public void setJudicalRecords(String judicalRecords) {
        this.judicalRecords = judicalRecords;
    }

    public String getEstateRatio() {
        return estateRatio;
    }

    public void setEstateRatio(String estateRatio) {
        this.estateRatio = estateRatio;
    }

    public String getOtherPropertyRatio() {
        return otherPropertyRatio;
    }

    public void setOtherPropertyRatio(String otherPropertyRatio) {
        this.otherPropertyRatio = otherPropertyRatio;
    }
}
    //===========================================================
    public class ManagementInfo {
        //管理类别	管理系统	智能电表	智能门锁	智能路由
        private String type;
        private String hasSys;
        private String hasSmartMeter;
        private String hasSmartLock;
        private String hasSmartRouter;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHasSys() {
            return hasSys;
        }

        public void setHasSys(String hasSys) {
            this.hasSys = hasSys;
        }

        public String getHasSmartMeter() {
            return hasSmartMeter;
        }

        public void setHasSmartMeter(String hasSmartMeter) {
            this.hasSmartMeter = hasSmartMeter;
        }

        public String getHasSmartLock() {
            return hasSmartLock;
        }

        public void setHasSmartLock(String hasSmartLock) {
            this.hasSmartLock = hasSmartLock;
        }

        public String getHasSmartRouter() {
            return hasSmartRouter;
        }

        public void setHasSmartRouter(String hasSmartRouter) {
            this.hasSmartRouter = hasSmartRouter;
        }
    }
    //===========================================================
    public class RentalInfo {
        //租金价格/月	付款方式	平均租期	平均空置周期
        private String rent;
        private String payType;
        private String tenancy;
        private String vacancy;

        public void setRent(String rent) {
            this.rent = rent;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public void setTenancy(String tenancy) {
            this.tenancy = tenancy;
        }

        public void setVacancy(String vacancy) {
            this.vacancy = vacancy;
        }

        public String getRent() {
            return rent;
        }

        public String getPayType() {
            return payType;
        }

        public String getTenancy() {
            return tenancy;
        }

        public String getVacancy() {
            return vacancy;
        }
    }
    //===========================================================
    public class UsableInfo {
        //卧室	客厅	卫生间	阳台
        private String bedroom;
        private String drawingRoom;
        private String bathroom;
        private String balcony;

        public void setBedroom(String bedroom) {
            this.bedroom = bedroom;
        }

        public void setDrawingRoom(String drawingRoom) {
            this.drawingRoom = drawingRoom;
        }

        public void setBathroom(String bathroom) {
            this.bathroom = bathroom;
        }

        public void setBalcony(String balcony) {
            this.balcony = balcony;
        }

        public String getBedroom() {
            return bedroom;
        }

        public String getDrawingRoom() {
            return drawingRoom;
        }

        public String getBathroom() {
            return bathroom;
        }

        public String getBalcony() {
            return balcony;
        }
    }
    //===========================================================
    public class DecorationInfo {
        private String year;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }
    //===========================================================
    public class HouseType {
        //楼号	单元号	门牌号	总面积	户型	楼层
        private String buildingNo;
        private String cellNo;
        private String doorNo;
        private String area;
        private String type;
        private String floors;

        public String getBuildingNo() {
            return buildingNo;
        }

        public void setBuildingNo(String buildingNo) {
            this.buildingNo = buildingNo;
        }

        public String getCellNo() {
            return cellNo;
        }

        public void setCellNo(String cellNo) {
            this.cellNo = cellNo;
        }

        public String getDoorNo() {
            return doorNo;
        }

        public void setDoorNo(String doorNo) {
            this.doorNo = doorNo;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setFloors(String floors) {
            this.floors = floors;
        }

        public String getArea() {
            return area;
        }

        public String getType() {
            return type;
        }

        public String getFloors() {
            return floors;
        }
    }
    //===========================================================
    public class TrafficInfo {
        // 地铁站	距离	公交站	距离
        private String metro;
        private String metroDistance;
        private String bus;
        private String busDistance;

        public void setMetro(String metro) {
            this.metro = metro;
        }

        public void setMetroDistance(String metroDistance) {
            this.metroDistance = metroDistance;
        }

        public void setBus(String bus) {
            this.bus = bus;
        }

        public void setBusDistance(String busDistance) {
            this.busDistance = busDistance;
        }

        public String getMetro() {
            return metro;
        }

        public String getMetroDistance() {
            return metroDistance;
        }

        public String getBus() {
            return bus;
        }

        public String getBusDistance() {
            return busDistance;
        }
    }
    //===========================================================
    public class BaseInfo {
        //省 	市	区/县	物业地址	产权性质	入住年限	小区规划	物业服务	房屋总套数	出租房屋比例	户型
        private String province;
        private String city;
        private String region;
        private String propertyAddr;
        private String propertyType;
        private String liveInYear;
        private String houseType;
        private String communityLayout;
        private String households;
        private String rentalRatio;

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public void setPropertyAddr(String propertyAddr) {
            this.propertyAddr = propertyAddr;
        }

        public void setPropertyType(String propertyType) {
            this.propertyType = propertyType;
        }

        public void setLiveInYear(String liveInYear) {
            this.liveInYear = liveInYear;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public void setCommunityLayout(String communityLayout) {
            this.communityLayout = communityLayout;
        }

        public void setHouseholds(String households) {
            this.households = households;
        }

        public void setRentalRatio(String rentalRatio) {
            this.rentalRatio = rentalRatio;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getRegion() {
            return region;
        }

        public String getPropertyAddr() {
            return propertyAddr;
        }

        public String getPropertyType() {
            return propertyType;
        }

        public String getLiveInYear() {
            return liveInYear;
        }

        public String getHouseType() {
            return houseType;
        }

        public String getCommunityLayout() {
            return communityLayout;
        }

        public String getHouseholds() {
            return households;
        }

        public String getRentalRatio() {
            return rentalRatio;
        }
    }
}
