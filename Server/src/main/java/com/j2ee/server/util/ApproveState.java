package com.j2ee.server.util;

/**
 * @Author: 许杨
 * @Description: 信息审批状态
 * @Date: 2019/2/23
 */
public enum ApproveState {
    WAITING("等待审核"),
    PASS("通过审核"),
    FAIL("审核不通过");
    private final String str;

    ApproveState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
