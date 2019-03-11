package com.j2ee.server.service.user;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Clients;
import com.j2ee.server.util.CanteenType;
import com.j2ee.server.util.District;
import com.j2ee.server.util.RegistResult;
import com.j2ee.server.util.SendEmailResult;
import com.j2ee.server.vo.*;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用户管理模块
 * @Date: 2019/2/21
 */
public interface UserService {
    /**
     * 登录
     * @param id        餐厅7位编号|会员昵称
     * @param password  密码
     * @return          登录结果
     */
    LoginResultVO login(String id, String password);

    /**
     * 注销账号
     * @param name  会员昵称
     * @return      注销结果
     */
    boolean abandon(String name);

    /**
     * 发送邮件
     * @param name  会员昵称
     * @param mail  邮箱
     * @return      结果
     */
    SendEmailResult sendEmail(String name, String mail);

    /**
     * 会员注册
     * @param client    会员信息
     * @return          注册结果
     */
    RegistResult registerClient(RegisterClient client);

    /**
     * 修改会员信息（包括真实姓名 手机号 收货地址）
     * @param name          会员昵称
     * @param realName      真实姓名
     * @param phone         手机号
     * @param addressVOS    收货地址
     * @return              修改操作是否成功
     */
    boolean modifyClientInfo(String name, String realName, String phone, AddressVO[] addressVOS);
/*

    */
/**
     * 修改会员送货地址
     * @param clientName    会员昵称
     * @param addressVOS    地址信息
     * @return              修改操作是否成功
     *//*

    boolean modifyAddress(String clientName, ArrayList<AddressVO> addressVOS);
*/

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
    CanteenRegisterResultVO registerCanteen(String name, String password, CanteenType type, District district, String addressDetail, double shippingFee, double packagingFee);

    /**
     * 修改餐厅信息（除密码）
     * @param canteenInfo   餐厅信息
     * @return              提交是否成功
     */
    boolean modifyCanteenInfo(Canteens canteenInfo);

    /**
     * 查看所有餐厅信息
     * @return  餐厅概要信息
     */
    List<CanteenInfoVO> getAllCanteens();
}
