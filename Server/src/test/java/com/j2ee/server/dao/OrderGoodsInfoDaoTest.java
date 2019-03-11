package com.j2ee.server.dao;

import com.j2ee.server.entity.Goods;
import com.j2ee.server.entity.OrderGoodsInfo;
import com.j2ee.server.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OrderGoodsInfoDaoTest {
    @Autowired
    private OrderGoodsInfoDao orderGoodsInfoDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void save() {
        Orders order = orderDao.findById("2019-02-19T09:37:48.855-9").get();
        order.print();
        Goods goods = goodsDao.findById("Ccanteen1").get();
        OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
        orderGoodsInfo.setGoods(goods);
        orderGoodsInfo.setOrder(order);
        orderGoodsInfo.setQuantity(1);
        orderGoodsInfoDao.save(orderGoodsInfo);
    }

    @Test
    public void getDailySaleSum() {
        String goodsid = "Ccanteen0";
        String date = "2019-02-17";
        Integer num = orderGoodsInfoDao.getDailySaleSum(goodsid, date);
        if(num == null) {
            System.out.println("num == null");
        }else {
            assertEquals(1, num.intValue());
        }
    }
}