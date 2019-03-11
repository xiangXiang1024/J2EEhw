package com.j2ee.server.dao;

import com.j2ee.server.entity.*;
import com.j2ee.server.util.OrderState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OrderDaoTest {
    @Autowired
    private OrderDao ordersDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private CanteenDao canteenDao;

    @Test
    public void save() {
        LocalDateTime time = LocalDateTime.now();
        OrderState state = OrderState.FINISHED;
        Clients client = clientDao.findById("client1").get();
        Canteens canteen = canteenDao.findById("canteen").get();
        Orders order = new Orders(state, time, canteen, client, null);
        ordersDao.save(order);
    }

    @Test
    public void findDistinctByClientNameAndStateAndOrderTimeBetween() {
        String clientName = "client1";

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int lastDay = now.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, lastDay, 23, 59, 59);

        System.out.println("start = "+start);
        System.out.println("end = "+end);

        List<Orders> ordersList = ordersDao.findDistinctByClientNameAndOrderTimeBetween(clientName, start, end);

        System.out.println("result:");
        if(ordersList == null || ordersList.size() == 0) {
            System.out.println("null");
        }else {
            for(Orders order : ordersList) {
                System.out.println(order.getId());
            }
        }
    }
}