package com.j2ee.server.service.user;

import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
public class UserVerification {
    private String name;                // 昵称
    private String verificationCode;    // 验证码
    private LocalDateTime time;         // 发送时间

    public UserVerification(String name, String verificationCode) {
        this.name = name;
        this.verificationCode = verificationCode;
        this.time = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
