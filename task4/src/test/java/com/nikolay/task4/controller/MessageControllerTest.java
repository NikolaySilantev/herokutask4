package com.nikolay.task4.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MessageControllerTest {

    /* 26.4.19 Testing: The simplest form of server-side tests is to write controller unit tests */

    @Autowired
    private MessageController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
