package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class HashTag {
    @Id @GeneratedValue
    private Long id;

    private String tag;

    @ManyToMany(mappedBy = "tags")
    @ToString.Exclude
    private List<BoardData> boardDatas = new ArrayList<>();
}
