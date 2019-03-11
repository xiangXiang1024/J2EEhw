package com.j2ee.server.vo;

import com.j2ee.server.util.LoginResult;
import com.j2ee.server.util.UserType;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
public class LoginResultVO {
    private LoginResult result;
    private String userId;
    private UserType userType;
    private String loginResult;

    public LoginResultVO(LoginResult result) {
        this.result = result;
        loginResult = result.getStr();
    }

    public LoginResultVO(LoginResult result, String userId, UserType userType) {
        this.result = result;
        this.userId = userId;
        this.userType = userType;
        loginResult = result.getStr();
    }

    public LoginResult getResult() {
        return result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
        loginResult = result.getStr();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public void print() {
        System.out.println("LoginResultVO:");
        System.out.println("result = " + result);
        System.out.println("userId = " + userId);
        System.out.println("userType = " + userType);
    }
}
