package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
public enum UserType {
    CANTEEN("餐厅"),
    CLIENT("会员"),
    ADMIN("管理员");

    private final String str;

    UserType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
