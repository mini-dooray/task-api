package com.minidooray.taskapi.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonBackReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<ProjectMember> projectMemberList;

    public static Project createByDto(RequestCreateProjectDto dto) {
        return Project.builder()
                .content(dto.getContent())
                .name(dto.getName())
                .status(ProjectStatus.ACTIVE).build();
    }

    public void setProjectMember(ProjectMember projectMember) {
        this.projectMemberList = List.of(projectMember);
    }

    @Builder
    public Project(String name, String content, ProjectStatus status, List<ProjectMember> projectMemberList) {
        this.name = name;
        this.content = content;
        this.status = status;
        this.projectMemberList = projectMemberList;
    }

    public void modifyProjectBYDto(RequestUpdateProjectDto dto) {
        this.name = dto.getName();
        this.content = dto.getContent();
        this.status = dto.getStatus();
    }
}
