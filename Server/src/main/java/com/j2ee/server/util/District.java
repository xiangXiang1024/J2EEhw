package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 南京的市辖区
 * @Date: 2019/1/28
 */
public enum District {
    // 玄武区、秦淮区、鼓楼区、建邺区、栖霞区、雨花台区、浦口区、江宁区、六合区、溧水区、高淳区
    QINWU("玄武区"),
    QINHUAI("秦淮区"),
    GULOU("鼓楼区"),
    JIANYE("建邺区"),
    QIXIA("栖霞区"),
    PUKOU("浦口区"),
    YUHUATAI("雨花台区"),
    JIANGNING("江宁区"),
    LUHE("六合区"),
    LISHUI("溧水区"),
    GAOCHUN("高淳区"),;

    private final String str;

    District(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static District getEnum(String str) {
        District[] districts = District.values();
        for(District district : districts) {
            if(district.getStr().equals(str)) {
                return district;
            }
        }
        return null;
    }
}
