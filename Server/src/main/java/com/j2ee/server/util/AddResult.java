package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 添加商品、套餐、优惠的结果
 * @Date: 2019/1/26
 */
public enum AddResult {
    PASS("成功"),
    INCOMPLETEINFO("信息未填写完整"),
    INVALIDCANTEEN("餐厅不存在"),
    TIMEERROR("时间填写错误"),
    INVALIDGOODS("商品不存在");

    private final String str;

    AddResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
