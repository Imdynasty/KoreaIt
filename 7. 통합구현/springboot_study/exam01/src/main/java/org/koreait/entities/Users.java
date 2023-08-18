package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;
import org.koreait.contants.UserType;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name ="MEMBER",
    indexes = {
        @Index(name="idx_Users_regDt", columnList = "regDt DESC"),
        @Index(name="idx_Users_userNm", columnList = "userNm")
    })
public class Users extends BaseEntity{

    @Id @GeneratedValue
    private Long userNo;
   // @Column(name = "memId")
    @Column(length=45 , unique = true, nullable = false)
    private String userId;
    @Column(length=65 , nullable = false)
    private String userPw;
    @Column(length=45 , nullable = false)
    private String userNm;

    @Transient// 엔티티 내부에서만 쓸목적
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(length=10,nullable = false)
    private UserType type = UserType.USER;




}
