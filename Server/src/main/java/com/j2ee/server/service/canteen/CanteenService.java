package com.j2ee.server.service.canteen;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Commodities;
import com.j2ee.server.entity.Offers;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.util.AddResult;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.*;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 餐厅模块
 * @Date: 2019/1/26
 */
public interface CanteenService {
    /**
     * 添加商品
     * @param canteenId 餐厅编号
     * @param commodities 商品
     * @return 添加是否成功
     */
    AddResult addGoods(String canteenId, Commodities commodities);

    /**
     * 添加优惠
     * @param canteenId 餐厅编号
     * @param offer 优惠
     * @return 添加是否成功
     */
    AddResult addOffer(String canteenId, Offers offer);

    /**
     * 添加套餐
     * @param setMealsBriefVO  套餐
     * @return 添加是否成功
     */
    AddResult addSetMeal(SetMealsBriefVO setMealsBriefVO);

    /**
     * 餐厅查看订单
     * @param canteenId 餐厅编号
     * @param state     订单状态
     * @return          订单详情
     */
    List<Orders> seeOrders(String canteenId, OrderState state);

    /**
     * 查看餐厅信息
     * @param canteenId 餐厅编号
     * @return          餐厅信息
     */
    Canteens getCanteenInfo(String canteenId);

    /**
     * 查看餐厅的商品概要
     * @param canteenId 餐厅编号
     * @return          商品概要
     */
    List<CommodityInfoVO> getCanteenCommoditiesInfo(String canteenId);

    /**
     * 查看餐厅的商品详情
     * @param canteenId 餐厅编号
     * @return          商品详情
     */
    List<CommodityDetailVO> getCommodityDetails(String canteenId);

    /**
     * 查看餐厅的优惠详情
     * @param canteenId 餐厅编号
     * @return          优惠详情
     */
    List<SetMealDetailVO> getSetMealDetails(String canteenId);

    /**
     * 查看餐厅的优惠详情
     * @param canteenId 餐厅编号
     * @return          优惠详情
     */
    List<OfferInfoVO> getOfferInfos(String canteenId);

    /**
     * 餐厅查看统计信息
     * @param canteenId 餐厅编号
     * @param start     开始时间
     * @param end       结束时间
     * @param min       最低金额
     * @param max       最高金额
     * @param client    会员昵称
     * @return          订单详情
     */
    List<Orders> seeOrders(String canteenId, String start, String end, Double min, Double max, String client);

    /**
     * 判断餐厅是否可以发布信息（即，是否正在信息审批中）
     * @param canteen   餐厅编号
     * @return          是否可以发布信息
     */
    boolean getState(String canteen);
}
