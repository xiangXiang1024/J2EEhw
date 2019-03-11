package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 地址状态
 * @Date: 2019/2/19
 */
public enum AddressState {
    DEFAULT("默认地址"),
    INUSE("使用中"),
    DISCARD("弃用");

    private final String str;

    AddressState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
