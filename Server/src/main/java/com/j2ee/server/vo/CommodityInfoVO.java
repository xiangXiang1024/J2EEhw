package com.j2ee.server.vo;

import com.j2ee.server.entity.Commodities;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/27
 */
public class CommodityInfoVO {
    private String id;
    private String name;

    public CommodityInfoVO() {
    }

    public CommodityInfoVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CommodityInfoVO(Commodities commodity) {
        this.id = commodity.getId();
        this.name = commodity.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("CommodityInfoVO:");
        System.out.println("id: "+id);
        System.out.println("name: "+name);
    }
}
