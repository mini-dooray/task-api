package com.minidooray.taskapi.tag.entity;

import com.minidooray.taskapi.project.entity.Project;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "tag_seq")
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_seq")
    private Project project;

}
