package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 会员下单结果
 * @Date: 2019/1/28
 */
public enum AddOrderResult {
    PASS("下单成功"),
    INVALIDCANTEEN("餐厅不存在"),
    TOOFAR("超出配送区"),
    INVALIDCLIENT("会员不存在"),
    INVALIDOFFER("优惠不存在"),
    INVALIDGOODS("商品不存在"),
    NOGOODS("未添加商品"),
    NOTENOUGHSTOCK("库存不足"),
    NOTENOUGHBALANCE("余额不足"),
    CANNOTUSEORDER("优惠无法使用");

    private final String s;

    AddOrderResult(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
