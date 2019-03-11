package com.j2ee.server.service.admin;

import com.j2ee.server.dao.CanteenDao;
import com.j2ee.server.dao.OrderDao;
import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 每日2:30结算前一日收益
 *               餐厅:平台 7:3
 * @Date: 2019/3/4
 */
@Configuration // 主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 开启定时任务
public class IncomeCal {
    private CanteenDao canteenDao;
    private OrderDao orderDao;
    @Autowired
    public IncomeCal(CanteenDao canteenDao, OrderDao orderDao) {
        this.canteenDao = canteenDao;
        this.orderDao = orderDao;
    }

    @Scheduled(cron = "0 30 2 ? * *")
    public void calIncome() {
        LocalDate date = LocalDate.now().minusDays(1);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

//        System.out.println("year: "+year+"  month: "+month+"  day: "+day);

        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);

        List<Orders> orderList = orderDao.findAllByOrderTimeBetween(start, end);
        /*System.out.println("orders:");
        for(Orders o : orderList) {
            System.out.println(o.getId());
        }*/

        if(orderList != null) {
            for(Orders o : orderList) {
                Canteens canteen = o.getCanteen();
                if(o.getState() == OrderState.FINISHED) {
                    double money = o.getInitialPrice() * 0.7;
                    canteen.plusBalance(money);

//                    System.out.println("FINISHED订单");
//                    System.out.println("canteen: "+canteen.getId()+" money: "+money);

                    canteenDao.save(canteen);
                }
                if(o.getState() == OrderState.CANCELED) {
                    double money = o.getCancelFee() * 0.7;
                    canteen.plusBalance(money);

//                    System.out.println("CANCELED订单");
//                    System.out.println("canteen: "+canteen.getId()+" money: "+money);

                    canteenDao.save(canteen);
                }
            }
        }
    }
}
