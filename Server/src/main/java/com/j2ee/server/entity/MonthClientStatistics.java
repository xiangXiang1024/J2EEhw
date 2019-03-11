package com.j2ee.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 平台每月的会员统计
 * @Date: 2019/3/3
 */
@Entity
public class MonthClientStatistics {
    @Id
    private String id;          // yyyy-mm   2018-01
    // 会员统计
    @NotNull
    private Long clientNum;  // 会员总数
    @NotNull
    private Long client0Num; // 0级会员数量
    @NotNull
    private Long client1Num; // 1级会员数量
    @NotNull
    private Long client2Num; // 2级会员数量
    @NotNull
    private Long client3Num; // 3级会员数量
    @NotNull
    private Long client4Num; // 4级会员数量
    @NotNull
    private Long client5Num; // 5级会员数量

    public MonthClientStatistics() {
    }

    public MonthClientStatistics(@NotNull Long clientNum, @NotNull Long client0Num, @NotNull Long client1Num, @NotNull Long client2Num, @NotNull Long client3Num, @NotNull Long client4Num, @NotNull Long client5Num) {
        LocalDate day = LocalDate.now().minusMonths(1);
        int year = day.getYear();
        int month = day.getMonthValue();
        if(month < 10) {
            this.id = year+"-0"+month;
        }else {
            this.id = year+"-"+month;
        }
        this.clientNum = clientNum;
        this.client0Num = client0Num;
        this.client1Num = client1Num;
        this.client2Num = client2Num;
        this.client3Num = client3Num;
        this.client4Num = client4Num;
        this.client5Num = client5Num;
    }

    public MonthClientStatistics(String id) {
        this.id = id;
        this.clientNum = (long)0;
        this.client0Num = (long)0;
        this.client1Num = (long)0;
        this.client2Num = (long)0;
        this.client3Num = (long)0;
        this.client4Num = (long)0;
        this.client5Num = (long)0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getClientNum() {
        return clientNum;
    }

    public void setClientNum(Long clientNum) {
        this.clientNum = clientNum;
    }

    public Long getClient0Num() {
        return client0Num;
    }

    public void setClient0Num(Long client0Num) {
        this.client0Num = client0Num;
    }

    public Long getClient1Num() {
        return client1Num;
    }

    public void setClient1Num(Long client1Num) {
        this.client1Num = client1Num;
    }

    public Long getClient2Num() {
        return client2Num;
    }

    public void setClient2Num(Long client2Num) {
        this.client2Num = client2Num;
    }

    public Long getClient3Num() {
        return client3Num;
    }

    public void setClient3Num(Long client3Num) {
        this.client3Num = client3Num;
    }

    public Long getClient4Num() {
        return client4Num;
    }

    public void setClient4Num(Long client4Num) {
        this.client4Num = client4Num;
    }

    public Long getClient5Num() {
        return client5Num;
    }

    public void setClient5Num(Long client5Num) {
        this.client5Num = client5Num;
    }
}
