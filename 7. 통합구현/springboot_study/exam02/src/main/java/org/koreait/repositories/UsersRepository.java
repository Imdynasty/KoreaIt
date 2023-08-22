package org.koreait.repositories;

import org.koreait.entities.Users;
import org.koreait.entities.QUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UsersRepository extends JpaRepository<Users, Long>, QuerydslPredicateExecutor<Users> {
    Users findByUserId(String userId);

    default boolean exists(String userId) {
        QUsers qusers = QUsers.users;
        return exists(qusers.userId.eq(userId));
    }
}