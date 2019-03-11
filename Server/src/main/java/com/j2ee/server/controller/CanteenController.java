package com.j2ee.server.controller;

import com.j2ee.server.entity.*;
import com.j2ee.server.service.canteen.CanteenService;
import com.j2ee.server.util.AddResult;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 餐厅模块
 * @Date: 2019/1/26
 */
@RestController
@RequestMapping("/canteen")
public class CanteenController {
    private CanteenService canteenService;
    @Autowired
    public CanteenController(CanteenService canteenService) {
        this.canteenService = canteenService;
    }

    /**
     * 添加商品
     * @return 添加是否成功
     */
    @PostMapping(value = "/addGoods")
    public String addGoods(@RequestBody NewCommodityVO commodityVO) {
        Commodities commodities = commodityVO.getCommodity();
        return canteenService.addGoods(commodityVO.getCanteenId(), commodities).getStr();
    }

    /**
     * 添加优惠
     * @param canteenId 餐厅编号
//     * @param offer 优惠
     * @return 添加是否成功
     */
    @GetMapping(value = "/addOffer")
    public String addOffer(String canteenId, String start, String end, double base, double discount) {
        base = (double)Math.round(base*100)/100;
        discount = (double)Math.round(discount*100)/100;

        Offers offer = new Offers(start, end, base, discount);
        return canteenService.addOffer(canteenId, offer).getStr();
    }

    /**
     * 添加套餐
     * // @param setMealsBriefVO  套餐
     * @return 添加是否成功
     */
    @PostMapping(value = "/addSetMeal")
    public String addSetMeal(String name, String description, String canteenId, double price, int quantity, String start, String end, String[] goodsIdlist, int[] goodsNumlist/*SetMealsBriefVO setMealsBriefVO*/) {
        GoodsBriefVO[] goodsBriefVOList;
        if(goodsIdlist != null && goodsNumlist != null) {
/*
            for(int i = 0 ; i < goodsIdlist.length ; i++) {
                System.out.println(goodsIdlist[i]+"  "+goodsNumlist[i]);
            }*/

                goodsBriefVOList = new GoodsBriefVO[goodsIdlist.length];
            for(int i = 0 ; i < goodsIdlist.length ; i++) {
                GoodsBriefVO vo = new GoodsBriefVO(goodsIdlist[i], goodsNumlist[i]);
                goodsBriefVOList[i] = vo;
            }
        }else {
            goodsBriefVOList = new GoodsBriefVO[0];
        }

        SetMealsBriefVO setMealsBriefVO = new SetMealsBriefVO(name, description, canteenId, goodsBriefVOList, price, quantity, start, end);
//        System.out.println("in /canteen/addSetMeal");
        setMealsBriefVO.print();
        return canteenService.addSetMeal(setMealsBriefVO).getStr();
    }

    /**
     * 餐厅查看订单
     * @param canteenId 餐厅编号
     * @param state     订单状态
     * @return          订单详情
     */
    @GetMapping("/seeOrder")
    public List<OrderDetailVO> seeOrders(String canteenId, OrderState state) {
        List<OrderDetailVO> list = new ArrayList<>();

        List<Orders> ordersList = canteenService.seeOrders(canteenId, state);
        for(Orders o : ordersList) {
            list.add(new OrderDetailVO(o));
        }

        return list;
    }

    /**
     * 查看餐厅信息
     * @param canteenId 餐厅编号
     * @return          餐厅信息
     */
    @GetMapping("/getCanteenInfo")
    CanteenInfoVO getCanteenInfo(String canteenId) {
        Canteens canteen = canteenService.getCanteenInfo(canteenId);
        CanteenInfoVO info = new CanteenInfoVO(canteen);
        info.print();
        return info;
    }

    /**
     * 查看餐厅的商品概要
     * @param canteenId 餐厅编号
     * @return          商品概要
     */
    @GetMapping("/getCanteenCommodities")
    public List<CommodityInfoVO> getCanteenCommoditiesInfo(String canteenId) {
        return canteenService.getCanteenCommoditiesInfo(canteenId);
    }

    /**
     * 查看餐厅的商品详情
     * @param canteenId 餐厅编号
     * @return          商品详情
     */
    @GetMapping("/getCommodityDetails")
    public List<CommodityDetailVO> getCommodityDetails(String canteenId) {
        return canteenService.getCommodityDetails(canteenId);
    }

    /**
     * 查看餐厅的套餐详情
     * @param canteenId 餐厅编号
     * @return          套餐详情
     */
    @GetMapping("/getSetMealDetails")
    public List<SetMealDetailVO> getSetMealDetails(String canteenId) {
        return canteenService.getSetMealDetails(canteenId);
    }

    /**
     * 查看餐厅的优惠详情
     * @param canteenId 餐厅编号
     * @return          优惠详情
     */
    @GetMapping("/getOfferInfos")
    public List<OfferInfoVO> getOfferInfos(String canteenId) {
        return canteenService.getOfferInfos(canteenId);
    }

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
    @GetMapping("/seeStatistics")
    public List<OrderDetailVO> seeStatistics(String canteenId, String start, String end, Double min, Double max, String client) {
        /*System.out.println("in /canteen/seeStatistics");
        System.out.println("canteenId: "+canteenId+"\n"
                            +"start: "+start+"\n"
                            +"end: "+end+"\n"
                            +"min: "+min+"\n"
                            +"max: "+max+"\n"
                            +"client: "+client+"\n");*/

        List<OrderDetailVO> list = new ArrayList<>();

        List<Orders> ordersList = canteenService.seeOrders(canteenId, start, end, min, max, client);
        for(Orders o : ordersList) {
            list.add(new OrderDetailVO(o));
        }

        return list;
    }

    /**
     * 判断餐厅是否可以发布信息（即，是否正在信息审批中）
     * @param canteen   餐厅编号
     * @return          是否可以发布信息
     */
    @GetMapping("/canAddInfo")
    public boolean canAddInfo(String canteen) {
        return canteenService.getState(canteen);
    }
}
