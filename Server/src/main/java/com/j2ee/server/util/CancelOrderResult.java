package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 订单退订结果
 * @Date: 2019/2/19
 */
public enum CancelOrderResult {
    INVALIDID("退订失败，订单不存在"),
    IN10MIN("退订成功，5分钟内，收取10%费用"),
    IN20MIN("退订成功，10分钟内，收取20%费用"),
    FAIL("退订失败，10分钟后，无法退订"),
    HASDELIVERED("商家已发货，无法退订"),
    HASRECEIVED("已收货，无法退订");

    private final String str;

    CancelOrderResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
