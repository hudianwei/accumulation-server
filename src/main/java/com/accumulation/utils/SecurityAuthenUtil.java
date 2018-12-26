package com.accumulation.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/18 17:34
 */
public class SecurityAuthenUtil {
    public static String getLoginName() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User authenUser=(User) authentication.getPrincipal();
        return authenUser.getUsername();
    }

    public static User getAuthenticationUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User authenUser=(User) authentication.getPrincipal();
        return authenUser;
    }
}
