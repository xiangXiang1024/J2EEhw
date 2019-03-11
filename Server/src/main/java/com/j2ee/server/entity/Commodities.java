package com.j2ee.server.entity;

import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.GoodsType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 商品
 * @Date: 2019/1/26
 */
@Entity
public class Commodities extends Goods {
    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;

    public Commodities() {
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    public CommodityState getState() {
        return super.getState();
    }

    public void setState(CommodityState state) {
        super.setState(state);
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

    public void print() {
        System.out.println("Commodities:");
        if(this.getId() == null) {
            System.out.println("id = null");
        }else {
            System.out.println("id = " + this.getId());
        }
        if(getName() == null) {
            System.out.println("name = null");
        }else {
            System.out.println("name = " + getName());
        }
        if(this.getPrice() == null) {
            System.out.println("price = null");
        }else {
            System.out.println("price = "+this.getPrice());
        }
        if(getDescription() == null) {
            System.out.println("description = null");
        }else {
            System.out.println("description = "+getDescription());
        }
        if(this.getQuantity() == null) {
            System.out.println("quantity = null");
        }else {
            System.out.println("quantity = " + this.getQuantity());
        }
        if(getState() == null) {
            System.out.println("state = null");
        }else {
            System.out.println("state = "+getState());
        }
        if(this.getCanteen() == null) {
            System.out.println("canteen = null");
        }else {
            System.out.println("canteen = " + this.getCanteen().getId());
        }
        System.out.println("start: " + start);
        System.out.println("end: "+end);
    }
}
