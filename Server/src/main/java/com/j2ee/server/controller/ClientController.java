package com.j2ee.server.controller;

import com.j2ee.server.entity.Orders;
import com.j2ee.server.service.client.ClientService;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.AddressVO;
import com.j2ee.server.vo.ClientInfoVO;
import com.j2ee.server.vo.OrderDetailVO;
import com.j2ee.server.vo.OrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 会员模块
 * @Date: 2019/1/26
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    /**
     * 会员查看订单
     * @param clientName    会员昵称
     * @param state         订单状态
     * @return              订单概要
     */
    @GetMapping("/seeOrdersByState")
    List<OrderDetailVO> seeMyOrders(String clientName, OrderState state) {
        return clientService.seeOrders(clientName, state);
    }

    /**
     * 会员查看历史订单
     * @param name  会员昵称
     * @return      订单详情
     */
    @GetMapping("/seeHistoryOrders")
    List<OrderDetailVO> seeHistoryOrders(String name) {
        return clientService.seeHistoryOrders(name);
    }

    /**
     * 查看会员信息
     * @param name  会员昵称
     * @return      会员信息
     */
    @GetMapping("/seeClientInfo")
    public ClientInfoVO seeClientInfo(String name) {
        return clientService.seeClientInfo(name);
    }

    /**
     * 会员查看统计信息
     * @param client    会员
     * @param start     开始时间
     * @param end       结束时间
     * @param min       最低金额
     * @param max       最高金额
     * @param canteen   餐厅名
     * @return          订单详情
     */
    @GetMapping("/seeStatistics")
    public List<OrderDetailVO> seeStatistics(String client, String start, String end, Double min, Double max, String canteen) {
        /*System.out.println("in /client/seeStatistics");
        System.out.println("client: "+client+"\n"
                            +"start: "+start+"\n"
                            +"end: "+end+"\n"
                            +"min: "+min+"\n"
                            +"max: "+max+"\n"
                            +"canteen: "+canteen+"\n");*/

        List<OrderDetailVO> list = clientService.seeStatistics(client, start, end, min, max, canteen);

        return list;
    }

    /**
     * 获得会员的送餐地址
     * @param client    会员昵称
     * @return          送餐地址
     */
    @GetMapping("/getAddress")
    public List<AddressVO> getAddress(String client) {
        return clientService.getAddress(client);
    }
}
