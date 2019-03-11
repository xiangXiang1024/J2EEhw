package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 商品类别
 * @Date: 2019/2/16
 */
public enum GoodsType {
    drink("饮料"),
    staplefood("主食"),
    dessert("甜点"),
    setmeal("套餐");

    private final String str;

    GoodsType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static GoodsType getEnum(String str) {
        GoodsType[] types = GoodsType.values();
        for(GoodsType type : types) {
            if(str.equals(type.getStr())) {
                return type;
            }
        }
        return null;
    }
}
