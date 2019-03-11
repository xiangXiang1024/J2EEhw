package com.j2ee.server.vo;

import com.j2ee.server.entity.SetMeals;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/27
 */
public class SetMealDetailVO {
    private String name;                    // 名称
    private String description;             // 简介
    private List<String> goodsNames;            // 商品
    private List<Integer> goodsNums;                //
    private double price;                   // 价格
    private int quantity;                   // 每天限量
    private String start;                   // 开始时间
    private String end;                     // 结束时间


    public void print() {
        System.out.println("SetMealDetailVO");
        System.out.println("name: "+name);
        System.out.println("description: "+description);
        System.out.print("goodsNames: ");
        for(String name : goodsNames) {
            System.out.print(name+"  ");
        }
        System.out.println();
        System.out.println("goodsNums: ");
        for(int num : goodsNums) {
            System.out.print(num+"  ");
        }
        System.out.println();
        System.out.println("price: "+price);
        System.out.println("quantity: "+quantity);
        System.out.println("start: "+start);
        System.out.println("end: "+end);
    }

    public SetMealDetailVO() {
    }

    public SetMealDetailVO(String name, String description, List<String> goodsNames, List<Integer> goodsNums, Double price, Integer quantity, String start, String end) {
        this.name = name;
        this.description = description;
        this.goodsNames = goodsNames;
        this.goodsNums = goodsNums;
        this.price = price;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
    }

    public SetMealDetailVO(SetMeals setMeals, List<String> goodsNames, List<Integer> goodsNums) {
        this.name = setMeals.getName();
        this.description = setMeals.getDescription();
        this.price = setMeals.getPrice();
        this.quantity = setMeals.getQuantity();
        this.start = setMeals.getStart().toString();
        this.end = setMeals.getEnd().toString();
        this.goodsNames = goodsNames;
        this.goodsNums = goodsNums;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setStart(LocalDate start) {
        this.start = start.toString();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setEnd(LocalDate end) {
        this.end = end.toString();
    }
}
