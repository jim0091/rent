package com.jinhui.controller.assembler;

import com.jinhui.controller.RentController;
import com.jinhui.controller.vo.*;
import com.jinhui.domain.*;
import com.jinhui.service.RentService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jinhui on 2018/4/9.
 */
public class ObjectAssembler {

    public static Attachment toAttachment(Long userId, String name, String type) {
        Attachment attachment = new Attachment(userId, name, Attachment.Type.typeOf(type));
        return attachment;
    }

    public static List<AttachmentVo> toAttachmentVos(List<Attachment> attachments) {
        List<AttachmentVo> vos = new ArrayList<>();
        //List<Attachment> attachments = rentService.findAttachments(attachmentIds);
        for(Attachment attachment:attachments) {
            AttachmentVo vo = toAttachmentVo(attachment);
            vos.add(vo);
        }
        return vos;
    }

    public static AttachmentVo toAttachmentVo(Attachment attachment) {
        AttachmentVo vo = new AttachmentVo();
        BeanUtils.copyProperties(attachment, vo);
        vo.setUrl(RentController.AttachmentUrl+ attachment.toUrl());
        vo.setType(attachment.getType().toString());
        return vo;
    }

    public static User toUser(NewUserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        String encodePassword = encodePassword(userVo.getPassword());
        user.setPassword(encodePassword);
        return user;
    }
    public static House toHouse(NewHouseVo houseVo) {
        House house = new House();
        BeanUtils.copyProperties(houseVo, house);
        //contract
        Contract contract = new Contract();
        BeanUtils.copyProperties(houseVo.getNewContractVo(), contract);
        house.setContract(contract);
        //trusteeship
        Trusteeship trusteeship = new Trusteeship();
        BeanUtils.copyProperties(houseVo.getNewTrusteeship(), trusteeship);
        house.setTrusteeship(trusteeship);
        //modifiedRecord
        ModifiedRecord modifiedRecord = new ModifiedRecord();
        BeanUtils.copyProperties(houseVo.getNewModifiedRecord(), modifiedRecord);
        modifiedRecord.setGmtCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        house.setModifiedRecord(modifiedRecord);
        List<ModifiedRecord.OperationField> operationFields = new ArrayList<>();
        operationFields.add(ModifiedRecord.OperationField.TakeAction);
        List<String> operationFieldVos = houseVo.getNewModifiedRecord().getOperationFields();
        for(String of : operationFieldVos) {
            operationFields.add(ModifiedRecord.OperationField.codeOf(of));
        }
        modifiedRecord.setOperationFields(operationFields);
        return house;
    }

    public static UserVo toUserVo(User user, RentService rentService) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        List<Attachment> attachments = strIds2Attachments(user.getIdentityImage(), rentService);
        List<AttachmentVo> identityImages = toAttachmentVos(attachments);
        userVo.setIdentityImage(identityImages);
        List<House> lastModifiedHouses = user.getLastModifiedHouses();
        if(lastModifiedHouses != null) {
            List<HouseVo> houseVos = new ArrayList<>();
            for (House house : lastModifiedHouses) {
                HouseVo houseVo = toHouseVo(house, rentService);
                houseVos.add(houseVo);
            }
            userVo.setHouseVos(houseVos);
        }
        return userVo;
    }

    public static List<PointVo> loginReward(User user) {
        List<PointVo> pointVos = new ArrayList<>();
        Calendar lastLoginTime = Calendar.getInstance();
        lastLoginTime.setTime(user.getLastLoginTime());
        Calendar currentTime = Calendar.getInstance();
        long hours = (currentTime.getTimeInMillis() - lastLoginTime.getTimeInMillis()) / (1000 * 60 *60);
        hours = hours > 24 ? 24 : hours;
        for(int h=1;h<=hours;) {
            PointVo pointVo = new PointVo();
            pointVo.setIndex(h++);
            Double nonce = nextDouble(0.05, 0.05);
            pointVo.setNonce(nonce);
            Double point = nonce * (user.getLastModifiedHouses().size() + 1);
            //余两位小数
            point = (double)Math.round(point*100)/100;
            pointVo.setPoint(point);
            pointVos.add(pointVo);
        }
        return pointVos;
    }

    private static double nextDouble(double origin, double bound) {
        Random random = new Random();
        double r = random.nextDouble();
        r = r * (bound - origin) + origin;
        if (r >= bound)
            r = Math.nextDown(bound);
        return r;
    }

    public static HouseVo toHouseVo(House house, RentService rentService) {
        HouseVo houseVo = new HouseVo();
        BeanUtils.copyProperties(house, houseVo);
        //houseCredential
        List<Attachment> attachments = strIds2Attachments(house.getHouseCredential(), rentService);
        List<AttachmentVo> attachmentVos = toAttachmentVos(attachments);
        houseVo.setHouseCredential(attachmentVos);
        //images
        attachments = strIds2Attachments(house.getImages(), rentService);
        attachmentVos = toAttachmentVos(attachments);
        houseVo.setImages(attachmentVos);
        if(house.getContract() != null) {
            ContractVo contractVo = toContractVo(house.getContract(), rentService);
            houseVo.setContractVo(contractVo);
        }
        if(house.getTrusteeship() != null) {
            TrusteeshipVo trusteeshipVo = toTrusteeshipVo(house.getTrusteeship(), rentService);
            houseVo.setTrusteeshipVo(trusteeshipVo);
        }
        if(house.getModifiedRecord() != null) {
            ModifiedRecordVo modifiedRecordVo = toModifiedRecordVo(house.getModifiedRecord());
            houseVo.setModifiedRecordVo(modifiedRecordVo);
        }
        return houseVo;
    }

    public static ContractVo toContractVo(Contract contract, RentService rentService) {
        ContractVo contractVo = new ContractVo();
        BeanUtils.copyProperties(contract, contractVo);
        List<Attachment> attachments = strIds2Attachments(contract.getFiles(), rentService);
        List<AttachmentVo> attachmentVos = toAttachmentVos(attachments);
        contractVo.setFiles(attachmentVos);
        return contractVo;
    }

    public static TrusteeshipVo toTrusteeshipVo(Trusteeship trusteeship, RentService rentService) {
        TrusteeshipVo trusteeshipVo = new TrusteeshipVo();
        BeanUtils.copyProperties(trusteeship, trusteeshipVo);
        List<Attachment> attachments = strIds2Attachments(trusteeship.getFiles(), rentService);
        List<AttachmentVo> attachmentVos = toAttachmentVos(attachments);
        trusteeshipVo.setFiles(attachmentVos);
        return trusteeshipVo;
    }

    public static ModifiedRecordVo toModifiedRecordVo(ModifiedRecord modifiedRecord) {
        ModifiedRecordVo modifiedRecordVo = new ModifiedRecordVo();
        BeanUtils.copyProperties(modifiedRecord, modifiedRecordVo);
        return modifiedRecordVo;
    }

    public static String encodePassword(String data) {
        byte[]  hashcode = DigestUtils.md5Digest(data.getBytes());
        return Base64.getEncoder().encodeToString(hashcode);
    }

    private static List<String> strIds2Urls(String str, RentService rentService) {
        List<Attachment> attachments = strIds2Attachments(str, rentService);
        List<String> urls = new ArrayList<>(attachments.size());
        for(Attachment attachment:attachments) {
            urls.add(RentController.AttachmentUrl+ attachment.toUrl());
        }
        return urls;
    }

    private static List<Attachment> strIds2Attachments(String str, RentService rentService) {
        List<Long> attachmentIds = strIds2Ids(str);
        return attachmentIds.size() > 0 ?
                rentService.findAttachments(attachmentIds) : Collections.emptyList();
    }

    private static List<Long> strIds2Ids(String str) {
        List<Long> ids = new ArrayList<>();
        if(!StringUtils.isEmpty(str)) {
            String[] strIds = str.split(",");
            for(String strId:strIds) {
                if(!StringUtils.isEmpty(strId))
                    ids.add(Long.parseLong(strId.trim()));
            }
        }
        return ids;
    }
}
