package com.j2ee.server.dao;

import com.j2ee.server.entity.MonthClientStatistics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/3
 */
public interface MonthClientStatisticsDao extends CrudRepository<MonthClientStatistics, String> {

    List<MonthClientStatistics> findDistinctByIdBetween(String startId, String endId);
}
