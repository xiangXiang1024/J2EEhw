package com.j2ee.server.dao;

import com.j2ee.server.entity.Goods;
import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.GoodsType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/16
 */
public interface GoodsDao extends CrudRepository<Goods, String> {
    long countByCanteenId(String canteenId);

    List<Goods> findDistinctByCanteenIdAndState(String canteen, CommodityState state);
}
