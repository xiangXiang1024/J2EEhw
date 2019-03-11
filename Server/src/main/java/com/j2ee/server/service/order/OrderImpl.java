package com.j2ee.server.service.order;

import com.j2ee.server.dao.*;
import com.j2ee.server.entity.*;
import com.j2ee.server.util.*;
import com.j2ee.server.vo.GoodsBriefVO;
import com.j2ee.server.vo.GoodsInfoVO;
import com.j2ee.server.vo.OrderConfirmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 订单模块
 * 平台 餐厅：3 7分
 * @Date: 2019/1/26
 */
@Service
public class OrderImpl implements OrderService {
    private OrderDao orderDao;
    private CanteenDao canteenDao;
    private ClientDao clientDao;
    private GoodsDao goodsDao;
    private OfferDao offerDao;
    private OrderGoodsInfoDao orderGoodsInfoDao;
    private AddressDao addressDao;
    @Autowired
    public OrderImpl(OrderDao orderDao, CanteenDao canteenDao, ClientDao clientDao, GoodsDao goodsDao, OfferDao offerDao, OrderGoodsInfoDao orderGoodsInfoDao, AddressDao addressDao) {
        this.orderDao = orderDao;
        this.canteenDao = canteenDao;
        this.clientDao = clientDao;
        this.goodsDao = goodsDao;
        this.offerDao = offerDao;
        this.orderGoodsInfoDao = orderGoodsInfoDao;
        this.addressDao = addressDao;
    }

    /**
     * 会员下单
     * @param clientName   会员昵称
     * @param canteenId    餐厅编号
     * @param goodsList    商品列表
     * @param offerId       使用优惠
     * @param district      区
     * @param address       地址详情
     * @param note          备注
     * @return 下单结果
     */
    @Override
    public AddOrderResult addOrder(String clientName, String canteenId, ArrayList<GoodsBriefVO> goodsList, String offerId, District district, String address, String note) {
        if(goodsIsEmpty(goodsList)) {
            return AddOrderResult.NOGOODS;
        }

        Clients client;
        if(clientExist(clientName)) {
            client = clientDao.findById(clientName).get();
        }else {
            return AddOrderResult.INVALIDCLIENT;
        }

        Canteens canteen;
        if(canteenExist(canteenId)) {
            canteen = canteenDao.findById(canteenId).get();
        }else {
            return AddOrderResult.INVALIDCANTEEN;
        }

        if(canteen.getDistrict() != district) {
            return AddOrderResult.TOOFAR;
        }

        LocalDate today = LocalDate.now();

        // 检查使用折扣
        Offers offer = null;
        if(offerId != null && offerId.length() > 0) {
            if(!offerDao.existsById(offerId)) {
                return AddOrderResult.INVALIDOFFER;
            }
            offer = offerDao.findById(offerId).get();
            if(!offer.getCanteen().getId().equals(canteenId)) {
                return AddOrderResult.INVALIDOFFER;
            }
            if(today.isBefore(offer.getStart())) {
                return AddOrderResult.INVALIDOFFER;
            }
            if(offer.getEnd() != null && today.isAfter(offer.getEnd())) {
                return AddOrderResult.INVALIDOFFER;
            }
        }

        Orders order = new Orders(OrderState.WAITINGFORORDER, LocalDateTime.now(), canteen, client, offer);
        order.setShippingFee(canteen.getShippingFee());
        order.setPackagingFee(canteen.getPackagingFee());
        order.setDistrict(district);
        order.setAddressDetail(address);
        order.setNote(note);
        double sum = canteen.getPackagingFee()+canteen.getShippingFee();

        // 检查商品库存
        ArrayList<OrderGoodsInfo> goodsInfos = new ArrayList<>();
        for(GoodsBriefVO goodsBriefVO : goodsList ) {
            String goodsId = goodsBriefVO.getGoodsId();
            if(!goodsDao.existsById(goodsId)) {
                return AddOrderResult.INVALIDGOODS;
            }else {
                int quantity = goodsBriefVO.getNum();
                Goods goods = goodsDao.findById(goodsId).get();
                Integer saleNum = orderGoodsInfoDao.getDailySaleSum(goodsId, today.toString());
                if(saleNum == null) {
                    saleNum = 0;
                }
                if(goods.getQuantity() > 0 && saleNum + quantity > goods.getQuantity()) {
                    return AddOrderResult.NOTENOUGHSTOCK;
                }else {
                    sum = sum + goods.getPrice() * quantity;
                    OrderGoodsInfo goodsInfo = new OrderGoodsInfo();
                    goodsInfo.setGoods(goods);
                    goodsInfo.setQuantity(quantity);
                    goodsInfo.setOrder(order);
                    goodsInfos.add(goodsInfo);
                }
            }
        }

        order.setSum(sum);
        double initialPrice = 0;
        if(offer != null) {
            if(sum >= offer.getBase()) {
                initialPrice = sum-offer.getDiscount();
            }
        }else {
            initialPrice = sum;
        }

        int ranking = client.getRanking();
        double discount = 1 - ranking * 0.2;
        double finalPrice = initialPrice * discount;

        DecimalFormat df = new DecimalFormat("#.00");
        finalPrice = Double.parseDouble(df.format(finalPrice));

        double balance = client.getBalance();
        if(finalPrice > balance) {
            return AddOrderResult.NOTENOUGHBALANCE;
        }else {
            //client.minusBalance(finalPrice);
            order.setFinalMoney(finalPrice);
        }

        //order.print();

        // 数据库操作
        orderDao.save(order);
        for(OrderGoodsInfo goodsInfo : goodsInfos) {
            orderGoodsInfoDao.save(goodsInfo);
        }
        //clientDao.save(client);

        CancelOrders.addOrder(order.getId());

        return AddOrderResult.PASS;
    }
    // 判断会员是否存在
    private boolean clientExist(String clientName) {
        if(clientName != null && clientDao.existsById(clientName)) {
            System.out.println("会员存在");
            return true;
        }else {
            System.out.println("会员不存在");
            return false;
        }
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
    // 判断商品是否填写
    private boolean goodsIsEmpty(ArrayList<GoodsBriefVO> goodsList) {
        if(goodsList == null || goodsList.size() == 0) {
            return true;
        }

        for(GoodsBriefVO goodsBriefVO : goodsList) {
            if(goodsBriefVO.getNum() > 0) {
                return false;
            }
        }

        return true;
    }



    /**
     * 付款
     * @param orderId   订单编号
     * @return          付款结果
     */
    @Override
    public boolean payOrder(String orderId) {
        if(!orderDao.existsById(orderId)) {
            return false;
        }

        Orders order = orderDao.findById(orderId).get();
        if(order.getState() != OrderState.WAITINGFORORDER) {
            return false;
        }

        order.setState(OrderState.HASPAID);
        Clients client = order.getClient();
        client.minusBalance(order.getFinalMoney());
        if(client.getBalance() < 0 || client.getState() != UserState.ACTIVE) {
            return false;
        }

        clientDao.save(client);
        orderDao.save(order);

        return true;
    }

    /**
     * 接单
     * @param orderId   订单编号
     * @return          接单结果
     */
    @Override
    public boolean acceptOrder(String orderId) {
        if(!orderDao.existsById(orderId)) {
            return false;
        }

        Orders order = orderDao.findById(orderId).get();
        if(order.getState() != OrderState.HASPAID) {
            return false;
        }

        order.setState(OrderState.HASACCEPT);
        orderDao.save(order);

        return true;
    }

    /**
     * 发送商品
     * @param orderId   订单编号
     * @return          结果
     */
    @Override
    public boolean deliverOrder(String orderId) {
        if(!orderDao.existsById(orderId)) {
            return false;
        }

        Orders order = orderDao.findById(orderId).get();
        if(order.getState() != OrderState.HASACCEPT) {
            return false;
        }

        order.setState(OrderState.HASDELIVERED);
        order.setDeliverTime(LocalDateTime.now());
        orderDao.save(order);

        System.out.println("order "+orderId+" 已发货");
        ReceiveOrders.addOrder(orderId);

        return true;
    }

    /**
     * 确认收货
     * @param orderId   订单编号
     * @return          结果
     */
    @Override
    public boolean receiveOrder(String orderId) {
        if(!orderDao.existsById(orderId)) {
            return false;
        }

        Orders order = orderDao.findById(orderId).get();
        if(order.getState() != OrderState.HASDELIVERED) {
            return false;
        }

        order.setState(OrderState.FINISHED);
        order.setReceiveTime(LocalDateTime.now());
        orderDao.save(order);

        System.out.println("order "+orderId+" 已收货");

        return true;
    }

    /**
     * 退订订单
     * @param orderId   订单编号
     * @return          退订结果
     */
    @Override
    public CancelOrderResult cancelOrder(String orderId) {
        Orders order;
        if(!orderDao.existsById(orderId)) {
            return CancelOrderResult.INVALIDID;
        }else {
            order = orderDao.findById(orderId).get();
        }

        LocalDateTime orderTime = order.getOrderTime();
        LocalDateTime cancelTime = LocalDateTime.now();
        Duration duration = Duration.between(orderTime, cancelTime);
        long minus = duration.toMinutes();
        if(minus > 10) {    // 超过10min，不可以退订
            return CancelOrderResult.FAIL;
        }

        CancelOrderResult result = null;
        double cancelFee = 0;
        if(minus > 5) {     // 5~10min，收取20%退订费
            result = CancelOrderResult.IN20MIN;
            cancelFee = order.getFinalMoney() * 0.2;
        }else {             // 5min以内，收取20%退订费
            result = CancelOrderResult.IN10MIN;
            cancelFee = order.getFinalMoney() * 0.1;
        }

        OrderState state = order.getState();
        if(state == OrderState.HASDELIVERED) {
            result = CancelOrderResult.HASDELIVERED;
        }
        if(state == OrderState.HASDELIVERED) {
            result = CancelOrderResult.HASRECEIVED;
        }

        order.setCancelFee(cancelFee);
        order.setCancelTime(cancelTime);
        order.setState(OrderState.CANCELED);
        orderDao.save(order);

        Clients client = order.getClient();
        client.plusBalance(order.getFinalMoney()-cancelFee);
        clientDao.save(client);

        return result;
    }

    /**
     * 评价订单
     * @param orderId   订单编号
     * @param comment   评价
     * @return          退订结果
     */
    @Override
    public boolean commentOrder(String orderId, String comment) {
        if(!orderDao.existsById(orderId)) {
            return false;
        }

        Orders order = orderDao.findById(orderId).get();

        if(order.getState() != OrderState.FINISHED) {
            return false;
        }

        order.setComment(comment);
        orderDao.save(order);

        return true;
    }

    /**
     * 查看商家商品信息
     * @param canteen 餐厅编号
     * @return        商品概要信息
     */
    @Override
    public List<GoodsInfoVO> seeGoodsInfo(String canteen) {
        List<Goods> goodsList = goodsDao.findDistinctByCanteenIdAndState(canteen, CommodityState.INSTOCK);
        if(goodsList == null) {
            return new ArrayList<>();
        }else {
            List<GoodsInfoVO> infoVOList = new ArrayList<>();
            for(Goods goods : goodsList) {
                GoodsInfoVO infoVO = new GoodsInfoVO(goods);
                infoVOList.add(infoVO);
            }
            return infoVOList;
        }
    }

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
    @Override
    public OrderConfirmVO confirmOrder(String clientName, String canteenId, String[] goodsIdList, int[] goodsNumList, String offerId, Long addressId, String note) {
        AddOrderResult result = AddOrderResult.PASS;

        if(goodsIdList == null || goodsIdList.length == 0) {
            result = AddOrderResult.NOGOODS;
        }

        Clients client;
        if(clientExist(clientName)) {
            client = clientDao.findById(clientName).get();
        }else {
            return new OrderConfirmVO(AddOrderResult.INVALIDCLIENT);
        }

        Canteens canteen;
        if(canteenExist(canteenId)) {
            canteen = canteenDao.findById(canteenId).get();
        }else {
            return new OrderConfirmVO(AddOrderResult.INVALIDCANTEEN);
        }

        Address address = addressDao.findById(addressId).get();

        if(canteen.getDistrict() != address.getDistrict()) {
            result = AddOrderResult.TOOFAR;
        }

        LocalDate today = LocalDate.now();

        // 检查使用折扣
        Offers offer = null;
//        System.out.println("offerId: "+offerId);
        if(offerId != null && offerId.length() > 0 && !offerId.equals("null")) {
//            System.out.println("offerId: "+offerId);
            if(!offerDao.existsById(offerId)) {
//                System.out.println("offer "+offerId+" not exists.");
                return new OrderConfirmVO(AddOrderResult.INVALIDOFFER);
            }
            offer = offerDao.findById(offerId).get();
            if(!offer.getCanteen().getId().equals(canteenId)) {
                return new OrderConfirmVO(AddOrderResult.INVALIDOFFER);
            }
            if(today.isBefore(offer.getStart())) {
                return new OrderConfirmVO(AddOrderResult.INVALIDOFFER);
            }
            if(offer.getEnd() != null && today.isAfter(offer.getEnd())) {
                return new OrderConfirmVO(AddOrderResult.INVALIDOFFER);
            }
        }

        ArrayList<Goods> goodsList = new ArrayList<>();
        for(int i = 0 ; i < goodsIdList.length ; i++) {
            String goodsId = goodsIdList[i];
            if(!goodsDao.existsById(goodsId)) {
//                System.out.println("goods "+goodsId+" not exists.");
                return new OrderConfirmVO(AddOrderResult.INVALIDGOODS);
            }else {
                Goods goods = goodsDao.findById(goodsId).get();
                int quantity = goodsNumList[i];
                Integer saleNum = orderGoodsInfoDao.getDailySaleSum(goodsId, today.toString());
                if(saleNum == null) {
                    saleNum = 0;
                }
                if(goods.getQuantity() > 0 && saleNum + quantity > goods.getQuantity()) {
                    result = AddOrderResult.NOTENOUGHSTOCK;
                }else {
                    goodsList.add(goods);
                }
            }
        }

        String canteenName = canteen.getName();
        double shippingFee = canteen.getShippingFee();
        double packagingFee = canteen.getPackagingFee();
        String offerName = "无";
        if(offer != null) {
            offerName = offer.getDescription();
        }
        int clientRanking = client.getRanking();

        double sum = shippingFee+packagingFee;

        List<String> goodsNameList = new ArrayList<>();
        List<Double> goodsPriceList = new ArrayList<>();
        for(int i = 0 ; i < goodsIdList.length ; i++) {
            goodsNameList.add(goodsList.get(i).getName());
            goodsPriceList.add(goodsList.get(i).getPrice());
            sum = sum + goodsList.get(i).getPrice();
        }
        double initialPrice = sum;
        if(offer != null) {
            if(sum >= offer.getBase()) {
                initialPrice = sum-offer.getDiscount();
            }else {
                result = AddOrderResult.CANNOTUSEORDER;
            }
        }
        double discount = 1 - clientRanking * 0.02;
        double finalPrice = initialPrice * discount;
        if(finalPrice > client.getBalance()) {
            result = AddOrderResult.NOTENOUGHBALANCE;
        }

        if(note == null || note.length() == 0) {
            note = "无";
        }

        String addressStr = address.getDistrict().getStr()+" "+address.getDetail();

        //        vo.print();
        return new OrderConfirmVO(canteenId, canteenName, goodsIdList, goodsNameList, goodsNumList, goodsPriceList, shippingFee, packagingFee, offerId, offerName, clientRanking, sum, initialPrice, finalPrice, result.getS(), note, addressStr, addressId);
    }

    /**
     * 会员下单
     * @param vo    订单信息
     * @param client 会员
     * @return      订单编号
     */
    @Override
    public String addOrder(OrderConfirmVO vo, String client) {
        /*System.out.println("vo: ");
        vo.print();
        System.out.println("client: "+client);*/


        Canteens canteen = canteenDao.findById(vo.getCanteenId()).get();
        Clients clients = clientDao.findById(client).get();
        Address address = addressDao.findById(vo.getAddressId()).get();
        Offers offer = offerDao.findById(vo.getOfferId()).orElse(null);
        Orders order = new Orders(vo, canteen, clients, offer, address);

        ArrayList<OrderGoodsInfo> goodsInfos = new ArrayList<>();
        String[] goodsIdList = vo.getGoodsIdList();
        int[] goodsNumList = vo.getGoodsNumList();
        for(int i = 0 ; i < goodsIdList.length ; i++) {
            String goodsId = goodsIdList[i];
            int quantity = goodsNumList[i];

            Goods goods = goodsDao.findById(goodsId).get();
            OrderGoodsInfo goodsInfo = new OrderGoodsInfo();
            goodsInfo.setGoods(goods);
            goodsInfo.setQuantity(quantity);
            goodsInfo.setOrder(order);
            goodsInfos.add(goodsInfo);
        }

//        System.out.println("in orders: shippingFee = "+order.getShippingFee());

        // 数据库操作
        orderDao.save(order);

//        System.out.println("to save goodsInfos");
//        System.out.println("in orders: shippingFee = "+order.getShippingFee());
        orderGoodsInfoDao.saveAll(goodsInfos);

        CancelOrders.addOrder(order.getId());

        return order.getId();
    }

    /**
     * 检查订单是否超时
     * @param orderId   订单编号
     */
    @Override
    public void timeOutOrder(String orderId) {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if(orderDao.existsById(orderId)) {
            Orders order = orderDao.findById(orderId).get();

            if(order.getState() != OrderState.WAITINGFORORDER) {
                return;
            }

            /*LocalDateTime orderTime = order.getOrderTime();
            Duration duration = Duration.between(orderTime, now);
            if(duration.toMinutes() >= 2) {
                */order.setState(OrderState.CANCELED);
                orderDao.save(order);
//            }
        }

    }
}
