package com.j2ee.server.dao;

import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.OrderState;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 订单模块
 * @Date: 2019/1/26
 */
public interface OrderDao extends CrudRepository<Orders, String>{
    List<Orders> findDistinctByCanteenIdAndState(String canteenId, OrderState state);

    List<Orders> findDistinctByClientNameAndState(String clientName, OrderState state);

    List<Orders> findDistinctByClientNameAndOrderTimeBetween(String clientName, LocalDateTime start, LocalDateTime end);

    List<Orders> findDistinctByClientName(String clientName);

    List<Orders> findDistinctByCanteenId(String canteenId);

    List<Orders> findAllByOrderTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Orders> findAllByIdBetween(String start, String end);
}
