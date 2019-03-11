package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/16
 */
public enum OrderState {
    FINISHED("已完成"),
    WAITINGFORORDER("已下单"),
    HASPAID("已付款"),
    HASACCEPT("已接单"),
    HASDELIVERED("已发货"),
    CANCELED("已退订");

    private final String str;

    OrderState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
