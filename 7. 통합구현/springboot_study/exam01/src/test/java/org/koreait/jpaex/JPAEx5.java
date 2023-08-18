package org.koreait.jpaex;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.contants.UserType;
import org.koreait.entities.BoardData;
import org.koreait.entities.UserProfile;
import org.koreait.entities.Users;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.UserProfileRepository;
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
public class JPAEx5 {
    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EntityManager em;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @BeforeEach
    void init(){
        UserProfile profile = new UserProfile();
        profile.setProfileImage("이미지!!!");
        userProfileRepository.saveAndFlush(profile);



        Users user = Users.builder()
                .userId("user01")
                .userPw("12345678")
                .userNm("사용자01")
                .type(UserType.USER)
                .profile(profile)
                .build();
        usersRepository.saveAndFlush(user);

        List<BoardData> items = new ArrayList<>();
        for (int i = 1 ; i <= 10 ; i++){
            BoardData item = BoardData
                    .builder()
                    .poster("작성자" + i)
                    .subject("제목" + i)
                    .content("내용" + i)
                    .user(user)
                    .build();
            items.add(item);
        }
        boardDataRepository.saveAllAndFlush(items);
        em.clear();
    }
    @Test
    void test1(){
        BoardData data = boardDataRepository.findById(1L).orElse(null);
        System.out.println(data);
        Users user = data.getUser();
        System.out.println(user);
    }

    @Test
    void test2(){
        Users user = usersRepository.findByUserId("user01");
        List<BoardData> items = user.getBoardDatas();

        items.stream().forEach(System.out::println);
    }

    @Test
    void test3(){
        Users user = usersRepository.findByUserId("user01");

        UserProfile profile = user.getProfile();
        System.out.println(profile);
    }

    @Test
    void test4(){
        UserProfile profile = userProfileRepository.findById(1L).orElse(null);
        Users user = profile.getUser();
        System.out.println(user);
    }

}
