package com.j2ee.server.entity;

import com.j2ee.server.util.DoubleUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 平台每月的财务统计
 * @Date: 2019/3/3
 */
@Entity
public class MonthFinanceStatistics {
    @Id
    private String id;          // yyyy-mm   2018-01
    @NotNull
    private Double totalSum;    // 平台成交总额
    @NotNull
    private Double canteenSum;  // 餐厅分成总额
    @NotNull
    private Double platSum;     // 平台分成总额
    @NotNull
    private Integer orderSum;   // 订单总数
    @NotNull
    private Integer cancelSum;  // 退单总数
    @NotNull
    private Integer finishedSum;// 完成订单总数

    public MonthFinanceStatistics() {
    }

    public MonthFinanceStatistics(String id) {
        this.id = id;
        totalSum = 0.0;
        canteenSum = 0.0;
        platSum = 0.0;
        orderSum = 0;
        cancelSum = 0;
        finishedSum = 0;
    }

    public MonthFinanceStatistics(@NotNull Double totalSum, @NotNull Double canteenSum, @NotNull Double platSum, @NotNull Integer orderSum, @NotNull Integer cancelSum, @NotNull Integer finishedSum) {
        LocalDate day = LocalDate.now().minusMonths(1);
        int year = day.getYear();
        int month = day.getMonthValue();
        if(month < 10) {
            this.id = year+"-0"+month;
        }else {
            this.id = year+"-"+month;
        }
        this.totalSum = DoubleUtil.format(totalSum);
        this.canteenSum = DoubleUtil.format(canteenSum);
        this.platSum = DoubleUtil.format(platSum);
        this.orderSum = orderSum;
        this.cancelSum = cancelSum;
        this.finishedSum = finishedSum;
    }

    public MonthFinanceStatistics(String id, @NotNull Double totalSum, @NotNull Double canteenSum, @NotNull Double platSum, @NotNull Integer orderSum, @NotNull Integer cancelSum, @NotNull Integer finishedSum) {
        this.id = id;
        this.totalSum = DoubleUtil.format(totalSum);
        this.canteenSum = DoubleUtil.format(canteenSum);
        this.platSum = DoubleUtil.format(platSum);
        this.orderSum = orderSum;
        this.cancelSum = cancelSum;
        this.finishedSum = finishedSum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = DoubleUtil.format(totalSum);
    }

    public Double getCanteenSum() {
        return canteenSum;
    }

    public void setCanteenSum(Double canteenSum) {
        this.canteenSum = DoubleUtil.format(canteenSum);
    }

    public Double getPlatSum() {
        return platSum;
    }

    public void setPlatSum(Double platSum) {
        this.platSum = DoubleUtil.format(platSum);
    }

    public Integer getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Integer orderSum) {
        this.orderSum = orderSum;
    }

    public Integer getCancelSum() {
        return cancelSum;
    }

    public void setCancelSum(Integer cancelSum) {
        this.cancelSum = cancelSum;
    }

    public Integer getFinishedSum() {
        return finishedSum;
    }

    public void setFinishedSum(Integer finishedSum) {
        this.finishedSum = finishedSum;
    }
}
