package com.minidooray.taskapi.tasktag.entity;

import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.task.entity.Task;

import javax.persistence.*;

@Entity
@Table(name = "task_tag")
public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "tag_seq")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "task_seq")
    private Task task;
}
