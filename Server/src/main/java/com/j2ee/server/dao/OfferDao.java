package com.j2ee.server.dao;

import com.j2ee.server.entity.Offers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 优惠
 * @Date: 2019/1/26
 */
public interface OfferDao extends CrudRepository<Offers, String>{
    long countByCanteenId(String canteenId);

    List<Offers> findDistinctByCanteenId(String canteenId);
}
