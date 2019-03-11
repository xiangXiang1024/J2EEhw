package com.j2ee.server.vo;

import java.util.ArrayList;

/**
 * @Author: 许杨
 * @Description: 会员下单
 * @Date: 2019/1/28
 */
public class OrderBriefVO {
    private String canteenId;   // 餐厅编号
    private String clientName;  // 会员姓名
    private ArrayList<GoodsBriefVO> goodsList = new ArrayList<>();
    // TODO 优惠

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<GoodsBriefVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<GoodsBriefVO> goodsList) {
        this.goodsList = goodsList;
    }
}
