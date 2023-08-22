package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class PostData extends BaseMemberEntity{
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;
}
