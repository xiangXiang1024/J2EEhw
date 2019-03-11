package com.j2ee.server.service.user;

import com.j2ee.server.dao.AddressDao;
import com.j2ee.server.dao.CanteenDao;
import com.j2ee.server.dao.ClientDao;
import com.j2ee.server.dao.ModifyCanteenInfoDao;
import com.j2ee.server.entity.Canteens;
import com.j2ee.server.entity.Clients;
import com.j2ee.server.entity.Address;
import com.j2ee.server.entity.ModifyCanteenInfo;
import com.j2ee.server.util.*;
import com.j2ee.server.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 许杨
 * @Description: 用户管理
 * @Date: 2019/2/21
 */
@Service
public class UserServiceImpl implements UserService{
    private static ArrayList<UserVerification> userVerifications = new ArrayList<>();
/*    static {
        UserVerification userVerification = new UserVerification("client", "12345");
        userVerification.setTime(LocalDateTime.parse("2019-02-22 15:50:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        userVerifications.add(userVerification);
    }*/

    private CanteenDao canteenDao;
    private ClientDao clientDao;
    private JavaMailSender mailSender;
    private AddressDao addressDao;
    private ModifyCanteenInfoDao modifyCanteenInfoDao;
    @Autowired
    public UserServiceImpl(CanteenDao canteenDao, ClientDao clientDao, JavaMailSender mailSender, AddressDao addressDao, ModifyCanteenInfoDao modifyCanteenInfoDao) {
        this.canteenDao = canteenDao;
        this.clientDao = clientDao;
        this.mailSender = mailSender;
        this.addressDao = addressDao;
        this.modifyCanteenInfoDao = modifyCanteenInfoDao;
    }

    /**
     * 登录
     * @param id       餐厅7位编号|会员昵称
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public LoginResultVO login(String id, String password) {
        if(id != null && password!=null && id.equals("admin")) {
            if(password.equals("admin")) {
                return new LoginResultVO(LoginResult.PASS, id, UserType.ADMIN);
            }else {
                return new LoginResultVO(LoginResult.WRONGPASSWORD);
            }
        }

        LoginResult result;
        if(id.length() == 7) {
            result = canteenLogin(id, password);
            if(result != LoginResult.INVALIDUSER) {
                return new LoginResultVO(result, id, UserType.CANTEEN);
            }
        }
        return clientLogin(id, password);
    }
    // 餐厅登录
    private LoginResult canteenLogin(String id, String password) {
        if(!canteenDao.existsById(id)) {
            return LoginResult.INVALIDUSER;
        }else {
            Canteens canteen = canteenDao.findById(id).get();
            if(password.equals(canteen.getPassword())) {
                return LoginResult.PASS;
            }else {
                return LoginResult.WRONGPASSWORD;
            }
        }
    }
    // 会员登录
    private LoginResultVO clientLogin(String id, String password) {
        if(!clientDao.existsByNameAndState(id, UserState.ACTIVE)) {
            return new LoginResultVO(LoginResult.INVALIDUSER);
        }else {
            Clients client = clientDao.findById(id).get();
            if(password.equals(client.getPassword())) {
                return new LoginResultVO(LoginResult.PASS, id, UserType.CLIENT);
            }else {
                return new LoginResultVO(LoginResult.WRONGPASSWORD);
            }
        }
    }

    /**
     * 注销账号
     * @param name  会员昵称
     * @return      注销结果
     */
    @Override
    public boolean abandon(String name) {
        if(clientDao.existsByNameAndState(name, UserState.ACTIVE)) {
            Clients client = clientDao.findById(name).get();
            client.setState(UserState.ABANDON);
            clientDao.save(client);
            return true;
        }
        return false;
    }

    /**
     * 发送邮件
     * @param name  会员昵称
     * @param mail  邮箱
     * @return      结果
     */
    @Override
    public SendEmailResult sendEmail(String name, String mail) {
       /* System.out.println("in the func send email");
        System.out.println("name: "+name);
        System.out.println("mail: "+mail);*/


        if(name == null || name.equals("")) {
            return SendEmailResult.NOUSER;
        }

        if(clientDao.existsByMail(mail)) {
            return SendEmailResult.EXISTMAIL;
        }

        if(name.equals("admin") || clientDao.existsById(name) || canteenDao.existsById(name)) {
            return SendEmailResult.EXISTUSER;
        }else if(!isEmail(mail)){
            return SendEmailResult.INVALIDMAIL;
        }else {
            String code = getVerificationCode();
            UserVerification userVerification = new UserVerification(name, code);
            for(UserVerification verification : userVerifications) {
                if(verification.getName().equals(name)) {
                    verification.setVerificationCode(code);
                    sendMail(mail, code);
                    return SendEmailResult.SUCCESS;
                }
            }

            userVerifications.add(userVerification);
            sendMail(mail, code);
            return SendEmailResult.SUCCESS;
        }
    }
    // 随机生成5位验证码
    private String getVerificationCode() {
        //生成一个5位数的随机数
        String code = UUID.randomUUID().toString().substring(0,5);
        //生成一个5位数的随机数
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        str+=str.toLowerCase();
        str+="0123456789";
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
    // 判断邮箱是否合法
    private boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        return m.matches();
    }
    // 发送邮件
    public void sendMail(String mail, String code) {
        String text = "【Yummy!】"+code+"为您的注册验证码，请在5分钟内完成身份认证。验证码请勿泄漏，如非本人操作，请忽略。";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("479026126@qq.com");
        message.setTo(mail);
        message.setSubject("主题：验证邮箱");
        message.setText(text);

        System.out.println("message:"+message.getText());

        mailSender.send(message);

        System.out.println("邮件发送完毕");
    }

    /**
     * 会员注册
     * @param client    会员信息
     * @return          注册结果
     */
    @Override
    public RegistResult registerClient(RegisterClient client) {
        if(isIncomplete(client)) {
            return RegistResult.INCOMPLETEINFOMATION;
        }

        String name = client.getName();
        if(name.equals("admin") || clientDao.existsById(name) || canteenDao.existsById(name)) {
            return RegistResult.HASEXISTED;
        }

        UserVerification verification = null;
        for(int i = 0 ; i < userVerifications.size() ; i++) {
            if(userVerifications.get(i).getName().equals(name)) {
                verification = userVerifications.get(i);
                break;
            }
        }

        if(verification == null) {
            return RegistResult.NOVERRIFICATION;
        }

        LocalDateTime registerTime = LocalDateTime.now();
        LocalDateTime sendTime = verification.getTime();
        Duration duration = Duration.between(sendTime, registerTime);
        if(duration.toMinutes() > 5) {
            return RegistResult.OVERTIME;
        }
        if(verification.getVerificationCode().equals(client.getVerificationCode())) {
            userVerifications.remove(verification);

            Clients clientEntity = new Clients(client);
            clientDao.save(clientEntity);

            return RegistResult.PASS;
        }else {
            return RegistResult.WRONGVERRIFICATION;
        }
    }
    // 会员注册信息不完整
    private boolean isIncomplete(RegisterClient client) {
        return client.getName() == null || client.getName().equals("") || client.getPassword() == null || client.getPassword().equals("") || client.getE_mail() == null || !isEmail(client.getE_mail());
    }

    /**
     * 修改会员信息（包括真实姓名 手机号 收货地址）
     * @param name          会员昵称
     * @param realName      真实姓名
     * @param phone         手机号
     * @param addressVOS    收货地址
     * @return              修改操作是否成功
     */
    @Override
    public boolean modifyClientInfo(String name, String realName, String phone, AddressVO[] addressVOS) {
        if(clientDao.existsById(name)) {
            Clients client = clientDao.findById(name).get();
            client.setPhone(phone);
            client.setRealName(realName);
            clientDao.save(client);

            // 地址
            List<Address> addressList = addressDao.findDistinctByClientName(name);
            if(addressList == null) {
                addressList = new ArrayList<>();
            }
            for(AddressVO addressVO : addressVOS) {
                District district = District.getEnum(addressVO.getDistrict());
                String detail = addressVO.getDetail();
                AddressState state = AddressState.INUSE;

                boolean isFound = false;
                for(Address address : addressList) {
                    if(address.getDistrict().equals(district) && address.getDetail().equals(detail)) {
                        isFound = true;
                        address.setState(state);
//                        addressDao.save(address);
//                        addressList.remove(address);
                        break;
                    }
                }

                if(!isFound) {
                    Address address = new Address(client, district, detail, state);
                    addressList.add(address);
//                    addressDao.save(address);
                }
            }
/*
            System.out.println("addressList: ");
            for(Address address : addressList) {
                System.out.println(address.getDistrict()+"  "+address.getDetail()+"  "+address.getState());
            }*/

            addressDao.saveAll(addressList);

            /*for(Address address : addressList) {
                if(address.getState() != AddressState.DISCARD) {
                    address.setState(AddressState.DISCARD);
                    addressDao.save(address);
                }
            }*/
        }

        return true;
    }
/*

    */
/**
     * 修改会员送货地址
     * @param clientName    会员昵称
     * @param addressVOS    地址信息
     * @return              修改操作是否成功
     *//*

    @Override
    public boolean modifyAddress(String clientName, ArrayList<AddressVO> addressVOS) {
        if(!clientDao.existsById(clientName)) {
            return false;
        }

        Clients client = clientDao.findById(clientName).get();

        List<Address> addressList = addressDao.findDistinctByClientName(clientName);
        if(addressList == null) {
            addressList = new ArrayList<>();
        }

        for(AddressVO addressVO : addressVOS) {
            District district = addressVO.getDistrict();
            String detail = addressVO.getDetail();
            AddressState state;
            if(addressVO.isDefault()) {
                state = AddressState.DEFAULT;
            }else {
                state = AddressState.INUSE;
            }

            boolean isfound = false;
            for(Address address : addressList) {
                if(address.getDistrict().equals(district) && address.getDetail().equals(detail)) {
                    isfound = true;
                    address.setState(state);
                    addressDao.save(address);
                    addressList.remove(address);

                    break;
                }
            }

            if(!isfound) {
                Address address = new Address(client, district, detail, state);
                addressDao.save(address);
            }
        }

        for(Address address : addressList) {
            if(address.getState() != AddressState.DISCARD) {
                address.setState(AddressState.DISCARD);
                addressDao.save(address);
            }
        }

        return true;
    }
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
    @Override
    public CanteenRegisterResultVO registerCanteen(String name, String password, CanteenType type, District district, String addressDetail, double shippingFee, double packagingFee) {
      /*  System.out.println("in the func registerCanteen");
        System.out.println("name: "+name);
        System.out.println("password: "+password);
        System.out.println("type: "+type);
        System.out.println("district: "+district);
        System.out.println("addressDetail: "+addressDetail);
        System.out.println("shippingFee: "+shippingFee);
        System.out.println("packagingFee: "+packagingFee);*/


        if(isIncomplete(name, password, district, addressDetail, type)) {
            return new CanteenRegisterResultVO(RegistResult.INCOMPLETEINFOMATION);
        }

        String id = getCanteenId();
        while(canteenDao.existsById(id)) {
            id = getCanteenId();
        }

        Canteens canteen = new Canteens(id, name, password, district, addressDetail, shippingFee, packagingFee, type);
        canteenDao.save(canteen);

        canteen.print();

        return new CanteenRegisterResultVO(RegistResult.PASS, id);
    }
    // 判断餐厅注册信息填写是否完整
    private boolean isIncomplete(String name, String password, District district, String addressDetail, CanteenType type) {
        return name == null || name.length() == 0 || password == null || password.length() == 0 || district == null || addressDetail == null || addressDetail.length() == 0 || type==null;
    }
    // 随机生成7位餐厅编号
    private String getCanteenId() {
        //生成一个7位数的随机数
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        str+=str.toLowerCase();
        str+="0123456789";
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 修改餐厅信息（除密码）
     * @param canteenInfo   餐厅信息
     * @return              提交是否成功
     */
    @Override
    public boolean modifyCanteenInfo(Canteens canteenInfo) {
        String id = canteenInfo.getId();
        if(canteenDao.existsById(id)) {
            Canteens canteen = canteenDao.findById(id).get();
            ModifyCanteenInfo modifyCanteenInfo = new ModifyCanteenInfo(canteen, canteenInfo);
            modifyCanteenInfoDao.save(modifyCanteenInfo);
            return true;
        }
        return false;
    }

    /**
     * 查看所有餐厅信息
     * @return  餐厅概要信息
     */
    @Override
    public List<CanteenInfoVO> getAllCanteens() {
        List<Canteens> canteenList = (List<Canteens>)canteenDao.findAll();
        if(canteenList == null) {
            return new ArrayList<>();
        }

        List<CanteenInfoVO> infoVOList = new ArrayList<>();
        for(Canteens canteen : canteenList) {
            CanteenInfoVO infoVO = new CanteenInfoVO(canteen);
            infoVOList.add(infoVO);
        }

        return infoVOList;
    }
}
