package com.j2ee.server.vo;

/**
 * @Author: 许杨
 * @Description: 会员注册的信息
 * @Date: 2019/2/22
 */
public class RegisterClient {
    private String name;
    private String password;        // 密码
    private String e_mail;          // 邮箱
    private String phone;           // 电话
    private String realName;        // 真名
    private String verificationCode;// 验证码

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

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
