package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 餐厅类型
 * @Date: 2019/3/1
 */
public enum CanteenType {
    SELF_HELP("自助餐厅"),
    CHINESE_RESTAURANT("中餐厅"),
    WESTERN_RESTAURANT("西餐厅"),
    SNACK("小吃店"),
    DESSERT_SHOP("甜品店"),
    FAST_FOOD_RESTAURANT("快餐厅");

    private final String str;

    CanteenType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static CanteenType getEnum(String s) {
        CanteenType[] types = CanteenType.values();
        for(CanteenType type : types) {
            if(type.getStr().equals(s)) {
                return type;
            }
        }
        return null;
    }
}
