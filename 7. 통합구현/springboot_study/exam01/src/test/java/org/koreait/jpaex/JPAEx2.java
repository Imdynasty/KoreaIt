package org.koreait.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.koreait.contants.UserType;
import org.koreait.entities.Users;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class JPAEx2 {
    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() {
        Users user = Users.builder()
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
            //    .regDt(LocalDateTime.now())
                .build();
        em.persist(user);
        em.flush();
        em.clear();

        Users user1 = em.find(Users.class, 1L);
        System.out.println(user1);
        System.out.printf("regDt:%s, modDt:%s%n",user1.getRegDt(), user1.getModDt());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}

        user1.setUserNm("(수정)사용자01");
        em.flush();
        em.clear();
        Users user2 = em.find(Users.class, 1L);
        System.out.println(user2);
        System.out.printf("regDt:%s, modDt:%s%n",user2.getRegDt(), user2.getModDt());
    }

    @Test
    void test2(){
        Users user = Users.builder()
            .userId("user01")
            .userPw("123456")
            .userNm("사용자01")
                .type(UserType.ADMIN)
         //   .regDt(LocalDateTime.now())
            .build();
        em.persist(user);
        em.flush();

    }
}