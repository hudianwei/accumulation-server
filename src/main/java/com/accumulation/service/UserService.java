package com.accumulation.service;

import com.accumulation.model.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/18 15:17
 */
public interface UserService {
    public UserEntity getUserEntityById(@Param("id") long id);

    public UserEntity getUserEntityByLoginName(@Param("loginName") String loginName);

    /*
     * @Description:获取user列表
     * @Param:
     * @Return:
     * @Author: HU
     * @Date: 2018/9/19 14:33
     */
    public List<UserEntity> usersList(String loginName, int pageSize, int start);

    /**
     * @Description: 获取user列表的总量
     * @Param:
     * @Return:
     * @Author: HU
     * @Date: 2018/9/19 14:38
     **/
    public Integer userSize(String loginName, int pageSize, int start);

    /**
     * @Description: 新建用户信息
     * @Param:
     * @Return:
     * @Author: HU
     * @Date: 2018/9/19 14:49
     **/
    public void insertUser(UserEntity userEntity);

    /**
     * @Description:更新用户信息
     * @Param:
     * @Return:
     * @Author: HU
     * @Date: 2018/9/19 14:50
     **/
    public void updateUser(UserEntity userEntity);

    public void deleteUser(List<String> groupId);

    public void updateUsertype(UserEntity userEntity);
}
