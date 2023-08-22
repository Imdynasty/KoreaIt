package org.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseMemberEntity extends BaseEntity {
    @CreatedBy
    @Column(updatable = false, length = 45)
    private String createdBy;
    @LastModifiedDate
    @Column(insertable = false, length = 45)
    private String modifiedBy;
}
