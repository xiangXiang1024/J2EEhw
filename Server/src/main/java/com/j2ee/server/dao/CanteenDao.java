package com.j2ee.server.dao;

import com.j2ee.server.entity.Canteens;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description: 餐厅模块
 * @Date: 2019/1/26
 */
public interface CanteenDao extends CrudRepository<Canteens, String> {
}
