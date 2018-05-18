package com.jinhui.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jinhui.domain.*;
import com.jinhui.mapper.ExtraMapper;
import com.jinhui.mapper.HouseMapper;
import com.jinhui.mapper.UserMapper;
import com.jinhui.service.RentService;
//import com.jinhui.util.FabricShellClient;
import com.jinhui.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinhui on 2018/1/18.
 */
@Service("RentService")
public class RentServiceImpl implements RentService {

    private static Logger logger = LoggerFactory.getLogger(RentServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private ExtraMapper extraMapper;
    //@Autowired
    //private FabricShellClient fabricClient;
    @Autowired
    private RentService self;
    @Value("${icon.path}")
    private String iconPath;
    @Override
    public User findUser(String cellphone) {
        User user = userMapper.findUserByCellphone(cellphone);
        if(user == null)
            return user;
        List<House> lastModifiedHouses = houseMapper.findLastModifiedHouses(user.getId());
        user.setLastModifiedHouses(lastModifiedHouses);
        return user;
    }

    @Override
    public User findUserByOpenid(String openid) {
        User user = userMapper.findUserByOpenid(openid);
        if(user == null)
            return user;
        List<House> lastModifiedHouses = houseMapper.findLastModifiedHouses(user.getId());
        user.setLastModifiedHouses(lastModifiedHouses);
        return user;
    }

    @Override
    public List<House> findHouseModifiedList(String houseId) {
        List<House> houses = houseMapper.findHouseModifiedList(houseId);
        // query from fabric
        /*String housesJson = fabricClient.
                query(FabricShellClient.Method.QueryHistoryJsonString, new String[]{houseId});
        List<House> houses = JSON.parseArray(housesJson, House.class);*/
        return houses;
    }

    @Override
    @Transactional
    public Double takeAction(House house) {
        houseMapper.saveHouse(house);
        house.getContract().setModifiedId(house.getModifiedId());
        houseMapper.saveContract(house.getContract());
        house.getTrusteeship().setModifiedId(house.getModifiedId());
        houseMapper.saveTrusteeship(house.getTrusteeship());
        house.getModifiedRecord().setModifiedId(house.getModifiedId());
        ModifiedRecord mr = house.getModifiedRecord();
        houseMapper.saveModifiedRecord(mr);
        //增加积分
        if(house.getModifiedRecord().getOperationType()
                .equals(ModifiedRecord.OperationType.Approved)) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("uid",mr.getUserRole());
            paras.put("houseId",house.getId());
            House lastModifiedHouse = houseMapper.findLastModifiedHouse(paras);
            if(lastModifiedHouse != null
                    && lastModifiedHouse.getModifiedRecord().getOperationType()
                    .equals(ModifiedRecord.OperationType.Approved)) {
                return 0.0;
            }
        }
        double points = 0.0;
        User.Role role = User.Role.codeOf(mr.getUserRole());
        for (ModifiedRecord.OperationField of : mr.getOperationFields()) {
            points += role.reward(of);
        }
        self.addPoints(mr.getUserId(), points);
        return points;
        //store to fabric
       /* String houseJson = JSONObject.toJSONString(house);
        if(logger.isInfoEnabled())
            logger.info("异步将房屋数据存入fabric, houseId = {}", house.getId());
        fabricClient.asycExec(FabricShellClient.Method.StoreJsonString,
                new String[]{house.getId(), houseJson.replace("\"","\\\"")});*/
    }

    @Override
    public String fetchHouseId() {
        return IdGenerator.generate();
    }

    @Override
    public String iconPath() {
        return this.iconPath;
    }

    @Override
    public void addUser(User user) {
        user.addPoint(10.0);
        userMapper.saveUser(user);
    }

    @Override
    public void modifyUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void addPoints(Long uid, Double points) {
        //增加积分
        User operator = userMapper.findUserById(uid);
        if(operator == null) {
            throw new IllegalArgumentException("操作用户未知");
        }
        operator.addPoint(points);
        userMapper.updateUser(operator);
    }

    @Override
    public boolean hasUser(String uid) {
        return false;
    }

    @Override
    public void uploadAttachment(Attachment attachment) {
        extraMapper.saveAttachment(attachment);
    }

    @Override
    public List<Attachment> findAttachments(List<Long> ids) {
        return extraMapper.findAttachments(ids);
    }

    public static void main(String[] strs) {
        System.out.println("{\"key\":\"value\"}".replace("\"","\\\""));
    }
}
