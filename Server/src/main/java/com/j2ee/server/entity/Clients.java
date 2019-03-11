package com.j2ee.server.entity;

import com.j2ee.server.util.DoubleUtil;
import com.j2ee.server.util.UserState;
import com.j2ee.server.vo.RegisterClient;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/1/26
 */
@Entity
public class Clients {
    @Id
    private String name;
    @NotNull
    private String password;        // 密码
    @NotNull
    private Double balance = 0.0;   // 账户余额
    @NotNull
    private String mail;            // 邮箱
    private String phone;           // 电话
    private String realName;        // 真名
    @NotNull
    private Integer grade = 0;      // 积分
    @NotNull
    private Integer ranking = 0;    // 级别
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserState state = UserState.ACTIVE; // 用户账号状态

    public Clients() {
    }

    public Clients(RegisterClient client) {
        this.name = client.getName();
        this.password = client.getPassword();
        this.mail = client.getE_mail();
        this.phone = client.getPhone();
        this.realName = client.getRealName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = DoubleUtil.format(balance);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void minusBalance(double charge) {
        balance = DoubleUtil.format(balance - charge);
    }

    public void plusBalance(double change) {
        balance = DoubleUtil.format(balance+change);
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void plusGrade(int change) {
        grade = grade + change;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }
}
