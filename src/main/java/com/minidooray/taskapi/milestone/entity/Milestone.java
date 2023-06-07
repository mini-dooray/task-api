package com.minidooray.taskapi.milestone.entity;

import com.minidooray.taskapi.milestone.dto.request.RequestMilestoneDto;
import com.minidooray.taskapi.project.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * milestone table 입니다.
 */
@Entity
@Table(name = "milestone")
@Getter
@NoArgsConstructor
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_seq")
    private Long seq;

    @Column(unique = true)
    private String name;

    @Embedded
    private MilestonePeriod period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_seq")
    private Project project;

    @Builder
    public Milestone(String name, MilestonePeriod period, Project project) {
        this.name = name;
        this.period = period;
        this.project = project;
    }

    public void updateMilestoneByDto(RequestMilestoneDto dto) {
        this.name = dto.getName();
    }
}
