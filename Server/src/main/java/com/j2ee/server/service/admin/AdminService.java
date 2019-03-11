package com.j2ee.server.service.admin;

import com.j2ee.server.entity.MonthCanteenStatistics;
import com.j2ee.server.entity.MonthClientStatistics;
import com.j2ee.server.entity.MonthFinanceStatistics;
import com.j2ee.server.util.ApproveState;
import com.j2ee.server.vo.ModifyInfoVO;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/4
 */
public interface AdminService {
    /**
     * 获得第一条待审批的修改信息
     * @return  第一条待审批的修改信息
     */
    ModifyInfoVO getModifyInfo();

    /**
     * 获得下一条待审批的修改信息
     * @param id    当前信息编号
     * @return      下一条待审批的修改信息
     */
    ModifyInfoVO getNextModifyInfo(Long id);

    /**
     * 审批餐厅修改信息
     * @param id        编号
     * @param result    结果
     * @param comment   备注
     */
    void approveModifyInfo(Long id, ApproveState result, String comment);

    /**
     * 获得近几月的会员统计信息
     * @param month 月数
     * @return      会员统计信息
     */
    List<MonthClientStatistics> getMonthClientStatistics(int month);

    /**
     * 获得近几月的餐厅统计信息
     * @param month 月数
     * @return      餐厅统计信息
     */
    List<MonthCanteenStatistics> getMonthCanteenStatistics(int month);

    /**
     * 获得近几月的平台统计信息
     * @param month 月数
     * @return      平台统计信息
     */
    List<MonthFinanceStatistics> getMonthFinanceStatistics(int month);
}
