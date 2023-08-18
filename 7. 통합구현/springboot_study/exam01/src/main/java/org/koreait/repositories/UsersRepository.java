package org.koreait.repositories;

import org.koreait.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUserId(String userId);

    List<Users> findByRegDtBetweenOrderByRegDtDesc(LocalDateTime sdate, LocalDateTime edate);

    List<Users> findByUserNmContainingOrderByRegDtDesc(String keyword);

    @Query("SELECT u FROM Users u WHERE u.userNm LIKE %:key% ORDER BY u.regDt DESC")
    List<Users> getUsers1(@Param("key") String keyword);
}
