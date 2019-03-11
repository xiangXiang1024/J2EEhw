package com.j2ee.server.service.impl;

import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Clients;
import com.j2ee.server.service.user.UserService;
import com.j2ee.server.util.CanteenType;
import com.j2ee.server.util.District;
import com.j2ee.server.util.RegistResult;
import com.j2ee.server.util.SendEmailResult;
import com.j2ee.server.vo.AddressVO;
import com.j2ee.server.vo.CanteenRegisterResultVO;
import com.j2ee.server.vo.LoginResultVO;
import com.j2ee.server.vo.RegisterClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void login() {
        String id = "client2";
        String password = "password";
        LoginResultVO resultVO = userService.login(id, password);
        resultVO.print();
    }

    @Test
    public void abandon() {
        String name = "client1";
        boolean result = userService.abandon(name);
        assertEquals(true, result);
    }

    @Test
    public void sendEmail() {
        String name = "user1";
        String mail = "479026126@qq.com";
        SendEmailResult result = userService.sendEmail(name, mail);
        System.out.println("SendEmailResult = "+result);

        System.out.println("----------------------------------------");

        name = "user2";
        mail = "479026126@qq.com";
        result = userService.sendEmail(name, mail);
        System.out.println("SendEmailResult = "+result);
    }

    @Test
    public void registerClient() {
        RegisterClient client = new RegisterClient();
        client.setName("client");
        client.setPassword("password");
        client.setE_mail("161250173@smail.nju.edu.cn");
        client.setPhone("18260199930");
        client.setRealName("许杨");
        client.setVerificationCode("12345");

        RegistResult result = userService.registerClient(client);

        System.out.println("result = "+result+"  "+result.getStr());
    }

    @Test
    // TODO
    public void modifyClientInfo() {
        String name = "client1";
        String realName = "realName";
        String phone = "phone";
        AddressVO[] addressVOS = new AddressVO[1];
        AddressVO vo = new AddressVO();
        vo.setDistrict("玄武区");
        vo.setDetail("test address 1");
        addressVOS[0] = vo;

        boolean result = userService.modifyClientInfo(name, realName, phone, addressVOS);

        assertEquals(true, result);
    }

   /* @Test
    public void modifyAddress() {
        String clientName = "client1";
        AddressVO addressVO1 = new AddressVO();
        addressVO1.setDistrict(District.GULOU);
        addressVO1.setDetail("南京大学鼓楼校区陶园南楼");
        addressVO1.setDefault(true);
        AddressVO addressVO2 = new AddressVO();
        addressVO2.setDistrict(District.QIXIA);
        addressVO2.setDetail("南京大学仙林校区6栋");
        addressVO2.setDefault(false);
        ArrayList<AddressVO> addressVOS = new ArrayList<>();
        addressVOS.add(addressVO1);
        addressVOS.add(addressVO2);

        boolean result = userService.modifyAddress(clientName, addressVOS);

        System.out.println("result = "+result);
    }*/

    @Test
    public void registerCanteen() {
        String name = "test canteen";
        String password = "password";
        District district = District.GULOU;
        String addressDetail = "小粉桥";
        double shippingFee = 0;
        double packagingFee = 0;
        CanteenType type = CanteenType.CHINESE_RESTAURANT;

        CanteenRegisterResultVO result = userService.registerCanteen(name, password, type, district, addressDetail, shippingFee, packagingFee);

        System.out.println("result = " + result.getResult());
        if(result.getResult() == RegistResult.PASS) {
            System.out.println("id = "+ result.getId());
        }
    }

    @Test
    public void modifyCanteenInfo() {
        Canteens canteenInfo = new Canteens("canteen", "test modify canteen", "modifypassword", District.GULOU, "address", 1.0, 1.0, CanteenType.CHINESE_RESTAURANT);
        boolean result = userService.modifyCanteenInfo(canteenInfo);
        System.out.println("result = "+result);
    }
}