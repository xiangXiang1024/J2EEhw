package com.j2ee.server.service.client;

import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.AddressVO;
import com.j2ee.server.vo.ClientInfoVO;
import com.j2ee.server.vo.OrderDetailVO;
import com.j2ee.server.vo.OrderInfoVO;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 客户模块
 * @Date: 2019/1/26
 */
public interface ClientService {

    /**
     * 会员查看历史订单
     * @param clientName    会员昵称
     * @return              订单详情
     */
    List<OrderDetailVO> seeHistoryOrders(String clientName);

    /**
     * 查看订单概要
     * @param clientName    会员昵称
     * @param state         订单状态
     * @return              订单概要
     */
    List<OrderDetailVO> seeOrders(String clientName, OrderState state);

    /**
     * 查看会员信息
     * @param name  会员昵称
     * @return      会员信息
     */
    ClientInfoVO seeClientInfo(String name);

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
    List<OrderDetailVO> seeStatistics(String client, String start, String end, Double min, Double max, String canteen);

    /**
     * 获得会员的送餐地址
     * @param client    会员昵称
     * @return          送餐地址
     */
    List<AddressVO> getAddress(String client);
}
