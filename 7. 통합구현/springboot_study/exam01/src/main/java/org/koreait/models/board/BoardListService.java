package org.koreait.models.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.entities.QBoardData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListService {
    private final EntityManager em;

    public List<BoardData> getList(){
        QBoardData boardData = QBoardData.boardData; //querydsl  방식
        JPAQueryFactory factory = new JPAQueryFactory(em);

        JPAQuery<BoardData> query = factory.selectFrom(boardData)
                .leftJoin(boardData.user)
                .fetchJoin();
        List<BoardData> items = query.fetch();
        return items;
    }
}
