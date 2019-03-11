package com.j2ee.server.service.client;

import com.j2ee.server.dao.ClientDao;
import com.j2ee.server.dao.OrderDao;
import com.j2ee.server.entity.Clients;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 每月1日3点进行用户上月的积分、等级计算
 * 积分规则：每完成1单：2分  每退订1单：-1分
 * 等级规则：0级：<10分  1级：10~99分  ... 最高5级
 * @Date: 2019/2/22
 */
@Configuration // 主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 开启定时任务
public class ClientGrading {
    private ClientDao clientDao;
    private OrderDao orderDao;
    @Autowired
    public ClientGrading(ClientDao clientDao, OrderDao orderDao) {
        this.clientDao = clientDao;
        this.orderDao = orderDao;
    }

    // 每月1日3点
    @Scheduled(cron = "0 0 3 1 * ?")
    public void calClientGrade() {
        List<Clients> clientList = clientDao.findDistinctByState(UserState.ACTIVE);

        LocalDate now = LocalDate.now().minusMonths(1);
        int year = now.getYear();
        int month = now.getMonthValue();
        int lastDay = now.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, lastDay, 23, 59, 59);

        for(Clients client : clientList) {
            List<Orders> orderList = orderDao.findDistinctByClientNameAndOrderTimeBetween(client.getName(), start, end);
            int gradeChange = 0;

            for(Orders order : orderList) {
                switch (order.getState()) {
                    case FINISHED:
                        gradeChange = gradeChange + 2;
                        break;
                    case CANCELED:
                        gradeChange = gradeChange - 1;
                       break;
                    default:
                        break;
                }
            }

            client.plusGrade(gradeChange);
            int grade = client.getGrade();
            int ranking = String.valueOf(grade).length() - 1;
            if(ranking > 5) {
                ranking = 5;
            }
            client.setRanking(ranking);
            clientDao.save(client);
        }
    }
}
