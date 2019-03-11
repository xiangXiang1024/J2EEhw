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
 * @Date: 2019/2/19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void addOrder() {
        try {
            MvcResult result = mvc.perform(
                    post("/order/addOrder")
                            .param("clientName", "client1")
                            .param("canteenId", "canteen")
                            .param("goodsList", "null")
                            .param("offerId", "null")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void payOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/order/payOrder")
                            .param("orderId", "2019-02-19T09:37:48.855-9")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acceptOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/order/acceptOrder")
                            .param("orderId", "2019-02-19T09:37:48.855-9")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deliverOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/order/deliverOrder")
                            .param("orderId", "2019-02-19T09:37:48.855-9")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void receiveOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/order/receiveOrder")
                            .param("orderId", "2019-02-19T09:37:48.855-9")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/order/cancelOrder")
                            .param("orderId", "2019-02-19T09:37:48.855-9")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/order/commentOrder")
                            .param("orderId", "2019-02-19T09:37:48.855-9")
                            .param("comment", "test comment function")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}