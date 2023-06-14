package com.minidooray.taskapi.priority.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "priority")
@Getter
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_seq")
    private Long seq;

    @Column(name = "name")
    @Enumerated(EnumType.ORDINAL)
    private PriorityStatus priorityStatus;

    @Builder
    public Priority(PriorityStatus priorityStatus) {
        this.priorityStatus = priorityStatus;
    }
}
