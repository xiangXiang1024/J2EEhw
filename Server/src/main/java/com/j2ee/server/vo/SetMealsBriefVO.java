package com.j2ee.server.vo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @Author: 许杨
 * @Description: 套餐(商家制定套餐时使用)
 * @Date: 2019/1/27
 */
public class SetMealsBriefVO {
    private String name;                    // 名称
    private String description;             // 简介
    private String canteenId;               // 餐厅
    private GoodsBriefVO[] goodsList;       // 商品
    private double price;                   // 价格
    private int quantity;               // 每天限量
    private String start;                // 开始时间
    private String end;                  // 结束时间

    public SetMealsBriefVO() {
    }

    public SetMealsBriefVO(String name, String description, String canteenId, GoodsBriefVO[] goodsList, double price, int quantity, String start, String end) {
        this.name = name;
        this.description = description;
        this.canteenId = canteenId;
        this.goodsList = goodsList;
        this.price = price;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public GoodsBriefVO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(GoodsBriefVO[] goodsList) {
        this.goodsList = goodsList;
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

    public void print() {
        System.out.println("SetMealsBriefVO:");
        if(name == null) {
            System.out.println("name = null");
        }else {
            System.out.println("name = " + name);
        }
        if(description == null) {
            System.out.println("description = null");
        }else {
            System.out.println("description = " + description);
        }
        if(canteenId == null) {
            System.out.println("canteenId = null");
        }else {
            System.out.println("canteenId = " + canteenId);
        }
        if(goodsList == null) {
            System.out.println("goodsList = null");
        }else {
            System.out.print("goodsList: id  quantity");
            for(GoodsBriefVO goods : goodsList) {
                System.out.println(goods.getGoodsId()+"  "+goods.getNum());
            }
            System.out.println();
        }
        System.out.println("price = " + price);
        System.out.println("quantity = " + quantity);
        if(start == null) {
            System.out.println("start = null");
        }else {
            System.out.println("start = " + start);
        }
        if(end == null) {
            System.out.println("end = null");
        }else {
            System.out.println("end = " + end);
        }
    }
}
