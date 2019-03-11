package com.j2ee.server.service.admin;

import com.j2ee.server.dao.*;
import com.j2ee.server.entity.*;
import com.j2ee.server.util.ApproveState;
import com.j2ee.server.util.DoubleUtil;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.util.UserState;
import com.j2ee.server.vo.ModifyInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/4
 */
@Service
public class AdminImpl implements AdminService {
    private ModifyCanteenInfoDao modifyCanteenInfoDao;
    private CanteenDao canteenDao;
    private MonthClientStatisticsDao clientStatisticsDao;
    private MonthCanteenStatisticsDao canteenStatisticsDao;
    private MonthFinanceStatisticsDao financeStatisticsDao;
    private ClientDao clientDao;
    private OrderDao orderDao;
    @Autowired
    public AdminImpl(ModifyCanteenInfoDao modifyCanteenInfoDao, CanteenDao canteenDao, MonthClientStatisticsDao clientStatisticsDao, MonthCanteenStatisticsDao canteenStatisticsDao, MonthFinanceStatisticsDao financeStatisticsDao, ClientDao clientDao, OrderDao orderDao) {
        this.modifyCanteenInfoDao = modifyCanteenInfoDao;
        this.canteenDao = canteenDao;
        this.clientStatisticsDao = clientStatisticsDao;
        this.canteenStatisticsDao = canteenStatisticsDao;
        this.financeStatisticsDao = financeStatisticsDao;
        this.clientDao = clientDao;
        this.orderDao = orderDao;
    }

    /**
     * 获得第一条待审批的修改信息
     * @return 第一条待审批的修改信息
     */
    @Override
    public ModifyInfoVO getModifyInfo() {
        ModifyCanteenInfo canteenInfo = modifyCanteenInfoDao.findTopByState(ApproveState.WAITING);
        if(canteenInfo == null) {
            return null;
        }else {
            return new ModifyInfoVO(canteenInfo);
        }
    }

    /**
     * 获得下一条待审批的修改信息
     * @param id 当前信息编号
     * @return 下一条待审批的修改信息
     */
    @Override
    public ModifyInfoVO getNextModifyInfo(Long id) {
        ModifyCanteenInfo canteenInfo = modifyCanteenInfoDao.findTopByIdAfterAndState(id, ApproveState.WAITING);
        if(canteenInfo == null) {
            return null;
        }else {
            return new ModifyInfoVO(canteenInfo);
        }
    }

    /**
     * 审批餐厅修改信息
     * @param id        编号
     * @param result    结果
     * @param comment   备注
     */
    @Override
    public void approveModifyInfo(Long id, ApproveState result, String comment) {
        if(!modifyCanteenInfoDao.existsById(id)) {
            return;
        }
        ModifyCanteenInfo modifyCanteenInfo = modifyCanteenInfoDao.findById(id).get();
        modifyCanteenInfo.setState(result);
        modifyCanteenInfo.setComment(comment);
        modifyCanteenInfoDao.save(modifyCanteenInfo);

        if(result == ApproveState.PASS) {
            Canteens canteen = modifyCanteenInfo.getCanteen();
            canteen.setName(modifyCanteenInfo.getName());
            canteen.setType(modifyCanteenInfo.getType());
            canteen.setDistrict(modifyCanteenInfo.getDistrict());
            canteen.setAddress(modifyCanteenInfo.getAddressDetail());
            canteenDao.save(canteen);
        }
    }

    /**
     * 获得近几月的会员统计信息
     * @param month 月数
     * @return 会员统计信息
     */
    @Override
    public List<MonthClientStatistics> getMonthClientStatistics(int month) {
        if(month <= 0) {
            return new ArrayList<>();
        }

        String startId = getStartId(month);
        String endId = getEndId();

        List<MonthClientStatistics> clientStatisticsList = clientStatisticsDao.findDistinctByIdBetween(startId, endId);
        List<MonthClientStatistics> clientStatisticsList1 = new ArrayList<>();
        LocalDate date = LocalDate.now().minusMonths(month-1);
        while(clientStatisticsList.size()+clientStatisticsList1.size() < month-1) {
            String id = date.toString().substring(0, 7);
            MonthClientStatistics clientStatistics = new MonthClientStatistics(id);
            clientStatisticsList1.add(clientStatistics);
            date = date.plusMonths(1);
        }
        clientStatisticsList1.addAll(clientStatisticsList);
        clientStatisticsList1.add(getThisMonthClientStatistics());
        return clientStatisticsList1;
    }
    // 获得结束的id
    private String getEndId() {
        LocalDate now = LocalDate.now();
        return now.toString().substring(0, 7);
    }
    // 获得开始的id
    private String getStartId(int month) {
        LocalDate time = LocalDate.now().minusMonths(month-1);
        return time.toString().substring(0, 7);
    }
    // 得到本月数据
    private MonthClientStatistics getThisMonthClientStatistics(){
        Long clientNum = clientDao.countByState(UserState.ACTIVE);
        Long client0Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 0);
        Long client1Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 1);
        Long client2Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 2);
        Long client3Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 3);
        Long client4Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 4);
        Long client5Num = clientDao.countByStateAndRanking(UserState.ACTIVE, 5);

        return new MonthClientStatistics(getZero(clientNum), getZero(client0Num), getZero(client1Num), getZero(client2Num), getZero(client3Num), getZero(client4Num), getZero(client5Num));
    }
    private Long getZero(Long num) {
        if(num == null) {
            return (long)0;
        }else {
            return num;
        }
    }

    /**
     * 获得近几月的餐厅统计信息
     * @param month 月数
     * @return 餐厅统计信息
     */
    @Override
    public List<MonthCanteenStatistics> getMonthCanteenStatistics(int month) {
        String startId = getStartId(month);
        String endId = getEndId();

        List<MonthCanteenStatistics> canteenStatisticsList = canteenStatisticsDao.findDistinctByIdBetween(startId, endId);

       /* System.out.println("canteenStatisticsList: ");
        for(MonthCanteenStatistics canteenStatistics : canteenStatisticsList) {
            System.out.println(canteenStatistics.getId()+"  "+canteenStatistics.getCanteenNum());
        }*/

        List<MonthCanteenStatistics> canteenStatisticsList1 = new ArrayList<>();
        LocalDate date = LocalDate.now().minusMonths(month-1);
        while(canteenStatisticsList.size()+canteenStatisticsList1.size() < month-1) {
            String id = date.toString().substring(0, 7);
            MonthCanteenStatistics canteenStatistics = new MonthCanteenStatistics(id);
            canteenStatisticsList1.add(canteenStatistics);
            date = date.plusMonths(1);
        }

       /* System.out.println("canteenStatisticsList1: ");
        for(MonthCanteenStatistics canteenStatistics : canteenStatisticsList1) {
            System.out.println(canteenStatistics.getId()+"  "+canteenStatistics.getCanteenNum());
        }*/

        canteenStatisticsList1.addAll(canteenStatisticsList);
        canteenStatisticsList1.add(getThisMonthCanteenStatistics());

        /*System.out.println("canteenStatisticsList1: ");
        for(MonthCanteenStatistics canteenStatistics : canteenStatisticsList1) {
            System.out.println(canteenStatistics.getId()+"  "+canteenStatistics.getCanteenNum());
        }*/

        return canteenStatisticsList1;

    }
    // 得到本月数据
    private MonthCanteenStatistics getThisMonthCanteenStatistics(){
        Long canteenNum = canteenDao.count();

        return new MonthCanteenStatistics(getZero(canteenNum));
    }

    /**
     * 获得近几月的平台统计信息
     * @param month 月数
     * @return 平台统计信息
     */
    @Override
    public List<MonthFinanceStatistics> getMonthFinanceStatistics(int month) {
        String startId = getStartId(month);
        String endId = getEndId();

        List<MonthFinanceStatistics> financeStatisticsList = financeStatisticsDao.findDistinctByIdBetween(startId, endId);
        List<MonthFinanceStatistics> financeStatisticsList1 = new ArrayList<>();
        LocalDate date = LocalDate.now().minusMonths(month-1);
        while(financeStatisticsList.size()+financeStatisticsList1.size() < month-1) {
            String id = date.toString().substring(0, 7);
            MonthFinanceStatistics financeStatistics = new MonthFinanceStatistics(id);
            financeStatisticsList1.add(financeStatistics);
            date.plusMonths(1);
        }
        financeStatisticsList1.addAll(financeStatisticsList);
        financeStatisticsList1.add(getThisMonthFinanceStatistics());
        return financeStatisticsList1;
    }
    // 得到本月数据
    private MonthFinanceStatistics getThisMonthFinanceStatistics(){
        LocalDate endDate = LocalDate.now();
        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        int endDay = endDate.getDayOfMonth();

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, endDay, 23, 59, 59);

        List<Orders> orderList = orderDao.findAllByOrderTimeBetween(start, end);
        Double totalSum = 0.0;      // 平台成交总额
        Double canteenSum = 0.0;    // 餐厅分成总额
        Double platSum = 0.0;       // 平台分成总额
        Integer orderSum = 0;       // 订单总数
        Integer cancelSum = 0;      // 退单总数
        Integer finishedSum = 0;    // 完成订单总数
        for(Orders o : orderList) {
            if(o.getState() == OrderState.FINISHED) {
                double initialPrice = o.getInitialPrice();
                double finalMoney = o.getFinalMoney();
                double canteenIncome = initialPrice * 0.7;
                double platIncome = finalMoney - canteenIncome;

                totalSum = totalSum + finalMoney;
                canteenSum = canteenSum + canteenIncome;
                platSum = platSum + platIncome;

                orderSum = orderSum + 1;
                finishedSum = finishedSum + 1;
            }else if(o.getState() == OrderState.CANCELED) {
                double cancelFee = o.getCancelFee();
                double canteenIncome = cancelFee * 0.7;
                double platIncome = cancelFee - canteenIncome;

                totalSum = totalSum + cancelFee;
                canteenSum = canteenSum + canteenIncome;
                platSum = platSum + platIncome;

                orderSum = orderSum + 1;
                cancelSum = cancelSum + 1;
            }
        }

        return new MonthFinanceStatistics(totalSum, canteenSum, platSum, orderSum, cancelSum, finishedSum);
    }
}
