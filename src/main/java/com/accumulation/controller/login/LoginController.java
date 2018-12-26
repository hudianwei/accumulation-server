package com.accumulation.controller.login;


import com.accumulation.controller.authentication.MyAuthentication;
import com.accumulation.controller.authentication.MyAuthenticationToken;
import com.accumulation.security.MyUserDetailsService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @Description:分为两部分，一个是第三方认证，一个是获取OAuth2.0的认证令牌
 * @author: HU
 * @date: 2018/9/19 16:07
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public class LoginController {
    @Autowired
    private MyAuthentication gitHubAuthentication;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/github")
    public OAuth2AccessToken loginForGithHub(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code)
            throws IOException {
        // String code = request.getParameter(WEIXIN_CODE);

        if (code == null) {
            code = "";
        }

        code = code.trim();

        String id = gitHubAuthentication.getUserId(code);

        MyAuthenticationToken authRequest = new MyAuthenticationToken(id);

        authRequest.setDetails(new OAuth2AuthenticationDetails(request));

        OAuth2AccessToken oAuth2AccessToken = loginSuccessHandler.getAccessToken(request, response, authRequest);

        return oAuth2AccessToken;
    }

    /**
     * @Description: 通过access_token获取认证信息
     * @Param: [userDetails]
     * @Return: org.springframework.security.core.userdetails.UserDetails
     * @Author: HU
     * @Date: 2018/12/26 10:09
     */
    @GetMapping("/test")
    public UserDetails AuthorizationTest(@AuthenticationPrincipal UserDetails userDetails) {
        //不管表单认证，短信认证，token认证，只要上使用Spring Security框架进行的认证，都可以使用下面的方法获取当前登陆的用户
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }
}
