package com.j2ee.server.dao;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.ModifyCanteenInfo;
import com.j2ee.server.util.ApproveState;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/23
 */
public interface ModifyCanteenInfoDao extends CrudRepository<ModifyCanteenInfo, Long> {
    ModifyCanteenInfo findTopByIdAfterAndState(Long id, ApproveState state);

    ModifyCanteenInfo findTopByState(ApproveState state);

    boolean existsByCanteenAndState(Canteens canteen, ApproveState state);
}
