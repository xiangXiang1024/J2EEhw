package com.j2ee.server.service.admin;

import com.j2ee.server.entity.MonthCanteenStatistics;
import com.j2ee.server.entity.MonthClientStatistics;
import com.j2ee.server.entity.MonthFinanceStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AdminImplTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void getMonthClientStatistics() {
        List<MonthClientStatistics> list = adminService.getMonthClientStatistics(12);

        for(MonthClientStatistics statistics : list) {
            System.out.println(statistics.getId()+"  "+
                                + statistics.getClientNum()+"  "+
                    + statistics.getClient0Num()+"  "+
                    + statistics.getClient1Num()+"  "+
                    + statistics.getClient2Num()+"  "+
                    + statistics.getClient3Num()+"  "+
                    + statistics.getClient4Num()+"  "+
                    + statistics.getClient5Num()+"  ");
        }
    }

    @Test
    public void getMonthCanteenStatistics() {
        List<MonthCanteenStatistics> list = adminService.getMonthCanteenStatistics(12);

        System.out.println("canteenStatisticsList: ");
        for(MonthCanteenStatistics canteenStatistics : list) {
            System.out.println(canteenStatistics.getId()+"  "+canteenStatistics.getCanteenNum());
        }
    }

    @Test
    public void getMonthFinanceStatistics() {
        List<MonthFinanceStatistics> list = adminService.getMonthFinanceStatistics(12);

        for(MonthFinanceStatistics statistics : list) {
            System.out.println(statistics.getId()+"  "+
                    + statistics.getTotalSum()+"  "+
                    + statistics.getCanteenSum()+"  "+
                    + statistics.getPlatSum()+"  "+
                    + statistics.getOrderSum()+"  "+
                    + statistics.getFinishedSum()+"  "+
                    + statistics.getCancelSum());
        }
    }
}