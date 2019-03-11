package com.j2ee.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 平台每月的餐厅统计
 * @Date: 2019/3/3
 */
@Entity
public class MonthCanteenStatistics {
    @Id
    private String id;          // yyyy-mm   2018-01
    @NotNull
    private Long canteenNum; // 餐厅数量

    public MonthCanteenStatistics() {
    }

    public MonthCanteenStatistics(String id) {
        this.id = id;
        canteenNum = (long)0;
    }

    public MonthCanteenStatistics(@NotNull Long canteenNum) {
        LocalDate day = LocalDate.now().minusMonths(1);
        int year = day.getYear();
        int month = day.getMonthValue();
        if(month < 10) {
            this.id = year+"-0"+month;
        }else {
            this.id = year+"-"+month;
        }
        this.canteenNum = canteenNum;
    }

    public MonthCanteenStatistics(String id, @NotNull Long canteenNum) {
        this.id = id;
        this.canteenNum = canteenNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCanteenNum() {
        return canteenNum;
    }

    public void setCanteenNum(Long canteenNum) {
        this.canteenNum = canteenNum;
    }
}
