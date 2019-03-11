package com.j2ee.server.service.order;

import com.j2ee.server.dao.OrderDao;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @Author: 许杨
 * @Description: 发货30分钟后自动确认收货
 * @Date: 2019/3/4
 */
@Configuration // 主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 开启定时任务
public class ReceiveOrders {
    private static ArrayList<String> orderList = new ArrayList<>();
    private OrderDao orderDao;
    @Autowired
    public ReceiveOrders(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // 每秒执行
    @Scheduled(fixedRate = 1*1000)
    public void receiveOrders() {
        LocalDateTime now = LocalDateTime.now();
//          System.out.println("now:"+now);

        int i = 0;
        while(i < orderList.size()) {
            String orderId = orderList.get(i);

//            System.out.println("checkOrder: "+orderId);

            Orders order = orderDao.findById(orderId).get();
            if(order.getState() == OrderState.HASDELIVERED) {
                LocalDateTime deliverTime = order.getDeliverTime();
                Duration duration = Duration.between(deliverTime, now);
//                System.out.println("order: "+orderId+" duration: "+duration.toMinutes());
                if(duration.toMinutes() >= 30) {
                    order.setState(OrderState.FINISHED);
                    order.setReceiveTime(now);
                    orderDao.save(order);
                    orderList.remove(i);
                    continue;
                }
            }
            i++;
        }
    }

    public static void addOrder(String orderId) {
        //System.out.println("add to cancel order list: ");
        //System.out.println("orderId: "+orderId);
        orderList.add(orderId);
    }
}
