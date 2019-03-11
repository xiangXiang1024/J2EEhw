package com.j2ee.server.vo;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.ModifyCanteenInfo;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/4
 */
public class ModifyInfoVO {
    private Long id;
    private String canteenId;
    // 修改前信息
    private String preName;             // 餐厅名
    private String preType;             // 类型
    private String preDistrict;         // 街道
    private String preAddressDetail;    // 地址详情
    // 修改后信息
    private String name;             // 餐厅名
    private String type;             // 类型
    private String district;         // 街道
    private String addressDetail;    // 地址详情

    public ModifyInfoVO() {
    }

    public ModifyInfoVO(ModifyCanteenInfo canteenInfo) {
        this.id = canteenInfo.getId();
        Canteens pre = canteenInfo.getCanteen();
        this.canteenId = pre.getId();

        this.preName = pre.getName();
        this.preType = pre.getType().getStr();
        this.preDistrict = pre.getDistrict().getStr();
        this.preAddressDetail = pre.getAddress();

        this.name = canteenInfo.getName();
        this.type = canteenInfo.getType().getStr();
        this.district = canteenInfo.getDistrict().getStr();
        this.addressDetail = canteenInfo.getAddressDetail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getPreType() {
        return preType;
    }

    public void setPreType(String preType) {
        this.preType = preType;
    }

    public String getPreDistrict() {
        return preDistrict;
    }

    public void setPreDistrict(String preDistrict) {
        this.preDistrict = preDistrict;
    }

    public String getPreAddressDetail() {
        return preAddressDetail;
    }

    public void setPreAddressDetail(String preAddressDetail) {
        this.preAddressDetail = preAddressDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
