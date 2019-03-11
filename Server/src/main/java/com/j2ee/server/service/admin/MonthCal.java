package com.j2ee.server.service.admin;

import com.j2ee.server.dao.*;
import com.j2ee.server.entity.MonthCanteenStatistics;
import com.j2ee.server.entity.MonthClientStatistics;
import com.j2ee.server.entity.MonthFinanceStatistics;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.util.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 每月1号统计上月的平台运营信息
 * @Date: 2019/3/4
 */
@Configuration // 主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 开启定时任务
public class MonthCal {
    private MonthClientStatisticsDao clientStatisticsDao;
    private MonthCanteenStatisticsDao canteenStatisticsDao;
    private MonthFinanceStatisticsDao financeStatisticsDao;
    private ClientDao clientDao;
    private CanteenDao canteenDao;
    private OrderDao orderDao;
    @Autowired
    public MonthCal(MonthClientStatisticsDao clientStatisticsDao, MonthCanteenStatisticsDao canteenStatisticsDao, MonthFinanceStatisticsDao financeStatisticsDao, ClientDao clientDao, CanteenDao canteenDao, OrderDao orderDao) {
        this.clientStatisticsDao = clientStatisticsDao;
        this.canteenStatisticsDao = canteenStatisticsDao;
        this.financeStatisticsDao = financeStatisticsDao;
        this.clientDao = clientDao;
        this.canteenDao = canteenDao;
        this.orderDao = orderDao;
    }

    // 会员情况 每月1日0点
    @Scheduled(cron = "0 0 0 1 * ?")
    public void calMonthClientStatistics() {
        Long clientNum = clientDao.countByState(UserState.ACTIVE);
        Long client0Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 0);
        Long client1Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 1);
        Long client2Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 2);
        Long client3Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 3);
        Long client4Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 4);
        Long client5Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 5);

        MonthClientStatistics clientStatistics = new MonthClientStatistics(getZero(clientNum), getZero(client0Num), getZero(client1Num), getZero(client2Num), getZero(client3Num), getZero(client4Num), getZero(client5Num));
        clientStatisticsDao.save(clientStatistics);
    }

    // 餐厅情况 每月1日1点
    @Scheduled(cron = "0 0 1 1 * ?")
    public void calMonthCanteenStatistics() {
        Long canteenNum = canteenDao.count();

        MonthCanteenStatistics canteenStatistics = new MonthCanteenStatistics(getZero(canteenNum));
        canteenStatisticsDao.save(canteenStatistics);
    }

    // 平台运营情况 每月1日2点
    @Scheduled(cron = "0 0 2 1 * ?")
    public void calMonthFinanceStatistics() {
        LocalDate endDate = LocalDate.now().minusDays(1);
        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        int endDay = endDate.getDayOfMonth();

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, endDay, 23, 59, 59);

        List<Orders> orderList = orderDao.findAllByOrderTimeBetween(start, end);
        Double totalSum = 0.0;      // 平台成交总额
        Double canteenSum = 0.0;    // 餐厅分成总额
        Double platSum = 0.0;       // 平台分成总额
        Integer orderSum = 0;       // 订单总数
        Integer cancelSum = 0;      // 退单总数
        Integer finishedSum = 0;    // 完成订单总数
        for(Orders o : orderList) {
            if(o.getState() == OrderState.FINISHED) {
                double initialPrice = o.getInitialPrice();
                double finalMoney = o.getFinalMoney();
                double canteenIncome = initialPrice * 0.7;
                double platIncome = finalMoney - canteenIncome;

                totalSum = totalSum + finalMoney;
                canteenSum = canteenSum + canteenIncome;
                platSum = platSum + platIncome;

                orderSum = orderSum + 1;
                finishedSum = finishedSum + 1;
            }else if(o.getState() == OrderState.CANCELED) {
                double cancelFee = o.getCancelFee();
                double canteenIncome = cancelFee * 0.7;
                double platIncome = cancelFee - canteenIncome;

                totalSum = totalSum + cancelFee;
                canteenSum = canteenSum + canteenIncome;
                platSum = platSum + platIncome;

                orderSum = orderSum + 1;
                cancelSum = cancelSum + 1;
            }
        }

        MonthFinanceStatistics financeStatistics = new MonthFinanceStatistics(totalSum, canteenSum, platSum, orderSum, cancelSum, finishedSum);
        financeStatisticsDao.save(financeStatistics);
    }

    private Long getZero(Long num) {
        if(num == null) {
            return (long)0;
        }else {
            return num;
        }
    }
}
