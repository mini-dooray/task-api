package com.minidooray.taskapi.tag.entity;

import com.minidooray.taskapi.project.entity.Project;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    @Builder
    public Tag(String name, Project project) {
        this.name = name;
        this.project = project;
    }
    public void updateTagName(String name){
        this.name = name;
    }
}
