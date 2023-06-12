package com.minidooray.taskapi.tag.entity;

import com.minidooray.taskapi.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_seq")
    private Long seq;
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_seq")
    private Project project;

}
