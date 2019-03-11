package com.j2ee.server.entity;

import com.j2ee.server.util.DoubleUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 优惠
 * @Date: 2019/1/26
 */
@Entity
public class Offers {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canteenId")
    private Canteens canteen;
    @NotNull
    private LocalDate start;            // 开始时间
    private LocalDate end;              // 结束时间
    private Double base = 0.0;          // 满...优惠
    @NotNull
    private Double discount = 10.0;     // 减
    private String description ;        // 描述

    public Offers() {
    }

    public Offers(String start, String end, double base, double discount) {
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
        this.base = DoubleUtil.format(base);
        this.discount = DoubleUtil.format(discount);
        this.description = "满"+base+"元减"+discount+"元";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Canteens getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteens canteen) {
        this.canteen = canteen;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = DoubleUtil.format(base);
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = DoubleUtil.format(discount);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void print() {
        System.out.println("Offers:");
        if(this.getId() == null) {
            System.out.println("id = null");
        }else {
            System.out.println("id = " + this.getId());
        }
        if(start == null) {
            System.out.println("start = null");
        }else {
            System.out.println("start = "+start);
        }
        if(end == null) {
            System.out.println("end = null");
        }else {
            System.out.println("end = "+end);
        }
        if(base == null) {
            System.out.println("base = null");
        }else {
            System.out.println("base = "+base);
        }
        if(discount == null) {
            System.out.println("discount = null");
        }else {
            System.out.println("discount = "+discount+"折");
        }
        if(description == null) {
            System.out.println("description = null");
        }else {
            System.out.println("description = "+description);
        }
        if(this.getCanteen() == null) {
            System.out.println("canteen = null");
        }else {
            System.out.println("canteen = " + this.getCanteen().getId());
        }
    }
}
