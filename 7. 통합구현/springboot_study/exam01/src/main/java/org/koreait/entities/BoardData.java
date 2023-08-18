package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardData extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @Column(length=45, nullable = false)
    private String poster;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    private long hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_no")
    @ToString.Exclude
    private Users user;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<HashTag> tags = new ArrayList<>();
}
