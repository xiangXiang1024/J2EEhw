package com.j2ee.server.service.impl;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Commodities;
import com.j2ee.server.entity.Offers;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.service.canteen.CanteenService;
import com.j2ee.server.util.AddResult;
import com.j2ee.server.util.CommodityState;
import com.j2ee.server.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/1/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CanteenImplTest {
    @Autowired
    private CanteenService canteenService;

    @Test
    public void addGoods() {
        String canteenName = "canteen";
        Commodities commodities = new Commodities();
        commodities.setDescription("add commodities test");
        commodities.setName("commodities");
        commodities.setPrice(20.0);
        commodities.setState(CommodityState.INSTOCK);
        AddResult result = canteenService.addGoods(canteenName, commodities);
        assertEquals(AddResult.PASS, result);
    }

    @Test
    public void addOffer() {
        String canteenId = "canteen";
        Offers offers = new Offers();
        offers.setBase(10.0);
        offers.setDiscount(9.0);
        offers.setStart(LocalDate.now());
        offers.setEnd(LocalDate.now().plusDays(1));
        AddResult result = canteenService.addOffer(canteenId, offers);
        assertEquals(AddResult.PASS, result);
    }

    @Test
    public void addSetMeal() {
        SetMealsBriefVO setMealsBriefVO = new SetMealsBriefVO();
        setMealsBriefVO.setCanteenId("canteen");
        setMealsBriefVO.setName("tao_can_2");
        GoodsBriefVO goodsBriefVO1 = new GoodsBriefVO("Ccanteen0", 1);
        GoodsBriefVO goodsBriefVO2 = new GoodsBriefVO("Ccanteen1", 2);
        GoodsBriefVO[] goodsIdList = new GoodsBriefVO[2];
        goodsIdList[0] = goodsBriefVO1; goodsIdList[1] = goodsBriefVO2;
        setMealsBriefVO.setGoodsList(goodsIdList);
        setMealsBriefVO.setPrice(50.0);
        AddResult result = canteenService.addSetMeal(setMealsBriefVO);
        assertEquals(AddResult.PASS, result);
    }

    @Test
    public void getCanteenInfo() {
        String canteenId = "canteen";
        Canteens canteen = canteenService.getCanteenInfo(canteenId);
        if(canteen == null) {
            System.out.println("canteen: null");
        }else {
            canteen.print();
        }

    }

    @Test
    public void getCanteenCommoditiesInfo() {
        String canteenId = "canteen";

        List<CommodityInfoVO> infoVOList = canteenService.getCanteenCommoditiesInfo(canteenId);

        for(CommodityInfoVO infoVO : infoVOList) {
            infoVO.print();
        }
    }

    @Test
    public void getSetMealDetails() {
        String canteenId = "canteen";
        List<SetMealDetailVO> detailVOList = canteenService.getSetMealDetails(canteenId);
        System.out.println("result: ");
        if(detailVOList == null) {
            System.out.println("null");
        }else {
            for(SetMealDetailVO vo : detailVOList) {
                vo.print();
            }
        }
    }

    @Test
    public void getOfferInfos() {
        String canteenId = "canteen";
        List<OfferInfoVO> voList = canteenService.getOfferInfos(canteenId);
        for(OfferInfoVO vo : voList) {
            vo.print();
        }
    }

    @Test
    public void seeOrders2() {
        String canteenId = "canteen";
        String start = "";
        String end = "";
        Double min = null;
        Double max = null;
        String client = "client";

        List<Orders> orders = canteenService.seeOrders(canteenId, start, end, min, max, client);

        if(orders == null) {
            System.out.println("get null");
        }else {
            System.out.println("list: ");
            for(Orders o : orders) {
                o.print();
            }
        }
    }

    @Test
    public void getState() {
        boolean result = canteenService.getState("canteen");
        System.out.println("result: "+result);
    }
}