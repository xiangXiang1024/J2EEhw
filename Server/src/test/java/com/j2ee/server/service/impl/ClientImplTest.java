package com.j2ee.server.service.impl;

import com.j2ee.server.service.client.ClientService;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.ClientInfoVO;
import com.j2ee.server.vo.OrderDetailVO;
import com.j2ee.server.vo.OrderInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ClientImplTest {
    @Autowired
    private ClientService clientService;

    @Test
    public void seeOrders() {
      /*  String clientName = "client1";
        List<OrderInfoVO> orderInfoVOList = clientService.seeOrders(clientName);

        System.out.println("result:");
        if(orderInfoVOList == null || orderInfoVOList.size() == 0) {
            System.out.println("null");
        }else {
            for(OrderInfoVO info : orderInfoVOList) {
                System.out.println(info.getId()+"  "
                                    + info.getState()+"  "
                                    + info.getOrderTime()+"  "
                                    + info.getFinalPrice()+"  "
                                    + info.getCanteenId());
            }
        }*/
    }

    @Test
    public void seeOrders1() {
        String clientName = "client1";
        OrderState state = OrderState.CANCELED;
        List<OrderDetailVO> orderInfoVOList = clientService.seeOrders(clientName, state);

        /*System.out.println("result:");
        if(orderInfoVOList == null || orderInfoVOList.size() == 0) {
            System.out.println("null");
        }else {
            for(OrderDetailVO info : orderInfoVOList) {
                System.out.println(info.getId()+"  "
                        + info.getState()+"  "
                        + info.getOrderTime()+"  "
                        + info.getFinalPrice()+"  "
                        + info.getCanteenId());
            }
        }*/
    }

    @Test
    public void seeClientInfo() {
        String name = "client1";
        ClientInfoVO vo = clientService.seeClientInfo(name);

        vo.print();
    }

    @Test
    public void seeStatistics() {
        String client = "client1";
        String start = LocalDate.now().minusMonths(2).toString();
        String end = LocalDate.now().toString();
        Double min = 0.0;
        Double max = 100.1;
        String canteen = "餐厅";

        System.out.println("start: "+start+"\n"+
                "end: "+end+"\n"+
                "min: "+min+"\n"+
                "max: "+max+"\n"+
                "canteen: "+canteen+"\n");

        List<OrderDetailVO> voList = clientService.seeStatistics(client, start, end, min, max, canteen);

        for(OrderDetailVO vo : voList) {
            vo.print();
        }
    }
}