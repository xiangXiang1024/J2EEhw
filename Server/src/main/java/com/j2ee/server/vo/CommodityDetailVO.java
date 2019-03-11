package com.j2ee.server.vo;

import com.j2ee.server.entity.Commodities;
import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.GoodsType;

import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/27
 */
public class CommodityDetailVO {
    private String commodityId;
    private String name;
    private double price;
    private String type;
    private String description;
    private int quantity;
    private String start;
    private String end;

    public CommodityDetailVO() {
    }

    public CommodityDetailVO(String commodityId, String name, double price, String type, String description, int quantity, String start, String end) {
        this.commodityId = commodityId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
    }

    public CommodityDetailVO(Commodities commodity) {
        this.commodityId = commodity.getId();
        this.name = commodity.getName();
        this.price = commodity.getPrice();
        this.type = commodity.getGoodsType().getStr();
        this.description = commodity.getDescription();
        this.quantity = commodity.getQuantity();
        this.start = commodity.getStart().toString();
        this.end = commodity.getEnd().toString();
    }

    public void print() {
        System.out.println("CommodityDetailVO:");
        System.out.println("commodityId: "+commodityId);
        System.out.println("name: "+name);
        System.out.println("price: "+price);
        System.out.println("type: "+type);
        System.out.println("description: "+description);
        System.out.println("quantity: "+quantity);
        System.out.println("start: " + start);
        System.out.println("end: "+end);
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
