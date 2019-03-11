package com.j2ee.server.dao;

import com.j2ee.server.entity.OrderGoodsInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/18
 */
public interface OrderGoodsInfoDao extends CrudRepository<OrderGoodsInfo, Long> {
    // 若数量为0，则返回null
    @Query(value = "select sum(info.quantity) from order_goods_info info, orders o " +
            "where o.id = info.orders_id " +
            "and info.goods_id = ?1 " +
            "and o.state <> 'CANCELED'" +
            "and o.order_time like CONCAT(?2,'%')"
            , nativeQuery=true)
    Integer getDailySaleSum(String goodsId, String date);
}
