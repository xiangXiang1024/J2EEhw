package com.j2ee.server.dao;

import com.j2ee.server.entity.SetMeals;
import com.j2ee.server.util.CommodityState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/1/26
 */
public interface SetMealDao extends CrudRepository<SetMeals, String> {
    Long countByCanteenId(String canteenId);

    List<SetMeals> findDistinctByCanteenIdAndState(String canteenId, CommodityState state);
}
