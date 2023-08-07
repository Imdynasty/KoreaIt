package models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
//@Transactional
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public boolean register(BoardData boardData) {
        String sql = "INSERT INTO BOARD_DATA (ID, POSTER, SUBJECT, CONTENT) " +
                " VALUES(BOARD_DATA_SEQ.nextval, ?, ?, ?)";
        /*
        int affectedRows = jdbcTemplate.update(sql, boardData.getPoster(), boardData.getSubject(), boardData.getContent());
        */
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"ID"});
                pstmt.setString(1, boardData.getPoster());
                pstmt.setString(2, boardData.getSubject());
                pstmt.setString(3, boardData.getContent());

                return pstmt;
            }
        }, keyHolder);

        Number key = keyHolder.getKey();
        long ID = key.longValue();
        boardData.setId(ID);

        return affectedRows > 0;
    }

    public List<BoardData> gets() {
        String sql = "SELECT * FROM BOARD_DATA ORDER BY REGDT DESC";
        List<BoardData> datas = jdbcTemplate.query(sql, this::mapper);

        return datas;
    }

    public BoardData get(long id) {
        try {
            String sql = "SELECT * FROM BOARD_DATA WHERE ID = ?";
            BoardData data = jdbcTemplate.queryForObject(sql, this::mapper, id);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public long getTotal() {
        String sql = "SELECT COUNT(*) FROM BOARD_DATA";
        long total = jdbcTemplate.queryForObject(sql, long.class);

        return total;
    }

    private BoardData mapper(ResultSet rs, int i) throws SQLException {
        return BoardData.builder()
                .id(rs.getLong("ID"))
                .poster(rs.getString("POSTER"))
                .subject(rs.getString("SUBJECT"))
                .content(rs.getString("CONTENT"))
                .regDt(rs.getTimestamp("REGDT").toLocalDateTime())
                .build();
    }

}