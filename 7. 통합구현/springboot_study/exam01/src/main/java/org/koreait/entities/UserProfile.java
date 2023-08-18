package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class UserProfile {
    @Id @GeneratedValue
    private Long id;
    private String profileImage;

    @OneToOne(mappedBy = "profile")
    private Users user;
}
