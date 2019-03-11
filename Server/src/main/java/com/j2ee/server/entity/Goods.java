package com.j2ee.server.entity;

import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.DoubleUtil;
import com.j2ee.server.util.GoodsType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: 许杨
 * @Description: 商品（commodity）、套餐（setmeal）、优惠（offer）的父类
 * @Date: 2019/2/16
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Goods {
    @Id
    private String id;
    @NonNull
    private String name;        // 商品名称
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "canteenId")
    private Canteens canteen;
    @Enumerated(EnumType.STRING)
    @NotNull
    private GoodsType goodsType;
    private Integer quantity = -1;  // 每天限量(-1代表未设置)
    @NonNull
    private Double price;       // 商品价格
    @NotNull
    @Enumerated(EnumType.STRING)
    private CommodityState state;
    private String description;

    public Goods() {
    }

    public Goods(String id, String name, Canteens canteen, @NotNull GoodsType goodsType, Integer quantity, Double price, @NotNull CommodityState state, String description) {
        this.id = id;
        this.name = name;
        this.canteen = canteen;
        this.goodsType = goodsType;
        this.quantity = quantity;
        this.price = DoubleUtil.format(price);
        this.state = state;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public Canteens getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteens canteen) {
        this.canteen = canteen;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = DoubleUtil.format(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommodityState getState() {
        return state;
    }

    public void setState(CommodityState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
