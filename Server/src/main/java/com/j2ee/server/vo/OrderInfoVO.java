package com.j2ee.server.vo;

import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.OrderState;

import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 会员查看订单的简略信息
 * @Date: 2019/2/23
 */
public class OrderInfoVO {
    private String id;                  // 订单编号
    private OrderState state;           // 状态
    private LocalDateTime orderTime;    // 下单时间
    private double finalPrice;          // 费用
    private String canteenId;           // 餐厅编号
    private String canteenName;         // 餐厅名称

    public OrderInfoVO() {
    }

    public OrderInfoVO(Orders order) {
        this.id = order.getId();
        this.state = order.getState();
        this.orderTime = order.getOrderTime();
        this.finalPrice = order.getFinalMoney();
        this.canteenId = order.getCanteen().getId();
        this.canteenName = order.getCanteen().getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }
}
