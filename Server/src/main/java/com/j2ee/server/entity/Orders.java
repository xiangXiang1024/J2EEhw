package com.j2ee.server.entity;

import com.j2ee.server.util.District;
import com.j2ee.server.util.DoubleUtil;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.OrderConfirmVO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 许杨
 * @Description: 订单表（和OrderGoodsInfo、OrderOfferInfo关联）
 * @Date: 2019/1/26
 */
@Entity
public class Orders {
    @Id
    private String id;              // 订单编号
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderState state;       // 订单状态
    @NotNull
    private LocalDateTime orderTime;// 下单时间
    private LocalDateTime cancelTime;   // 退订时间
    private LocalDateTime deliverTime;   // 发货时间
    private LocalDateTime receiveTime;   // 收货时间
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "canteenId")
    private Canteens canteen;       // 商家
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId")
    private Clients client;         // 会员
    private Double shippingFee = 0.0;   // 配送费
    private Double packagingFee = 0.0;  // 包装费
    private Double sum;             // 折扣前总额（商品+配送费+包装费）
    private Double initialPrice;    // 折扣后总额（折扣前总额*优惠的折扣）
    private Double finalMoney;      // 最终价格（加上会员等级优惠折扣）
    @Enumerated(EnumType.STRING)
    @NotNull
    private District district;      // 会员地址
    @NotNull
    private String addressDetail;   // 会员地址
    private String note;            // 会员备注
    @NotNull
    private Double cancelFee = 0.0; // 退订产生的用户扣费
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offerId")
    private Offers offer;           // 使用折扣
    @OneToMany(targetEntity=OrderGoodsInfo.class)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="ordersId", updatable=false)
    private Set<OrderGoodsInfo> goodsInfos = new HashSet<>();
    private String comment;         // 评价

    public Orders() {
    }

    public Orders(@NotNull OrderState state, @NotNull LocalDateTime orderTime, Canteens canteen, Clients client, Offers offer) {
        this.id = orderTime.toString() + "|" + (int) (Math.random() * 100);
        ;
        this.state = state;
        this.orderTime = orderTime;
        this.canteen = canteen;
        this.client = client;
        this.offer = offer;
        this.shippingFee = DoubleUtil.format(canteen.getShippingFee());
        this.packagingFee = DoubleUtil.format(canteen.getPackagingFee());
    }

    public Orders(OrderConfirmVO vo, Canteens canteen, Clients client, Offers offer, Address address) {
        this.state = OrderState.WAITINGFORORDER;
        this.orderTime = LocalDateTime.now();
        this.canteen = canteen;
        this.client = client;
//        System.out.println("shippingFee: "+vo.getShippingFee());
        this.shippingFee = DoubleUtil.format(vo.getShippingFee());
//        System.out.println("shippingFee: "+this.shippingFee);
        this.packagingFee = DoubleUtil.format(vo.getPackagingFee());
        this.sum = DoubleUtil.format(vo.getSum());
        this.initialPrice = DoubleUtil.format(vo.getInitialPrice());
        this.finalMoney = DoubleUtil.format(vo.getFinalPrice());
        this.district = address.getDistrict();
        this.addressDetail = address.getDetail();
        this.offer = offer;
        this.note = vo.getNote();
        this.id = orderTime.toString()+"|"+(int)(Math.random()*100);;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(LocalDateTime cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Canteens getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteens canteen) {
        this.canteen = canteen;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = DoubleUtil.format(shippingFee);
    }

    public Double getPackagingFee() {
        return packagingFee;
    }

    public void setPackagingFee(Double packagingFee) {
        this.packagingFee = DoubleUtil.format(packagingFee);
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = DoubleUtil.format(sum);
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = DoubleUtil.format(initialPrice);
    }

    public Double getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(Double finalMoney) {
        this.finalMoney = DoubleUtil.format(finalMoney);
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Double getCancelFee() {
        return cancelFee;
    }

    public void setCancelFee(Double cancelFee) {
        this.cancelFee = DoubleUtil.format(cancelFee);
    }

    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }

    public Set<OrderGoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(Set<OrderGoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(LocalDateTime deliverTime) {
        this.deliverTime = deliverTime;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public void print() {
        System.out.println("orders:");
        System.out.println("id = "+id);
        System.out.println("state = "+state);
        System.out.println("orderTime = "+orderTime);
        System.out.println("canteen = "+canteen.getId());
        System.out.println("client = "+client.getName());
        System.out.println("shippingFee = "+shippingFee);
        System.out.println("packagingFee = "+packagingFee);
        System.out.println("sum = "+sum);
        System.out.println("finalMoney = "+finalMoney);
        System.out.println("district = " + district.getStr());
        System.out.println("address = " + addressDetail);
        System.out.print("offer = ");
        if(offer == null) {
            System.out.println("null");
        }else {
            System.out.println("满"+offer.getBase()+"减"+offer.getDiscount());
        }
        System.out.println("goodsInfos = ");
        if (goodsInfos == null) {
            System.out.println("null");
        }else {
            for(OrderGoodsInfo goodsInfo : goodsInfos) {
                System.out.println(goodsInfo.getGoods().getId() + "  "+goodsInfo.getQuantity());
            }
            System.out.println("offerInfos = ");
        }
    }
}
