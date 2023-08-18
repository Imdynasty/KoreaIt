package org.koreait.jpaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.contants.UserType;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class JPAEx4 {
    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    void init(){
        List<Users> items = new ArrayList<>();
        for(int i = 1 ; i<= 10 ;i++){
            Users user = Users.builder()
                    .userId("user" + i)
                    .userPw("12345678")
                    .userNm("사용자" + i)
                    .type(UserType.USER)
                    .build();
            items.add(user);
        }

        usersRepository.saveAllAndFlush(items);
    }

    @Test
    void test1(){
        Users user = usersRepository.findByUserId("user5");
        System.out.println(user);

    }

    @Test
    void test2(){
        LocalDateTime sDate = LocalDateTime.now().minusDays(7);
        LocalDateTime eDate = LocalDateTime.now().plusDays(7);
        List<Users> items = usersRepository.findByRegDtBetweenOrderByRegDtDesc(sDate, eDate);
        items.stream().forEach(System.out::println);
    }

    @Test
    void test3(){
        Pageable pageable = PageRequest.of(0,3);
        List<Users> items = usersRepository.findByUserNmContainingOrderByRegDtDesc("사용자", pageable);
        items.stream().forEach(System.out::println);
    }

    @Test
    void test4(){
        List<Users> items = usersRepository.getUsers1("사용자");
        items.stream().forEach(System.out::println);
    }

    @Test
    void test5(){
        List<Users> items = usersRepository.getUsers2("사용자");
        items.stream().forEach(System.out::println);
    }

    @Test
    void test6(){
        List<Users> items = usersRepository.getUsers3("사용자");
        items.stream().forEach(System.out::println);
    }

}
