package com.scaler.springtaskmngr2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }
}
