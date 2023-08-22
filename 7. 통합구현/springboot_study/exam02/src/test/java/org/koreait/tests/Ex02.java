package org.koreait.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class Ex02 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1() throws Exception {
        mockMvc.perform(post("/board")
                        .with(csrf().asHeader()))
                .andExpect(status().isOk());
    }

    @Test
    //@WithAnonymousUser // 미로그인 상태
    //@WithMockUser // 로그인 상태
    @WithMockUser(username="user02", password="12345678")
    void test2() throws Exception {
        mockMvc.perform(get("/mypage"))
                .andDo(print());
    }

    @Test
    @WithMockUser(/* username="user01", password="12345678", */ authorities = "ADMIN")
    void test3() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print());
    }

}