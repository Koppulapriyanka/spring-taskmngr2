package com.scaler.springtaskmngr2.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity {

    @Column(name = "body")
    private String body;

    @ManyToOne
    private TaskEntity task;
}
