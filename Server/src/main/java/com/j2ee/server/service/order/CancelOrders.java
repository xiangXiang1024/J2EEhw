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
 * @Description: 每分钟检查一次，取消超时订单
 * @Date: 2019/2/23
 */
@Configuration // 主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 开启定时任务
public class CancelOrders {
    private static ArrayList<String> orderList = new ArrayList<>();
    private OrderDao orderDao;
    @Autowired
    public CancelOrders(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // 每秒执行
    @Scheduled(fixedRate = 1*1000)
    public void checkOrders() {
     //   System.out.println("checkOrder");

        LocalDateTime now = LocalDateTime.now();
      //  System.out.println("now:"+now);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int i = 0;
        while(i < orderList.size()) {
            String orderId = orderList.get(i);
          //  System.out.println("order:"+orderId);
            String orderTimeStr = orderId.substring(0, 19);
            orderTimeStr = orderTimeStr.replace('T', ' ');
          //  System.out.println("orderTimeStr:"+orderTimeStr);
            LocalDateTime orderTime = LocalDateTime.parse(orderTimeStr, df);
            Duration duration = Duration.between(orderTime, now);
           // System.out.println("duration:"+duration);
            if(duration.toMinutes() >= 2) {
                //System.out.println("set ");
                Orders order = orderDao.findById(orderId).get();
                if(order.getState() == OrderState.WAITINGFORORDER) {
                    order.setState(OrderState.CANCELED);
                    orderDao.save(order);
                    orderList.remove(i);
                    continue;
                }else {
                    i++;
                }
            }else {
                break;
            }
        }
    }

    public static void addOrder(String orderId) {
        //System.out.println("add to cancel order list: ");
        //System.out.println("orderId: "+orderId);
        orderList.add(orderId);
    }
}
