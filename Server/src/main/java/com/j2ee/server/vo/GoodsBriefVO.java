package com.j2ee.server.vo;

/**
 * @Author: 许杨
 * @Description: 订单、套餐中商品及数量
 * @Date: 2019/1/28
 */
public class GoodsBriefVO {
    private String goodsId;
    private int num = 1;

    public GoodsBriefVO() {
    }

    public GoodsBriefVO(String goodsId, int num) {
        this.goodsId = goodsId;
        this.num = num;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
