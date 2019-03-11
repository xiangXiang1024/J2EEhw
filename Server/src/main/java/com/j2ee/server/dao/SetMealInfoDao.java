package com.j2ee.server.dao;

import com.j2ee.server.entity.SetMealInfo;
import com.j2ee.server.entity.SetMeals;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/1/28
 */
public interface SetMealInfoDao extends CrudRepository<SetMealInfo, Long> {
    List<SetMealInfo> findDistinctBySetMeals(SetMeals setMeals);
}
