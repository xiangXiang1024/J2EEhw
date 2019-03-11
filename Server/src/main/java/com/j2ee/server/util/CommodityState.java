package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 商品状态
 * @Date: 2019/1/26
 */
public enum CommodityState {
    NOTONSHELF("未上架"),
    INSTOCK("在售"),
    HASREMOVED("已下架");

    private final String str;

    CommodityState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
