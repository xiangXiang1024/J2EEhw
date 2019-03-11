package com.j2ee.server.vo;

import com.j2ee.server.entity.Address;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/22
 */
public class AddressVO {
    private Long id;
    private String district;
    private String detail;

    public AddressVO() {
        super();
    }

    public AddressVO(Address address) {
        this.id = address.getId();
        this.district = address.getDistrict().getStr();
        this.detail = address.getDetail();
    }

    public AddressVO(String district, String detail) {
        this.district = district;
        this.detail = detail;
    }

    public AddressVO(Long id, String district, String detail) {
        this.id = id;
        this.district = district;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
