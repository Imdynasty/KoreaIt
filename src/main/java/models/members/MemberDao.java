package models.members;

import controllers.member.JoinForm;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class MemberDao {
   private final JdbcTemplate jdbcTemplate;

   public boolean register(JoinForm joinForm){
       String sql = "INSERT INTO MEMBER (USERNO, USERID, USERPW, USERNM, EMAIL, MOBILE)" +
               "VALUES(MEMBER_SEQ.nextval, ?, ?, ?, ?, ?)";
       String hash = BCrypt.hashpw(joinForm.getUserPw(), BCrypt.gensalt(12));

       int affectedRows = jdbcTemplate.update(sql, joinForm.getUserId(), hash,
                                              joinForm.getUserNm(),
                                              joinForm.getEmail(),
                                             joinForm.getMobile());
       return affectedRows > 0;
    }

    public Member get(String userId){
           try{
            String sql = "SELECT * FROM MEMBER WHERE userId =?";
             Member member = jdbcTemplate.queryForObject(sql, (rs,i)->{
              return Member.builder()
                      .userNo(rs.getLong("USERNO"))
                      .userId(rs.getString("USERID"))
                      .userPw(rs.getString("USERPW"))
                      .userNm(rs.getString("USERNM"))
                      .email(rs.getString("EMAIL"))
                      .mobile(rs.getString("MOBILE"))
                      .regDt(rs.getTimestamp("REGDT").toLocalDateTime())
                      .build();
             }, userId);
             return member;
           }catch (Exception e){
            e.printStackTrace();
            return null;
           }
    }

    public boolean exists(String userId){
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE userId=?";
        long cnt = jdbcTemplate.queryForObject(sql, long.class, userId);

        return cnt > 0;
    }
}
