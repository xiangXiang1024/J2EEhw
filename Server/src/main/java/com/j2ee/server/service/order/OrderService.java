package com.j2ee.server.service.order;

import com.j2ee.server.entity.Goods;
import com.j2ee.server.util.AddOrderResult;
import com.j2ee.server.util.CancelOrderResult;
import com.j2ee.server.util.District;
import com.j2ee.server.vo.GoodsBriefVO;
import com.j2ee.server.vo.GoodsInfoVO;
import com.j2ee.server.vo.OrderConfirmVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 订单模块
 * @Date: 2019/1/26
 */
public interface OrderService {
    /**
     * 会员下单
     * @param clientName    会员昵称
     * @param canteenId     餐厅编号
     * @param goodsList     商品列表
     * @param offerId       使用优惠
     * @param district      区
     * @param address       地址详情
     * @param note          备注
     * @return              下单结果
     */
    AddOrderResult addOrder(String clientName, String canteenId, ArrayList<GoodsBriefVO> goodsList, String offerId, District district, String address, String note);

    /**
     * 付款
     * @param orderId   订单编号
     * @return          付款结果
     */
    boolean payOrder(String orderId);

    /**
     * 接单
     * @param orderId   订单编号
     * @return          接单结果
     */
    boolean acceptOrder(String orderId);

    /**
     * 发送商品
     * @param orderId   订单编号
     * @return          结果
     */
    boolean deliverOrder(String orderId);

    /**
     * 确认收货
     * @param orderId   订单编号
     * @return          结果
     */
    boolean receiveOrder(String orderId);

    /**
     * 退订订单
     * @param orderId   订单编号
     * @return          退订结果
     */
    CancelOrderResult cancelOrder(String orderId);

    /**
     * 评价订单
     * @param orderId   订单编号
     * @param comment   评价
     * @return          退订结果
     */
    boolean commentOrder(String orderId, String comment);

    /**
     * 查看商家商品信息
     * @param canteen 餐厅编号
     * @return        商品概要信息
     */
    List<GoodsInfoVO> seeGoodsInfo(String canteen);

    /**
     * 获得订单确认结果
     * @param clientName        会员昵称
     * @param canteenId         餐厅编号
     * @param goodsIdList       商品编号
     * @param goodsNumList      商品数量
     * @param offerId           优惠
     * @param addressId         送餐地址
     * @param note              备注
     * @return                  订单确认结果
     */
    public OrderConfirmVO confirmOrder(String clientName, String canteenId, String[] goodsIdList, int[] goodsNumList, String offerId, Long addressId, String note);

    /**
     * 会员下单
     * @param vo    订单信息
     * @param client 会员
     * @return      订单编号
     */
    String addOrder(OrderConfirmVO vo, String client);

    /**
     * 检查订单是否超时
     * @param orderId   订单编号
     */
    void timeOutOrder(String orderId);
}
