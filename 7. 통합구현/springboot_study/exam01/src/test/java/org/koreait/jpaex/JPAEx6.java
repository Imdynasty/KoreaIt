package org.koreait.jpaex;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koreait.entities.BoardData;
import org.koreait.entities.HashTag;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.HashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class JPAEx6 {
    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private HashTagRepository hashTagRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void init(){
        List<HashTag> tags = new ArrayList<>();
        for(int i = 1 ; i <= 5 ; i++){
            HashTag tag = new HashTag();
            tag.setTag("TAG" + i);
            tags.add(tag);
        }

        hashTagRepository.saveAllAndFlush(tags);

        List<BoardData> items = new ArrayList<>();
        for(int i = 1 ; i <= 5 ; i++){
            BoardData item = BoardData
                    .builder()
                    .poster("작성자" + i)
                    .subject("제목" + i)
                    .content("내용" + i)
                    .tags(tags)
                    .build();
            items.add(item);
        }
        boardDataRepository.saveAllAndFlush(items);
        em.clear();
    }
    @Test
    void test1(){
        BoardData data = boardDataRepository.findById(1L).orElse(null);
        List<HashTag> tags = data.getTags();
        tags.stream().forEach(System.out::println);
    }

    @Test
    void test2(){
        HashTag tag = hashTagRepository.findById(1L).orElse(null);
        List<BoardData> items = tag.getBoardDatas();
        items.stream().forEach(System.out::println);
    }

}
