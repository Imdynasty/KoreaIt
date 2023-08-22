package org.koreait.tests;

import org.junit.jupiter.api.Test;
import org.koreait.entities.PostData;
import org.koreait.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class Ex01 {
    @Autowired
    private BoardRepository repository;

    @Test
        //@WithMockUser(username="user01", password="12345678")
    void test1() {
        PostData data = PostData.builder()
                .subject("제목")
                .content("내용")
                .build();

        //repository.saveAndFlush(data);

    }
}