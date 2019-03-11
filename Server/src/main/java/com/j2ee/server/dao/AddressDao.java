package com.j2ee.server.dao;

import org.springframework.data.repository.CrudRepository;
import com.j2ee.server.entity.Address;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/22
 */
public interface AddressDao extends CrudRepository<Address, Long> {
    List<Address> findDistinctByClientName(String name);
}
