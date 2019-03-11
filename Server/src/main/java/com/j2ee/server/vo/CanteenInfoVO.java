package com.j2ee.server.vo;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.util.CanteenType;
import com.j2ee.server.util.District;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/26
 */
public class CanteenInfoVO {
    private String id;
    private String name;            // 名称
    private String district;        // 所在区
    private String address;         // 地址
    private double shippingFee = 0.0;   // 配送费
    private double packagingFee = 0.0;  // 包装费
    private String type;

    public CanteenInfoVO() {
    }

    public CanteenInfoVO(String id, String name, String district, String address, double shippingFee, double packagingFee, String type) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.address = address;
        this.shippingFee = shippingFee;
        this.packagingFee = packagingFee;
        this.type = type;
    }

    public CanteenInfoVO(Canteens canteen) {
        this.id = canteen.getId();
        this.name = canteen.getName();
        this.district = canteen.getDistrict().getStr();
        this.address = canteen.getAddress();
        this.shippingFee = canteen.getShippingFee();
        this.packagingFee = canteen.getPackagingFee();
        this.type = canteen.getType().getStr();
    }

    public Canteens getCanteen() {
        Canteens canteen = new Canteens();
        canteen.setId(id);
        canteen.setName(name);
        canteen.setDistrict(District.getEnum(district));
        canteen.setAddress(address);
        canteen.setShippingFee(shippingFee);
        canteen.setPackagingFee(packagingFee);
        canteen.setType(CanteenType.getEnum(type));

        return canteen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getPackagingFee() {
        return packagingFee;
    }

    public void setPackagingFee(Double packagingFee) {
        this.packagingFee = packagingFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void print() {
        System.out.println("canteen info:");
        System.out.println("id = "+id);
        System.out.println("name = "+name);
        System.out.println("district = "+district);
        System.out.println("address = "+address);
        System.out.println("shippingFee = "+shippingFee);
        System.out.println("packagingFee = "+packagingFee);
        System.out.println("type = "+type);
    }
}
