package org.koreait.jpaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.contants.UserType;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class JPAEx3 {

    @Autowired
    private  UsersRepository usersRepository;

    @BeforeEach
    void init(){
        List<Users> users = new ArrayList<>();
        for(int i = 1 ; i <= 10 ; i++){
            Users user = Users.builder()
                    .userId("user" + i)
                    .userPw("123456")
                    .userNm("사용자" + i)
                    .type(UserType.USER)
                    .build();
            users.add(user);
        }

        usersRepository.saveAllAndFlush(users);
    }

    @Test
    void test1(){
        List<Users> users = usersRepository.findAll();
        users.stream().forEach(System.out::println);
    }

    @Test
    void test2(){
        Users user = usersRepository.findById(1L).orElse(null);
        System.out.println(user);
    }

}
