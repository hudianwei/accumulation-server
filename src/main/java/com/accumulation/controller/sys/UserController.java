package com.accumulation.controller.sys;

import com.accumulation.model.PageResult;
import com.accumulation.model.UserEntity;
import com.accumulation.service.UserService;
import com.accumulation.utils.SecurityAuthenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/18 15:22
 */
@RestController
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @GetMapping("/public/user")
    public UserEntity userGet() {
        UserEntity a= userService.getUserEntityById(1);
        String loginName = null;
        try {
            loginName = SecurityAuthenUtil.getLoginName();
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        UserEntity userEntity = userService.getUserEntityByLoginName(loginName);
        return userEntity;
    }

    /**
     * @Description:
     * @Param:
     * @Return:
     * @Author: HU
     * @Date: 2018/9/19 14:35
     **/
    @GetMapping("/users")
    public PageResult usersList(String loginName, int pageSize, int page) {
        PageResult pageResult = new PageResult();
        pageResult.setData(userService.usersList(loginName, pageSize, page * pageSize));
        log.debug("The method is ending");
        return pageResult;
    }
}
