package com.j2ee.server.vo;

import com.j2ee.server.util.RegistResult;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/22
 */
public class CanteenRegisterResultVO {
    private RegistResult result;
    private String id;
    private String resultStr;

    public CanteenRegisterResultVO() {
    }

    public CanteenRegisterResultVO(RegistResult result) {
        this.result = result;
        resultStr = result.getStr();
    }

    public CanteenRegisterResultVO(RegistResult result, String id) {
        this.result = result;
        this.id = id;
        resultStr = result.getStr();
    }

    public RegistResult getResult() {
        return result;
    }

    public void setResult(RegistResult result) {
        this.result = result;
        resultStr = result.getStr();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }
}
