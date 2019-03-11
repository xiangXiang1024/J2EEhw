package com.j2ee.server.controller;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Clients;
import com.j2ee.server.service.user.UserService;
import com.j2ee.server.util.CanteenType;
import com.j2ee.server.util.District;
import com.j2ee.server.util.RegistResult;
import com.j2ee.server.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用户管理
 * @Date: 2019/2/21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param id       餐厅7位编号|会员昵称
     * @param password 密码
     * @return 登录结果
     */
    @PostMapping("/login")
    public LoginResultVO login(String id, String password) {
        return userService.login(id, password);
    }

    /**
     * 注销账号
     * @param name  会员昵称
     * @return      注销结果
     */
    @GetMapping("/abandon")
    public boolean abandon(String name) {
        return userService.abandon(name);
    }

    /**
     * 发送邮件
     * @param name  会员昵称
     * @param mail  邮箱
     * @return      结果
     */
    @GetMapping("/sendEmail")
    public String sendEmail(String name, String mail) {
//        System.out.println("/user/sendEmail");
//        System.out.println("name:"+name+"  mail:"+mail);
        return userService.sendEmail(name, mail).getStr();
    }

    /**
     * 会员注册
     * @param client    会员信息
     * @return          注册结果
     */
    @PostMapping("/registerClient")
    public String registerClient(@RequestBody RegisterClient client) {
        RegistResult result = userService.registerClient(client);
//        System.out.println("in /user/registerClient");
//        System.out.println("result = "+result);
        return result.getStr();
    }

    /**
     * 修改会员信息（包括真实姓名 手机号 收货地址）
     * @param name          会员昵称
     * @param realName      真实姓名
     * @param phone         手机号
//     * @param addressVOS    收货地址
     * @return              修改操作是否成功
     */
    @PostMapping("/modifyClientInfo")
    public boolean modifyClientInfo(String name, String realName, String phone, String[] districts, String[] details) {

//        System.out.println("in /user/modifyClientInfo");
        AddressVO[] addressVOS;
        if(districts == null) {
            addressVOS = new AddressVO[0];
        }else {
            addressVOS = new AddressVO[districts.length];
            for(int i = 0 ; i < districts.length ; i++) {
                AddressVO vo = new AddressVO(districts[i], details[i]);
                addressVOS[i] = vo;
            }
        }

        return userService.modifyClientInfo(name, realName, phone, addressVOS);
    }

   /* *//**
     * 修改会员送货地址
     * @param clientName    会员昵称
     * @param addressVOS    地址信息
     * @return              修改操作是否成功
     *//*
    @GetMapping("/modifyAddress")
    public boolean modifyAddress(String clientName, ArrayList<AddressVO> addressVOS) {
        return userService.modifyAddress(clientName, addressVOS);
    }*/

    /**
     * 餐厅注册
     * @param name          餐厅名称
     * @param password      密码
     * @param type          餐厅类型
     * @param district      街道
     * @param addressDetail 具体地址
     * @param shippingFee   配送费
     * @param packagingFee  包装费
     * @return              注册结果
     */
    @PostMapping("/registerCanteen")
    public CanteenRegisterResultVO registerCanteen(String name, String password, String type, String district, String addressDetail, double shippingFee, double packagingFee) {
        District district1 = District.getEnum(district);
        CanteenType type1 = CanteenType.getEnum(type);
        return userService.registerCanteen(name, password, type1, district1, addressDetail, shippingFee, packagingFee);
    }

    /**
     * 修改餐厅信息（除密码）
     * @return      提交是否成功
     */
    @GetMapping("/modifyCanteenInfo")
    public boolean modifyCanteenInfo(String id, String name, String district, String address, double shippingFee, double packagingFee, String type) {

//        System.out.println("in /user/modifyCanteenInfo");
        CanteenInfoVO info = new CanteenInfoVO(id, name, district, address, shippingFee, packagingFee, type);

//        info.print();

        return userService.modifyCanteenInfo(info.getCanteen());
    }

    /**
     * 查看所有餐厅信息
     * @return  餐厅概要信息
     */
    @GetMapping("/getAllCanteens")
    public List<CanteenInfoVO> getAllCanteens() {
//        System.out.println("/user/getAllCanteens");
        return userService.getAllCanteens();
    }
}
