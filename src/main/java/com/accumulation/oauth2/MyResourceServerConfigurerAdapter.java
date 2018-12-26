package com.accumulation.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Description: ResourceServerConfigurerAdapter 用于保护 OAuth2 要开放的资源，
 * 同时主要作用于client端以及token的认证(Bearer Auth)，由于后面 OAuth2 服务端后续还需要提供用户信息，
 * 所以也是一个 Resource Server，默认拦截了所有的请求，也可以通过重新方法方式自定义自己想要拦截的资源 URL 地址。
 * 另外根据 OAuth2.0 规范，获取票据要支持 Basic 验证与验证用户的账户信息，比如密码模式：
 * POST /token HTTP/1.1
 * Host: server.example.com
 * Authorization: Basic 1sZCaJks20MzpnMsPOi
 * Content-Type: application/x-www-form-urlencoded
 * grant_type=password&username=irving&password=123456
 * 可以在 WebSecurityConfigurerAdapter 类中重新相应的方法来实现。
 * @author: HU
 * @date: 2018/9/21 10:21
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/**/*.jpg", "/**/*.png", "/**/*.jpeg").permitAll()
                .antMatchers("/users/**", "/menus/**", "/roles/**", "/admin/**").hasRole("ADMIN")
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/test").permitAll()
                .anyRequest().authenticated();
    }
}
