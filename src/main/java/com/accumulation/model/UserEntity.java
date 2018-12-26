package com.accumulation.model;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/18 9:18
 */
public class UserEntity {
    /*id*/
    private int id;
    /*登录名*/
    private String loginName;
    /*姓名*/
    private String name;
    private String password;
    /*用户类型(0:普通用户，1:管理员)*/
    private Integer userType;
    private String email;
    /*头像url*/
    private String headimg;
    /*GitHub主页*/
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /*注册时间*/
    private String createTime;


}
