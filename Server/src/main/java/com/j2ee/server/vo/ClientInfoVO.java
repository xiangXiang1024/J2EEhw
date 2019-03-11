package com.j2ee.server.vo;

import com.j2ee.server.entity.Clients;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/3/1
 */
public class ClientInfoVO {
    private String name;
    private String mail;
    private int ranking;
    private int grade;
    private double balance;
    private String realName;
    private String phone;
    private List<AddressVO> addressList;

    public ClientInfoVO() {
    }

    public ClientInfoVO(String name, String mail, int ranking, int grade, double balance, String realName, String phone, List<AddressVO> addressList) {
        this.name = name;
        this.mail = mail;
        this.ranking = ranking;
        this.grade = grade;
        this.balance = balance;
        this.realName = realName;
        this.phone = phone;
        this.addressList = addressList;
    }

    public ClientInfoVO(Clients client) {
        this.name = client.getName();
        this.mail = client.getMail();
        this.ranking = client.getRanking();
        this.grade = client.getGrade();
        this.balance = client.getBalance();
        this.realName = client.getRealName();
        this.phone = client.getPhone();
        addressList = new ArrayList<>();
    }

    public void print() {
        System.out.println("ClientInfoVO:");
        System.out.println("name: "+name);
        System.out.println("mail: "+mail);
        System.out.println("ranking: "+ranking);
        System.out.println("grade: "+grade);
        System.out.println("balance: "+balance);
        System.out.println("realName: "+realName);
        System.out.println("phone: "+phone);
        System.out.println("addressList: ");
        if(addressList == null) {
            System.out.println("null");
        }else {
            for(AddressVO vo : addressList) {
                System.out.println(vo.getDistrict()+" "+vo.getDetail());
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AddressVO> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressVO> addressList) {
        this.addressList = addressList;
    }
}
