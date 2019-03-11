package com.j2ee.server.vo;

import com.j2ee.server.entity.OrderGoodsInfo;
import com.j2ee.server.entity.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/26
 */
public class OrderDetailVO {
    private String orderId; // 订单编号
    private List<String> goodsNames;    // 商品名称
    private List<Integer> goodsNums;    // 商品数量
    private double price;   // 价格
    private double finalPrice;
    private String address; // 配送地址
    private String note;    // 备注
    private String state;   // 状态
    private String client;  // 会员昵称
    private String canteen; // 餐厅名称

    public OrderDetailVO() {
    }

    public OrderDetailVO(Orders order) {
        this.orderId = order.getId();
        this.price = order.getInitialPrice();
        this.finalPrice = order.getFinalMoney();

        goodsNames = new ArrayList<>();
        goodsNums = new ArrayList<>();
        Set<OrderGoodsInfo> goodsInfoSet = order.getGoodsInfos();
        for(OrderGoodsInfo goodsInfo : goodsInfoSet) {
            goodsNames.add(goodsInfo.getGoods().getName());
            goodsNums.add(goodsInfo.getQuantity());
        }

        this.address = order.getDistrict().getStr()+"  "+order.getAddressDetail();
        this.note = order.getNote();
        this.state = order.getState().getStr();
        this.client = order.getClient().getName();
        this.canteen = order.getCanteen().getName();
    }

    public void print() {
        System.out.println("OrderDetailVO:");
        System.out.println("orderId: "+orderId);
        System.out.println("goodsNames: ");
        if(goodsNames == null) {
            System.out.println("null");
        }else {
            for(String name : goodsNames) {
                System.out.print(name+"  ");
            }
            System.out.println();
        }
        System.out.println("goodsNums: ");
        if(goodsNums == null) {
            System.out.println("null");
        }else {
            for(int num : goodsNums) {
                System.out.print(num+"  ");
            }
            System.out.println();
        }
        System.out.println("price: "+price);
        System.out.println("finalPrice: "+finalPrice);
        System.out.println("address: "+address);
        System.out.println("note: "+note);
        System.out.println("state: "+state);
        System.out.println("client: "+client);
        System.out.println("canteen: "+canteen);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getGoodsNames() {
        return goodsNames;
    }

    public void setGoodsNames(List<String> goodsNames) {
        this.goodsNames = goodsNames;
    }

    public List<Integer> getGoodsNums() {
        return goodsNums;
    }

    public void setGoodsNums(List<Integer> goodsNums) {
        this.goodsNums = goodsNums;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCanteen() {
        return canteen;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }
}
