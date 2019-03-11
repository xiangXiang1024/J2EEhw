package com.j2ee.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: 许杨
 * @Description: 订单和商品间的关系
 * @Date: 2019/2/17
 */
@Entity
public class OrderGoodsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goodsId")
    private Goods goods;            // 商品
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ordersId")
    private Orders order;           // 订单
    @NotNull
    private Integer quantity = 1;   // 商品数量

    public OrderGoodsInfo() {
    }

    public OrderGoodsInfo(Goods goods, Orders order, Integer quantity) {
        this.goods = goods;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
