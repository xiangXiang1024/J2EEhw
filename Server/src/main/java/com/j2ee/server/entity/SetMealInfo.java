package com.j2ee.server.entity;

import javax.persistence.*;

/**
 * @Author: 许杨
 * @Description: 套餐和商品的关系
 * @Date: 2019/1/27
 */
@Entity
public class SetMealInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setMealsId")
    private SetMeals setMeals;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commodityId")
    private Commodities commodities;
    private Integer quantity = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SetMeals getSetMeals() {
        return setMeals;
    }

    public void setSetMeals(SetMeals setMeals) {
        this.setMeals = setMeals;
    }

    public Commodities getCommodities() {
        return commodities;
    }

    public void setCommodities(Commodities commodities) {
        this.commodities = commodities;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SetMealInfo() {
    }

    public SetMealInfo(SetMeals setMeals, Commodities commodities, Integer quantity) {
        this.setMeals = setMeals;
        this.commodities = commodities;
        this.quantity = quantity;
    }

    public void print() {
        System.out.println("SetMealInfo:");
        if(setMeals == null) {
            System.out.println("setMeals = null");
        }else {
            System.out.println("setMeals = " + setMeals.getName());
        }
        if(commodities == null) {
            System.out.println("commodities = null");
        }else {
            System.out.println("commodities = " + commodities.getId());
        }
        if(quantity == null) {
            System.out.println("quantity = null");
        }else {
            System.out.println("quantity = " + quantity);
        }
    }
}
