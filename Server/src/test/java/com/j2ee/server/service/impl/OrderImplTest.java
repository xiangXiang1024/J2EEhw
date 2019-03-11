package com.j2ee.server.service.impl;

import com.j2ee.server.service.order.OrderService;
import com.j2ee.server.util.AddOrderResult;
import com.j2ee.server.util.CancelOrderResult;
import com.j2ee.server.util.District;
import com.j2ee.server.vo.GoodsBriefVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Timer;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/1/28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OrderImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void addOrder() {
        String clientName = "client1";
        String canteenId = "canteen";
        GoodsBriefVO goodsBriefVO1 = new GoodsBriefVO("Ccanteen0", 1);
        GoodsBriefVO goodsBriefVO2 = new GoodsBriefVO("Scanteen0", 1);
        ArrayList<GoodsBriefVO> goodsList = new ArrayList<>();
        goodsList.add(goodsBriefVO1);   goodsList.add(goodsBriefVO2);
        String offerId = "1";
        District district = District.GULOU;
        String address = "南京大学鼓楼区陶园南楼202";
        AddOrderResult result = orderService.addOrder(clientName, canteenId, goodsList, offerId, district, address, null);
        System.out.println("result = " + result);


        try {
            Thread.sleep((long)3*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test end");
    }

    @Test
    public void payOrder() {
        String orderId = "2019-02-19T09:37:48.855-9";
        boolean result = orderService.payOrder(orderId);
        System.out.println("result = "+result);
    }

    @Test
    public void acceptOrder() {
        String orderId = "2019-02-19T09:37:48.855-9";
        boolean result = orderService.acceptOrder(orderId);
        System.out.println("result = "+result);
    }

    @Test
    public void deliverOrder() {
        String orderId = "2019-02-19T09:37:48.855-9";
        boolean result = orderService.deliverOrder(orderId);
        System.out.println("result = "+result);
    }

    @Test
    public void receiveOrder() {
        String orderId = "2019-02-19T09:37:48.855-9";
        boolean result = orderService.receiveOrder(orderId);
        System.out.println("result = "+result);
    }

    @Test
    public void cancelOrder() {
        String orderId = "2019-02-19T09:37:48.855-9";
        CancelOrderResult result = orderService.cancelOrder(orderId);
        System.out.println("result: "+result);
    }

    @Test
    public void commentOrder() {
        String orderId = "2019-02-19T09:37:48.855-9";
        String comment = "test comment function";
        boolean result = orderService.commentOrder(orderId, comment);
        System.out.println("result = "+result);
    }
}