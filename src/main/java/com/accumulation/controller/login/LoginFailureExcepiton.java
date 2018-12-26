package com.accumulation.controller.login;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/20 9:29
 */
public class LoginFailureExcepiton extends RuntimeException {
    public LoginFailureExcepiton(String message) {
        super(message);
    }
}
