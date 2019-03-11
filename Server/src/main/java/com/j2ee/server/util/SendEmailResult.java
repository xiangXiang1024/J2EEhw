package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
public enum SendEmailResult {
    NOUSER("用户名未填写"),
    EXISTUSER("用户已存在"),
    EXISTMAIL("邮箱已存在"),
    INVALIDMAIL("邮箱格式错误"),
    SUCCESS("成功");

    private final String str;

    SendEmailResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
