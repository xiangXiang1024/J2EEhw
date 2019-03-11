package com.j2ee.server.controller;

import com.j2ee.server.entity.MonthCanteenStatistics;
import com.j2ee.server.entity.MonthClientStatistics;
import com.j2ee.server.entity.MonthFinanceStatistics;
import com.j2ee.server.service.admin.AdminService;
import com.j2ee.server.util.ApproveState;
import com.j2ee.server.vo.ModifyInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 平台经理模块
 * @Date: 2019/3/3
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 获得第一条待审批的修改信息
     *
     * @return 第一条待审批的修改信息
     */
    @GetMapping("/getModifyInfo")
    public ModifyInfoVO getModifyInfo() {
        return adminService.getModifyInfo();
    }

    /**
     * 获得下一条待审批的修改信息
     *
     * @param id 当前信息编号
     * @return 下一条待审批的修改信息
     */
    @GetMapping("/getNextModifyInfo")
    public ModifyInfoVO getNextModifyInfo(Long id) {
        return adminService.getNextModifyInfo(id);
    }

    /**
     * 审批餐厅修改信息
     *
     * @param id      编号
     * @param result  结果
     * @param comment 备注
     */
    @GetMapping("/approveModifyInfo")
    public void approveModifyInfo(Long id, ApproveState result, String comment) {
        adminService.approveModifyInfo(id, result, comment);
        System.out.println("审批申请 " + id + " result： " + result + " comment： " + comment);
    }

    /**
     * 获得近几月的餐厅统计信息
     * @param month 月数
     * @return 餐厅统计信息
     */
    @GetMapping("/getMonthCanteenStatistics")
    public List<MonthCanteenStatistics> getMonthCanteenStatistics(int month) {
        return adminService.getMonthCanteenStatistics(month);
    }

    /**
     * 获得近几月的会员统计信息
     * @param month 月数
     * @return 会员统计信息
     */
    @GetMapping("/getMonthClientStatistics")
    public List<MonthClientStatistics> getMonthClientStatistics(int month) {
        return adminService.getMonthClientStatistics(month);
    }

    /**
     * 获得近几月的平台统计信息
     * @param month 月数
     * @return 平台统计信息
     */
    @GetMapping("/getMonthFinanceStatistics")
    public List<MonthFinanceStatistics> getMonthFinanceStatistics(int month) {
        return adminService.getMonthFinanceStatistics(month);
    }
}
