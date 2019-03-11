package com.j2ee.server.service.impl.schedule;

import com.j2ee.server.service.client.ClientGrading;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ClientGradingTest {
    @Autowired
    private ClientGrading test;

    @Test
    public void calClientGrade() {
        test.calClientGrade();
    }
}