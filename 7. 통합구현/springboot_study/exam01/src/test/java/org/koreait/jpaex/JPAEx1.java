package org.koreait.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Users;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class JPAEx1 {
    @PersistenceContext // @Autowired와 동일 역할
    private EntityManager em;

    private Users user;

    @BeforeEach
    void init(){
        Users user = Users.builder()
               // .userNo(1L)
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
            //    .regDt(LocalDateTime.now())
                .build();

        em.persist(user); // 영속 상태 : 변화감지
        em.flush();
       // em.clear();
    }


    @Test
    void test1() {
        Users user = Users.builder()
             //   .userNo(1L)
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
              //  .regDt(LocalDateTime.now())
                .build();

        em.persist(user); // 영속 상태 : 변화감지
        em.flush();

        em.detach(user);

        user.setUserNm("(수정)Person02");
        em.flush(); //update 쿼리

        em.merge(user);
        em.flush();
       // em.remove(user);
       // em.flush();
    }

    @Test
    void test2(){//한번만 조회  ,  find를 하면 flush가 기본적으로 먼저 진행된다
        Users user = em.find(Users.class, 1L);//DB - 영속성 컨택스트
        System.out.println(user);

        Users user2 = em.find(Users.class, 1L);//DB x  영속성 컨택스트
        System.out.println(user2);
    }

    @Test
    void test3() throws InterruptedException {
        Users user = em.find(Users.class, 1L);
        System.out.println(user);

        Thread.sleep(3000);

        user.setUserNm("(수정)Person01");
        Users user2 = em.find(Users.class, 1L);
        System.out.println(user2);
    }
}