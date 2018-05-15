package com.jinhui.service;


import com.jinhui.domain.Attachment;
import com.jinhui.domain.House;
import com.jinhui.domain.User;

import java.util.List;

/**
 * Created by jinhui on 2018/1/18.
 */
public interface RentService {

    User findUser(String cellphone);
    User findUserByOpenid(String openid);
    List<House> findHouseModifiedList(String houseId);
    void takeAction(House house);

    String fetchHouseId();
    String iconPath();
    void addUser(User user);
    void modifyUser(User user);
    void addPoints(Long uid, Long points);
    boolean hasUser(String uid);

    void uploadAttachment(Attachment attachment);
    List<Attachment> findAttachments(List<Long> ids);

}
