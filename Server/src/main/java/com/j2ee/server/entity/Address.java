package com.j2ee.server.entity;

import com.j2ee.server.util.AddressState;
import com.j2ee.server.util.District;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: 许杨
 * @Description: 会员的地址
 * @Date: 2019/2/19
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientName")
    private Clients client;
    @Enumerated(EnumType.STRING)
    @NotNull
    private District district;
    @NotNull
    private String detail;
    @Enumerated(EnumType.STRING)
    @NotNull
    private AddressState state;

    public Address() {
    }

    public Address(Clients client, @NotNull District district, @NotNull String detail, @NotNull AddressState state) {
        this.client = client;
        this.district = district;
        this.detail = detail;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public AddressState getState() {
        return state;
    }

    public void setState(AddressState state) {
        this.state = state;
    }

    public void print() {
        System.out.println("Address:");
        System.out.println(client.getName() + "  " + district.getStr() + "  " + detail + "  " + state.getStr());
    }
}
