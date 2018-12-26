package com.accumulation.service.impl;

import com.accumulation.dao.UserDao;
import com.accumulation.model.UserEntity;
import com.accumulation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/18 15:17
 */
@SuppressWarnings("ALL")
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getUserEntityById(long id) {
        return userDao.getUserEntityById(id);
    }

    @Override
    public UserEntity getUserEntityByLoginName(String loginName) {
        return userDao.getUserEntityByLoginName(loginName);
    }

    @Override
    public List<UserEntity> usersList(String loginName, int pageSize, int start) {
        return null;
    }

    @Override
    public Integer userSize(String loginName, int pageSize, int start) {
        return null;
    }

    @Override
    public void insertUser(UserEntity userEntity) {

    }

    @Override
    public void updateUser(UserEntity userEntity) {

    }

    @Override
    public void deleteUser(List<String> groupId) {

    }

    @Override
    public void updateUsertype(UserEntity userEntity) {

    }
}
