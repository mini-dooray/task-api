package com.minidooray.taskapi.project.entity;

import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.task.entity.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_seq")
    private Long seq;

    @Column(unique = true)
    private String name;

    private String content;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public static Project createByDto(RequestCreateProjectDto dto){
        Project project = new Project();
        project.setContent(dto.getContent());
        project.setName(dto.getName());
        project.setStatus(ProjectStatus.ACTIVE);
        return project;
    }
}
