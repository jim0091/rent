package com.jinhui.mapper;


import com.jinhui.domain.User;

/**
 * Created by jinhui on 2018/1/18.
 */
public interface UserMapper {

    User findUserByCellphone(String cellphone);
    User findUserById(Long id);
    User findUserByOpenid(String openid);
    void saveUser(User user);
    void updateUser(User user);
}
