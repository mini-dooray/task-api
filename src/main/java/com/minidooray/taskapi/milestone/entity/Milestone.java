package com.minidooray.taskapi.milestone.entity;

import com.minidooray.taskapi.project.entity.Project;
import lombok.Getter;

import javax.persistence.*;

/**
 * milestone table 입니다.
 */
@Entity
@Table(name = "milestone")
@Getter
public class Milestone {
    @Id
    @Column(name = "milestone_seq")
    private Long seq;

    @Column(unique = true)
    private String name;

    @Embedded
    private MilestonePeriod period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_seq")
    private Project project;
}
