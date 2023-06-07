package com.minidooray.taskapi.priority.entity;

import javax.persistence.*;

//TODO 2 : 존재 여부를 다시 생각해보기
@Entity
@Table(name = "priority")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_seq")
    private Long seq;

    @Column(name = "name", unique = true)
    private PriorityStatus priorityStatus;
}
