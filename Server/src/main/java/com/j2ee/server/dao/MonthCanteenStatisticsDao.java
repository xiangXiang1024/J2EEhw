package com.j2ee.server.dao;

import com.j2ee.server.entity.MonthCanteenStatistics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/4
 */
public interface MonthCanteenStatisticsDao extends CrudRepository<MonthCanteenStatistics, String> {
    List<MonthCanteenStatistics> findDistinctByIdBetween(String startId, String endId);
}
