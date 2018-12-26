package com.accumulation.dao;

import com.accumulation.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/18 9:17
 */
@Mapper
@Component
public interface UserDao {
    public UserEntity getUserEntityById(@Param("id") long id);

    public UserEntity getUserEntityByLoginName(@Param("loginName") String loginName);

    public ArrayList<UserEntity> usersList(@Param("loginName") String loginName, @Param("pageSize") int pageSize, @Param("start") int start);

    public Integer usersSize(@Param("loginName") String loginName);

    public void insertUser(UserEntity userEntity);

    public void updateUser(UserEntity userEntity);

    /**
     * 删除用户信息
     *
     * @param groupId
     */
    public void deleteUsers(@Param("groupId") List<String> groupId);

    public void updateUsertype(@Param("loginName") String loginName, @Param("usertype") Integer usertype);

}
