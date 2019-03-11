package com.j2ee.server.entity;

import com.j2ee.server.util.ApproveState;
import com.j2ee.server.util.CanteenType;
import com.j2ee.server.util.District;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: 许杨
 * @Description: 餐厅的修改信息，提交待管理员审批
 * @Date: 2019/2/23
 */
@Entity
public class ModifyCanteenInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;            // 餐厅名
    @NotNull
    @Enumerated(EnumType.STRING)
    private District district;      // 街道
    @NotNull
    private String addressDetail;   // 地址详情
    @NotNull
    @Enumerated(EnumType.STRING)
    private CanteenType type;      // 类型
    @NotNull
    @Enumerated(EnumType.STRING)
    private ApproveState state;     // 审核状态
    private String comment;         // 备注
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "canteenId")
    private Canteens canteen;       // 餐厅

    public ModifyCanteenInfo() {
    }

    public ModifyCanteenInfo(Canteens canteen, Canteens info) {
        this.name = info.getName();
        this.district = info.getDistrict();
        this.addressDetail = info.getAddress();
        this.type = info.getType();
        state = ApproveState.WAITING;
        this.canteen = canteen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ApproveState getState() {
        return state;
    }

    public void setState(ApproveState state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Canteens getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteens canteen) {
        this.canteen = canteen;
    }

    public CanteenType getType() {
        return type;
    }

    public void setType(CanteenType type) {
        this.type = type;
    }
}
