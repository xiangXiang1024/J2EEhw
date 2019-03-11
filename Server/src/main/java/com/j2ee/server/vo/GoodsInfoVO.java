package com.j2ee.server.vo;

import com.j2ee.server.entity.Goods;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/2
 */
public class GoodsInfoVO {
    private String id;
    private String name;        // 商品名称
    private String goodsType;
    private int quantity;  // 每天限量(-1代表未设置)
    private double price;       // 商品价格
    private String state;
    private String description;

    public GoodsInfoVO() {
    }

    public GoodsInfoVO(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.goodsType = goods.getGoodsType().getStr();
        this.quantity = goods.getQuantity();
        this.price = goods.getPrice();
        this.state = goods.getState().getStr();
        String description = goods.getDescription();
        if(description == null) {
            this.description = "无";
        }else {
            this.description = description;
        }
    }

    public GoodsInfoVO(String id, String name, String goodsType, int quantity, double price, String state, String description) {
        this.id = id;
        this.name = name;
        this.goodsType = goodsType;
        this.quantity = quantity;
        this.price = price;
        this.state = state;
        this.description = description;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
