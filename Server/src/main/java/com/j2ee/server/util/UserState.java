package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
public enum UserState {
    ACTIVE("使用中"),
    ABANDON("已注销");

    private final String str;

    UserState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
