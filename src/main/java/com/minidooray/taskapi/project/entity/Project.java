package com.minidooray.taskapi.project.entity;

import com.minidooray.taskapi.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "project")
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_seq")
    private Long seq;

    @Column(unique = true)
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProjectStatus status;

}
