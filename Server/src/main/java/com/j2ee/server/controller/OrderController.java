package com.j2ee.server.controller;

import com.j2ee.server.entity.Goods;
import com.j2ee.server.service.order.OrderService;
import com.j2ee.server.util.AddOrderResult;
import com.j2ee.server.util.CancelOrderResult;
import com.j2ee.server.util.District;
import com.j2ee.server.vo.GoodsBriefVO;
import com.j2ee.server.vo.GoodsInfoVO;
import com.j2ee.server.vo.OrderConfirmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 订单模块
 * @Date: 2019/1/26
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
/*

    */
/**
     * 会员下单
     *
     * @param clientName 会员昵称
     * @param canteenId  餐厅编号
     * @param goodsList  商品列表
     * @param offerId    使用优惠
     * @param district   区
     * @param address    地址详情
     * @param note       备注
     * @return 下单结果
     *//*

    @PostMapping("/addOrder")
    public String addOrder(String clientName, String canteenId, ArrayList<GoodsBriefVO> goodsList, String offerId, District district, String address, String note) {
        return orderService.addOrder(clientName, canteenId, goodsList, offerId, district, address, note).getS();
    }
*/

    /**
     * 付款
     * @param orderId 订单编号
     * @return 付款结果
     */
    @GetMapping("/payOrder")
    public boolean payOrder(String orderId) {
        return orderService.payOrder(orderId);
    }

    /**
     * 接单
     * @param orderId 订单编号
     * @return 接单结果
     */
    @GetMapping("/acceptOrder")
    public boolean acceptOrder(String orderId) {
        return orderService.acceptOrder(orderId);
    }

    /**
     * 发送商品
     * @param orderId 订单编号
     * @return 结果
     */
    @GetMapping("/deliverOrder")
    public boolean deliverOrder(String orderId) {
        return orderService.deliverOrder(orderId);
    }

    /**
     * 确认收货
     *
     * @param orderId 订单编号
     * @return 结果
     */
    @GetMapping("/receiveOrder")
    public boolean receiveOrder(String orderId) {
        return orderService.receiveOrder(orderId);
    }

    /**
     * 退订订单
     *
     * @param orderId 订单编号
     * @return 退订结果
     */
    @GetMapping("/cancelOrder")
    public String cancelOrder(String orderId) {
        return orderService.cancelOrder(orderId).getStr();
    }

    /**
     * 评价订单
     *
     * @param orderId 订单编号
     * @param comment 评价
     * @return 退订结果
     */
    @GetMapping("/commentOrder")
    public boolean commentOrder(String orderId, String comment) {
        return orderService.commentOrder(orderId, comment);
    }

    /**
     * 查看商家正在销售的商品信息
     * @param canteen 餐厅编号
     * @return 商品概要信息
     */
    @GetMapping("/seeGooes")
    public List<GoodsInfoVO> seeGoodsInfo(String canteen) {
        return orderService.seeGoodsInfo(canteen);
    }

    /**
     * 获得订单确认结果
     * @param clientName   会员昵称
     * @param canteenId    餐厅编号
     * @param goodsIdList  商品编号
     * @param goodsNumList 商品数量
     * @param offerId      优惠
     * @param addressId    送餐地址
     * @param note         备注
     * @return 订单确认结果
     */
    @PostMapping("/confirmOrder")
    public OrderConfirmVO confirmOrder(String clientName, String canteenId, String[] goodsIdList, int[] goodsNumList, String offerId, Long addressId, String note) {
//        System.out.println("in /order/confirmOrder");
        return orderService.confirmOrder(clientName, canteenId, goodsIdList, goodsNumList, offerId, addressId, note);
    }

    /**
     * 会员下单
     * @param client 会员
     * @return      订单编号
     */
    @PostMapping("/addOrder")
    public String addOrder(String canteenId, String[] goodsIdList, int[] goodsNumList, double shippingFee, double packagingFee, String offerId, double sum, double initialPrice, double finalPrice, String note, Long addressId, String client) {
        OrderConfirmVO vo = new OrderConfirmVO(canteenId, goodsIdList, goodsNumList, shippingFee, packagingFee, offerId, sum, initialPrice, finalPrice, note, addressId);
        return orderService.addOrder(vo, client);
    }

    /**
     * 检查订单是否超时
     * @param orderId   订单编号
     */
    @GetMapping("/timeOutOrder")
    public void timeOutOrder(String orderId) {
        orderService.timeOutOrder(orderId);
    }
}