package com.teamtrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Project project;

    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
