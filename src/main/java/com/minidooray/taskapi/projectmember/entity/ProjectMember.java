package com.minidooray.taskapi.projectmember.entity;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.project.entity.Project;

import javax.persistence.*;

@Entity
@Table(name = "project_member")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_member_seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "project_seq")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @Enumerated(EnumType.ORDINAL)
    private ProjectMemberAuthority authority;
}
