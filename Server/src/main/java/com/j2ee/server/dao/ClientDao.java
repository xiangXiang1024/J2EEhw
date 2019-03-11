package com.j2ee.server.dao;

import com.j2ee.server.entity.Clients;
import com.j2ee.server.util.UserState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 客户模块
 * @Date: 2019/1/26
 */
public interface ClientDao extends CrudRepository<Clients, String> {
    boolean existsByNameAndState(String name, UserState state);

    List<Clients> findDistinctByState(UserState state);

    boolean existsByMail(String mail);

    Long countByState(UserState state);

    Long countByStateAndRanking(UserState state, int ranking);
}
