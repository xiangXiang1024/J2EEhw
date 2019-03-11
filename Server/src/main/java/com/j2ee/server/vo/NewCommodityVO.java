package com.j2ee.server.vo;

import com.j2ee.server.entity.Commodities;
import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.GoodsType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/26
 */
public class NewCommodityVO {
    private String canteenId;   // 商店编号
    private String name;        // 商品名称
    private double price;
    private String type;
    private String description;
    private int quantity;
    private String start;
    private String end;

    public NewCommodityVO() {
    }

    public NewCommodityVO(String canteenId, String name, double price, String type, String description, int quantity, String start, String end) {
        this.canteenId = canteenId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
    }

    public void print() {
        System.out.println("NewCommodityVO:");
        System.out.println("canteenId: "+canteenId);
        System.out.println("name: "+name);
        System.out.println("price: "+price);
        System.out.println("type: "+type);
        System.out.println("description: "+description);
        System.out.println("quantity: "+quantity);
        System.out.println("start: " + start);
        System.out.println("end: "+end);
    }

    public Commodities getCommodity() {
        Commodities commodity = new Commodities();
        commodity.setName(name);
        commodity.setGoodsType(GoodsType.getEnum(type));
        commodity.setQuantity(quantity);
        commodity.setPrice(price);
        commodity.setDescription(description);
        commodity.setState(CommodityState.INSTOCK);
        commodity.setStart(LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        commodity.setEnd(LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return commodity;
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
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
