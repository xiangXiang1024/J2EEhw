package com.j2ee.server.entity;

import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.GoodsType;
import com.j2ee.server.vo.SetMealsBriefVO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 许杨
 * @Description: 套餐
 * @Date: 2019/1/26
 */
@Entity
public class SetMeals extends Goods {
    private LocalDate start;
    private LocalDate end;

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

    public CommodityState getState() {
        return super.getState();
    }

    public void setState(CommodityState state) {
        super.setState(state);
    }


    public SetMeals() {
        super();
        this.setGoodsType(GoodsType.setmeal);
    }

    public SetMeals(String id, Canteens canteen, SetMealsBriefVO setMealsBriefVO) {
        this.setGoodsType(GoodsType.setmeal);
        this.setId(id);
        this.setCanteen(canteen);
        setName(setMealsBriefVO.getName());
        setDescription(setMealsBriefVO.getDescription());
        this.setPrice(setMealsBriefVO.getPrice());
        this.setQuantity(setMealsBriefVO.getQuantity());
        this.start = LocalDate.parse(setMealsBriefVO.getStart());
        this.end = LocalDate.parse(setMealsBriefVO.getEnd());
        LocalDate start = LocalDate.parse(setMealsBriefVO.getStart());
        if(start.isAfter(LocalDate.now())) {
            setState(CommodityState.NOTONSHELF);
        }else {
            setState(CommodityState.INSTOCK);
        }
    }

    public void print() {
        System.out.println("SetMeals:");
        if(this.getId() == null) {
            System.out.println("id = null");
        }else {
            System.out.println("id = " + this.getId());
        }
        if(getName() == null) {
            System.out.println("name = null");
        }else {
            System.out.println("name = "+getName());
        }
        if(getDescription() == null) {
            System.out.println("description = null");
        }else {
            System.out.println("description = "+getDescription());
        }
        if(this.getPrice() == null) {
            System.out.println("price = null");
        }else {
            System.out.println("price = "+this.getPrice());
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
        if(this.getQuantity() == null) {
            System.out.println("quantity = null");
        }else {
            System.out.println("quantity = "+this.getQuantity());
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
    }
}
