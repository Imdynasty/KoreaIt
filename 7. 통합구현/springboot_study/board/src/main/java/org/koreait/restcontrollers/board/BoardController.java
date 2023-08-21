package org.koreait.restcontrollers.board;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.rests.JSONData;
import org.koreait.controllers.BoardForm;
import org.koreait.entities.BoardData;
import org.koreait.models.board.BoardInfoService;
import org.koreait.models.board.BoardSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("apiBoardController")
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardSaveService saveService; //저장
    private final BoardInfoService infoService; //조회

    @PostMapping
    public ResponseEntity<Object> write(@RequestBody BoardForm form) {

        saveService.save(form);

        // 등록 성공
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Object> update(@RequestBody BoardForm form) {

        return null;
    }


    @GetMapping("/view/{id}")
    //이부분 부터 조회기능
    public ResponseEntity<JSONData<Object>> view(@PathVariable Long id){
        BoardData boardData = infoService.get(id);

        JSONData<Object> data = JSONData.builder()
                .success(true)
                .data(boardData)
                .build();

        return ResponseEntity.ok(data);
    }
}