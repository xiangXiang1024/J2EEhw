package com.j2ee.server.vo;

import com.j2ee.server.util.AddOrderResult;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/2
 */
public class OrderConfirmVO {
    private String canteenId;
    private String canteenName;
    private String[] goodsIdList;
    private List<String> goodsNameList;
    private int[] goodsNumList;
    private List<Double> goodsPriceList;
    private double shippingFee;
    private double packagingFee;
    private String offerId;
    private String offerName;
    private int clientRanking;
    private double sum;
    private double initialPrice;
    private double finalPrice;
    private String result;
    private String note;
    private String address;
    private Long addressId;

    public OrderConfirmVO() {
    }

    public OrderConfirmVO(AddOrderResult result) {
        this.result = result.getS();
    }

    public OrderConfirmVO(String canteenId, String canteenName, String[] goodsIdList, List<String> goodsNameList, int[] goodsNumList, List<Double> goodsPriceList, double shippingFee, double packagingFee, String offerId, String offerName, int clientRanking, double sum, double initialPrice, double finalPrice, String result, String note, String address, Long addressId) {
        this.canteenId = canteenId;
        this.canteenName = canteenName;
        this.goodsIdList = goodsIdList;
        this.goodsNameList = goodsNameList;
        this.goodsNumList = goodsNumList;
        this.goodsPriceList = goodsPriceList;
        this.shippingFee = shippingFee;
        this.packagingFee = packagingFee;
        this.offerId = offerId;
        this.offerName = offerName;
        this.clientRanking = clientRanking;
        this.sum = sum;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.result = result;
        this.note = note;
        this.address = address;
        this.addressId = addressId;
    }

    public OrderConfirmVO(String canteenId, String[] goodsIdList, int[] goodsNumList, double shippingFee, double packagingFee, String offerId, double sum, double initialPrice, double finalPrice, String note, Long addressId) {
        this.canteenId = canteenId;
        this.goodsIdList = goodsIdList;
        this.goodsNumList = goodsNumList;
        this.shippingFee = shippingFee;
        this.packagingFee = packagingFee;
        this.offerId = offerId;
        this.sum = sum;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.note = note;
        this.addressId = addressId;
    }

    public void print() {
        System.out.println("canteenId: "+canteenId);
        System.out.println("canteenName: "+canteenName);
        System.out.println("goodsIdList: "+goodsIdList);
        System.out.println("goodsNameList: "+goodsNameList);
        System.out.println("goodsNumList: "+goodsNumList);
        System.out.println("goodsPriceList: "+goodsPriceList);
        System.out.println("shippingFee: "+shippingFee);
        System.out.println("packagingFee: "+packagingFee);
        System.out.println("offerId: "+offerId);
        System.out.println("offerName: "+offerName);
        System.out.println("clientRanking: "+clientRanking);
        System.out.println("sum: "+sum);
        System.out.println("initialPrice: "+initialPrice);
        System.out.println("finalPrice: "+finalPrice);
        System.out.println("result: "+result);
        System.out.println("note: "+note);
        System.out.println("address: "+address);
        System.out.println("addressId: "+addressId);
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String[] getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(String[] goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    public List<String> getGoodsNameList() {
        return goodsNameList;
    }

    public void setGoodsNameList(List<String> goodsNameList) {
        this.goodsNameList = goodsNameList;
    }

    public int[] getGoodsNumList() {
        return goodsNumList;
    }

    public void setGoodsNumList(int[] goodsNumList) {
        this.goodsNumList = goodsNumList;
    }

    public List<Double> getGoodsPriceList() {
        return goodsPriceList;
    }

    public void setGoodsPriceList(List<Double> goodsPriceList) {
        this.goodsPriceList = goodsPriceList;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double getPackagingFee() {
        return packagingFee;
    }

    public void setPackagingFee(double packagingFee) {
        this.packagingFee = packagingFee;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public int getClientRanking() {
        return clientRanking;
    }

    public void setClientRanking(int clientRanking) {
        this.clientRanking = clientRanking;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResult(AddOrderResult result) {
        this.result = result.getS();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
