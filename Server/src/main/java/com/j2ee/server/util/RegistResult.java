package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/22
 */
public enum RegistResult {
    PASS("注册成功"),
    INCOMPLETEINFOMATION("信息填写不完整"),
    HASEXISTED("用户已存在"),
    NOVERRIFICATION("邮箱未验证"),
    WRONGVERRIFICATION("验证码错误"),
    OVERTIME("超时");

    private final String str;

    RegistResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
