package com.j2ee.server.controller;

import com.alibaba.fastjson.JSON;
import com.j2ee.server.entity.SetMeals;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/1/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CanteenControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void addGoods() {
        try {
            MvcResult result = mvc.perform(
                    post("/canteen/addGoods")
                            .param("canteenId", "canteen")
                            .param("name", "goods")
                            .param("price", "20")
                            .param("description", "add goods test")
                            .param("state", "INSTOCK")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addOffer() {
        try {
            MvcResult result = mvc.perform(
                    post("/canteen/addOffer")
                            .param("canteenId", "canteen")
                           // .param("start", "2019-01-26")
                           // .param("end", "2019-01-27")
                            .param("base", "10.0")
                            .param("discount", "9.0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addSetMeal() {
        SetMeals setMeal = new SetMeals();
        try {
            MvcResult result = mvc.perform(
                    post("/canteen/addSetMeal")
                            .param("name", "tao_can_1")
                            .param("description", "")
                            .param("canteenId", "canteen")
                            .param("price", "30.0")
//                            .param("goodsIdList", "canteen0, canteen1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void seeOrder() {
        try {
            MvcResult result = mvc.perform(
                    get("/canteen/seeOrder")
                            .param("canteenId", "canteen")
                            .param("state", "FINISHED")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCanteenInfo() {
        try {
            MvcResult result = mvc.perform(
                    get("/canteen/getCanteenInfo")
                            .param("canteenId", "canteen")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCanteenCommodities() {
        try {
            MvcResult result = mvc.perform(
                    get("/canteen/getCanteenCommodities")
                            .param("canteenId", "canteen")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSetMealDetails() {
        try {
            MvcResult result = mvc.perform(
                    get("/canteen/getSetMealDetails")
                            .param("canteenId", "canteen")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}