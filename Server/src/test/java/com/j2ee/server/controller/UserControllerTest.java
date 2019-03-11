package com.j2ee.server.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void login() {
        try {
            MvcResult result = mvc.perform(
                    post("/user/login")
                            .param("id", "canteen")
                            .param("password", "password")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void abandon() {
        try {
            MvcResult result = mvc.perform(
                    get("/user/abandon")
                            .param("name", "client1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendEmail() {
        try {
            MvcResult result = mvc.perform(
                    get("/user/sendEmail")
                            .param("name", "client")
                            .param("mail", "479026126@qq.com")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerClient() {
        try {
            MvcResult result = mvc.perform(
                    post("/user/registerClient")
                            .param("name", "canteen")
                            .param("password", "password")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyClientInfo() {
        try {
            MvcResult result = mvc.perform(
                    post("/user/modifyClientInfo")
                            .param("name", "canteen")
                            .param("password", "password")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyAddress() {
        try {
            MvcResult result = mvc.perform(
                    get("/user/modifyAddress")
                            .param("name", "canteen")
                            .param("district", "GULOU")
                            .param("detail", "test detail")
                            .param("isDefault", "false")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerCanteen() {
        try {
            MvcResult result = mvc.perform(
                    post("/user/registerCanteen")
                            .param("name", "test canteen")
                            .param("password", "password")
                            .param("district", "GULOU")
                            .param("addressDetail", "小粉桥")
                            .param("shippingFee", "5")
                            .param("packagingFee", "2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyCanteenInfo() {
        try {
            MvcResult result = mvc.perform(
                    get("/user/modifyCanteenInfo")
                            .param("id", "canteen")
                            .param("name", "test modify canteen")
                            .param("district", "GULOU")
                            .param("address", "address")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}