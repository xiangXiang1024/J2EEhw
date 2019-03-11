package com.j2ee.server.dao;

import com.j2ee.server.entity.MonthFinanceStatistics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/4
 */
public interface MonthFinanceStatisticsDao extends CrudRepository<MonthFinanceStatistics, String> {
    List<MonthFinanceStatistics> findDistinctByIdBetween(String startId, String endId);
}
