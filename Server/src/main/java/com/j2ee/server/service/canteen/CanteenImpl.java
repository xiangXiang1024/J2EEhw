package com.j2ee.server.service.canteen;

import com.j2ee.server.dao.*;
import com.j2ee.server.entity.*;
import com.j2ee.server.util.AddResult;
import com.j2ee.server.util.ApproveState;
import com.j2ee.server.util.CommodityState;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 餐厅模块
 * @Date: 2019/1/26
 */
@Service
public class CanteenImpl implements CanteenService {
    private CanteenDao canteenDao;
    private CommodityDao commodityDao;
    private OfferDao offerDao;
    private SetMealDao setMealDao;
    private SetMealInfoDao setMealInfoDao;
    private OrderDao orderDao;
    private ModifyCanteenInfoDao modifyCanteenInfoDao;
    @Autowired
    public CanteenImpl(CanteenDao canteenDao, CommodityDao commodityDao, OfferDao offerDao, SetMealDao setMealDao, SetMealInfoDao setMealInfoDao, OrderDao orderDao, ModifyCanteenInfoDao modifyCanteenInfoDao) {
        this.canteenDao = canteenDao;
        this.commodityDao = commodityDao;
        this.offerDao = offerDao;
        this.setMealDao = setMealDao;
        this.setMealInfoDao = setMealInfoDao;
        this.orderDao = orderDao;
        this.modifyCanteenInfoDao = modifyCanteenInfoDao;
    }

    /**
     * 添加商品
     * @param canteenId 餐厅编号
     * @param commodities       商品
     * @return 添加是否成功
     */
    @Override
    public AddResult addGoods(String canteenId, Commodities commodities) {
        Canteens canteen;
        if(canteenExist(canteenId)) {
            canteen = canteenDao.findById(canteenId).get();
        }else {
            return AddResult.INVALIDCANTEEN;
        }

        if(!noOmission(commodities)) {
            return AddResult.INCOMPLETEINFO;
        }

        if(!checkDate(commodities.getStart(), commodities.getEnd())) {
            return AddResult.TIMEERROR;
        }

        String id = getCommoditiesId(canteenId);

        if(commodities.getStart() == null) {
            commodities.setStart(LocalDate.now());
        }
        commodities.setId(id);
        commodities.setCanteen(canteen);

        commodities.print();

        commodityDao.save(commodities);
        return AddResult.PASS;
    }
    // 判断餐厅是否存在
    private boolean canteenExist(String canteenId) {
        if(canteenId != null && canteenDao.existsById(canteenId)) {
            System.out.println("餐厅存在");
            return true;
        }else {
            System.out.println("餐厅不存在");
            return false;
        }
    }
    // 判断填写没有遗漏
    private boolean noOmission(Commodities commodities) {
        return !isEmpty(commodities.getName()) && commodities.getPrice() != null && commodities.getState() != null && commodities.getEnd() != null;
    }
    // 判断信息是否填写
    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
    // 生成商品编号
    private String getCommoditiesId(String canteenId) {
        long num = commodityDao.countByCanteenId(canteenId);
        return "C"+canteenId+num;
    }

    /**
     * 添加优惠
     * @param canteenId 餐厅编号
     * @param offer       优惠
     * @return 添加是否成功
     */
    @Override
    public AddResult addOffer(String canteenId, Offers offer) {
        Canteens canteen;
        if(canteenExist(canteenId)) {
            canteen = canteenDao.findById(canteenId).get();
        }else {
            return AddResult.INVALIDCANTEEN;
        }

        if(!noOmission(offer)) {
            return AddResult.INCOMPLETEINFO;
        }

        if(!checkDate(offer.getStart(), offer.getEnd())) {
            return AddResult.TIMEERROR;
        }

        if(offer.getStart() == null) {
            offer.setStart(LocalDate.now());
        }
        offer.setCanteen(canteen);
        offer.setId(getOffersId(canteenId));

        //offer.print();

        offerDao.save(offer);
        return AddResult.PASS;
    }
    // 生成优惠编号
    private String getOffersId(String canteenId) {
        long num = offerDao.countByCanteenId(canteenId);
        return "O"+canteenId+num;
    }
    // 检查开始时间早于结束时间
    private boolean checkDate(LocalDate start, LocalDate end) {
        if (start == null) {
            start = LocalDate.now();
        }
        return end == null || !end.isBefore(start);

    }
    // 判断填写没有遗漏
    private boolean noOmission(Offers offer) {
        /*if(offer.getDiscount() == null) {
            System.out.println("discount == null");
        }else {
            System.out.println("discount == "+offer.getDiscount());
        }*/
        return offer.getDiscount() != null;
    }

    /**
     * 添加套餐
     * @param setMealsBriefVO  套餐
     * @return 添加是否成功
     */
    @Override
    public AddResult addSetMeal(SetMealsBriefVO setMealsBriefVO) {
        setMealsBriefVO.print();

        Canteens canteen;
        String canteenId = setMealsBriefVO.getCanteenId();
        if(canteenExist(canteenId)) {
            canteen = canteenDao.findById(canteenId).get();
        }else {
            return AddResult.INVALIDCANTEEN;
        }

        if(!noOmission(setMealsBriefVO)) {
            return AddResult.INCOMPLETEINFO;
        }

        if(!checkDate(LocalDate.parse(setMealsBriefVO.getStart()), LocalDate.parse(setMealsBriefVO.getEnd()))) {
            return AddResult.TIMEERROR;
        }

        for(GoodsBriefVO goodsBriefVO : setMealsBriefVO.getGoodsList()) {
            if(!commodityDao.existsById(goodsBriefVO.getGoodsId())) {
                return AddResult.INVALIDGOODS;
            }
        }

        if(setMealsBriefVO.getStart() == null) {
            setMealsBriefVO.setStart(LocalDate.now().toString());
        }

        String id = getSetMealsId(canteenId);
        SetMeals setMeals = new SetMeals(id, canteen, setMealsBriefVO);
        setMeals.print();
        setMealDao.save(setMeals);

        for(GoodsBriefVO goodsBriefVO : setMealsBriefVO.getGoodsList()) {
            Commodities commodities = commodityDao.findById(goodsBriefVO.getGoodsId()).get();
            SetMealInfo setMealInfo = new SetMealInfo(setMeals, commodities, goodsBriefVO.getNum());
            setMealInfo.print();
            setMealInfoDao.save(setMealInfo);
        }

        return AddResult.PASS;
    }
    // 生成套餐编号
    private String getSetMealsId(String canteenId) {
        long num = setMealDao.countByCanteenId(canteenId);
        return "S"+canteenId+num;
    }
    // 判断填写没有遗漏
    private boolean noOmission(SetMealsBriefVO setMealsBriefVO) {
        GoodsBriefVO[] goodsList = setMealsBriefVO.getGoodsList();
        return setMealsBriefVO.getName() != null && goodsList != null && goodsList.length != 0 && setMealsBriefVO.getPrice() != null;
    }
 /*   // 查找一组id对应的商品
    private ArrayList<Commodities> getGoodsList(ArrayList<String> goodsIdList) {
        ArrayList<Commodities> goodsList = new ArrayList<>();

        for(String id : goodsIdList) {
            if(commodityDao.existsById(id)) {
                Commodities goods = commodityDao.findById(id).get();
                goodsList.add(goods);
            }
        }

        return goodsList;
    }*/

    /**
     * 餐厅查看订单
     * @param canteenId 餐厅编号
     * @param state     订单状态
     * @return 订单详情
     */
    @Override
    public List<Orders> seeOrders(String canteenId, OrderState state) {
        return orderDao.findDistinctByCanteenIdAndState(canteenId, state);
    }

    /**
     * 查看餐厅信息
     * @param canteenId 餐厅编号
     * @return          餐厅信息
     */
    @Override
    public Canteens getCanteenInfo(String canteenId) {
        return canteenDao.findById(canteenId).orElse(null);
    }

    /**
     * 查看餐厅的商品信息
     * @param canteenId 餐厅编号
     * @return          商品信息
     */
    @Override
    public List<CommodityInfoVO> getCanteenCommoditiesInfo(String canteenId) {
        List<Commodities> commoditiesList = commodityDao.findDistinctByCanteenIdAndState(canteenId, CommodityState.INSTOCK);
        List<CommodityInfoVO> infoVOList = new ArrayList<>();
        if(commoditiesList == null) {
            return null;
        }else {
            for(Commodities commodity : commoditiesList) {
                infoVOList.add(new CommodityInfoVO(commodity));
            }
        }
        return infoVOList;
    }

    /**
     * 查看餐厅的商品详情
     * @param canteenId 餐厅编号
     * @return          商品详情
     */
    @Override
    public List<CommodityDetailVO> getCommodityDetails(String canteenId) {
        List<Commodities> commoditiesList = commodityDao.findDistinctByCanteenIdAndState(canteenId, CommodityState.INSTOCK);
        List<CommodityDetailVO> voList = new ArrayList<>();
        if(commoditiesList == null) {
            return null;
        }else {
            for(Commodities commodity : commoditiesList) {
                voList.add(new CommodityDetailVO(commodity));
            }
        }
        return voList;
    }

    /**
     * 查看餐厅的套餐详情
     * @param canteenId 餐厅编号
     * @return          套餐详情
     */
    @Override
    public List<SetMealDetailVO> getSetMealDetails(String canteenId) {
        List<SetMeals> setMealsList = setMealDao.findDistinctByCanteenIdAndState(canteenId, CommodityState.INSTOCK);
        if(setMealsList == null) {
            return new ArrayList<>();
        }
        List<SetMealDetailVO> detailVOList = new ArrayList<>();
        for(SetMeals setMeals : setMealsList) {
            List<SetMealInfo> setMealInfos = setMealInfoDao.findDistinctBySetMeals(setMeals);
            List<String> goodsNames = new ArrayList<>();
            List<Integer> goodsNums = new ArrayList<>();
            for(SetMealInfo info : setMealInfos) {
                goodsNames.add(info.getCommodities().getName());
                goodsNums.add(info.getQuantity());
            }

            SetMealDetailVO detailVO = new SetMealDetailVO(setMeals, goodsNames, goodsNums);
            detailVOList.add(detailVO);
        }
        return detailVOList;
    }

    /**
     * 查看餐厅的优惠详情
     * @param canteenId 餐厅编号
     * @return          优惠详情
     */
    @Override
    public List<OfferInfoVO> getOfferInfos(String canteenId) {
//        System.out.println("canteen: "+canteenId);

        List<Offers> offersList = offerDao.findDistinctByCanteenId(canteenId);
/*
        System.out.println("offer: ");
        for(Offers o : offersList) {
            System.out.println(o.getId());
        }*/

        List<OfferInfoVO> voList = new ArrayList<>();
        if(offersList != null) {
            LocalDate now = LocalDate.now();
            for(Offers offer : offersList) {

                if(offer.getStart().isAfter(now) || offer.getEnd().isBefore(now)) {
                    continue;
                }

                OfferInfoVO vo = new OfferInfoVO(offer);
                voList.add(vo);
            }
        }
/*
        System.out.println("offer vo: ");
        for(OfferInfoVO vo : voList) {
            vo.print();
        }*/

        return voList;
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
    @Override
    public List<Orders> seeOrders(String canteenId, String start, String end, Double min, Double max, String client) {
        List<Orders> ordersList = orderDao.findDistinctByCanteenId(canteenId);

        if(ordersList == null) {
            return new ArrayList<>();
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if(start != null && start.length() > 0) {
            LocalDateTime startTime = LocalDateTime.parse(start+" 00:00:00",df);
            ordersList = findByStart(ordersList, startTime);
        }
        if(end != null && end.length() > 0) {
            LocalDateTime endTime = LocalDateTime.parse(end+" 23:59:59", df);
            ordersList = findByEnd(ordersList, endTime);
        }
        if(min != null) {
            ordersList = findByMin(ordersList, min);
        }
        if(max != null) {
            ordersList = findByMax(ordersList, max);
        }
        if(client != null) {
            ordersList = findByClient(ordersList, client);
        }

        return ordersList;
    }
    // 根据开始时间筛选
    private List<Orders> findByStart(List<Orders> orderList, LocalDateTime start) {
        int i = 0;
        while(i < orderList.size()) {
            Orders o = orderList.get(i);
            LocalDateTime time = o.getOrderTime();
            if(time.isAfter(start)) {
                i++;
            }else {
                orderList.remove(i);
            }
        }
        return orderList;
    }
    // 根据结束时间筛选
    private List<Orders> findByEnd(List<Orders> orderList, LocalDateTime end) {
        int i = 0;
        while(i < orderList.size()) {
            Orders o = orderList.get(i);
            LocalDateTime time = o.getOrderTime();
            if(time.isBefore(end)) {
                i++;
            }else {
                orderList.remove(i);
            }
        }
        return orderList;
    }
    // 根据最低金额筛选
    private List<Orders> findByMin(List<Orders> orderList, double min) {
        int i = 0;
        while(i < orderList.size()) {
            Orders o = orderList.get(i);
            double money = o.getInitialPrice();
            if(money >= min) {
                i++;
            }else {
                orderList.remove(i);
            }
        }
        return orderList;
    }
    // 根据最高金额筛选
    private List<Orders> findByMax(List<Orders> orderList, double max) {
        int i = 0;
        while(i < orderList.size()) {
            Orders o = orderList.get(i);
            double money = o.getInitialPrice();
            if(money <= max) {
                i++;
            }else {
                orderList.remove(i);
            }
        }
        return orderList;
    }
    // 根据会员昵称筛选
    private List<Orders> findByClient(List<Orders> orderList, String client) {
        int i = 0;
        while(i < orderList.size()) {
            Orders o = orderList.get(i);
            String name = o.getClient().getName();
            if(name.contains(client)) {
                i++;
            }else {
                orderList.remove(i);
            }
        }
        return orderList;
    }

    /**
     * 判断餐厅是否可以发布信息（即，是否正在信息审批中）
     * @param canteen   餐厅编号
     * @return          是否可以发布信息
     */
    @Override
    public boolean getState(String canteen) {
        Canteens canteens;
        if(!canteenDao.existsById(canteen)) {
            return false;
        }else {
            canteens = canteenDao.findById(canteen).get();
        }
        return !modifyCanteenInfoDao.existsByCanteenAndState(canteens, ApproveState.WAITING);
    }
}
