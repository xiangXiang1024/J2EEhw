package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
public enum LoginResult {
    PASS("登录成功"),
    INVALIDUSER("用户不存在"),
    WRONGPASSWORD("密码错误");
    private final String str;

    LoginResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
