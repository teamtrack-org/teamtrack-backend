package com.teamtrack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Task.Status status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
