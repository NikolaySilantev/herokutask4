package com.nikolay.task4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("testuser1")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/messages-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class MessageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/message"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/header/div/div/div/a/span").string("Hello " + "testuser1" + "!"));
    }

    @Test
    public void messageListTest() throws Exception {
        this.mockMvc.perform(get("/message"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='messages']/div").nodeCount(2));
    }
}
