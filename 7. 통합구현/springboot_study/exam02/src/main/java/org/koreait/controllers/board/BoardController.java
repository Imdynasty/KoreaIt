package org.koreait.controllers.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.PostData;
import org.koreait.repositories.BoardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository repository;

    @GetMapping("/board/test1")
    public void test1() {
        PostData data = PostData.builder()
                .subject("제목")
                .content("내용")
                .build();

        repository.saveAndFlush(data);
    }

    @GetMapping("/board/test2")
    public void test2() {
        PostData data = repository.findById(2L).orElse(null);
        data.setSubject("(수정)제목");
        repository.flush();
    }

    @PostMapping("/board")
    public void test3() {
        System.out.println("CSRF 테스트");
    }
}