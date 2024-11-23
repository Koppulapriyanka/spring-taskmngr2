package com.scaler.springtaskmngr2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    private Boolean completed;

    @Column(name = "due_date", nullable = true)
    private Date dueDate;

    /*@OneToMany(mappedBy = "task")
    private List<NoteEntity> notes;*/

    public TaskEntity() {
        super();
    }
    public TaskEntity(Integer id) {
        super(id);
    }
}
