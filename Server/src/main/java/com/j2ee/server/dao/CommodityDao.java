package com.j2ee.server.dao;

import com.j2ee.server.entity.Commodities;
import com.j2ee.server.util.CommodityState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 商品
 * @Date: 2019/1/26
 */
public interface CommodityDao extends CrudRepository<Commodities, String> {
    long countByCanteenId(String canteenId);

    List<Commodities> findDistinctByCanteenIdAndState(String canteenId, CommodityState state);
}
