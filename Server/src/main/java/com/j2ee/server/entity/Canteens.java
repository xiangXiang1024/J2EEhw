package com.j2ee.server.entity;

import com.j2ee.server.util.CanteenType;
import com.j2ee.server.util.District;
import com.j2ee.server.util.DoubleUtil;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @Author: 许杨
 * @Description: 餐厅表
 * @Date: 2019/1/26
 */
@Entity
public class Canteens {
    @Id
    private String id;
    @NotNull
    private String name;            // 名称
    @NotNull
    private String password;        // 密码
    @NotNull
    @Enumerated(EnumType.STRING)
    private District district;  // 所在区
    private String address;     // 地址
    private Double shippingFee = 0.0;   // 配送费
    private Double packagingFee = 0.0;  // 包装费
    private Double balance = 0.0;       // 餐厅余额
    @NotNull
    @Enumerated(EnumType.STRING)
    private CanteenType type;  // 所在区

    public Canteens() {
    }

    public Canteens(String id, @NotNull String name, @NotNull String password, @NotNull District district, String address, Double shippingFee, Double packagingFee, @NotNull CanteenType type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.district = district;
        this.address = address;
        this.shippingFee = DoubleUtil.format(shippingFee);
        this.packagingFee = DoubleUtil.format(packagingFee);
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = DoubleUtil.format(shippingFee);
    }

    public Double getPackagingFee() {
        return packagingFee;
    }

    public void setPackagingFee(Double packagingFee) {
        this.packagingFee = DoubleUtil.format(packagingFee);
    }

    public CanteenType getType() {
        return type;
    }

    public void setType(CanteenType type) {
        this.type = type;
    }

    public void print() {
        System.out.println("canteen:");
        System.out.println("id = "+id);
        System.out.println("name = "+name);
        System.out.println("password = "+password);
        System.out.println("district = "+district);
        System.out.println("address = "+address);
        System.out.println("shippingFee = "+shippingFee);
        System.out.println("packagingFee = "+packagingFee);
        System.out.println("type = "+type);
        System.out.println("balance = "+balance);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void plusBalance(double balance) {
        this.balance = this.balance + balance;
    }
}
