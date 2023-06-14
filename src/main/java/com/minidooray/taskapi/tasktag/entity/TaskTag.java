package com.minidooray.taskapi.tasktag.entity;

import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_tag")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @Builder
    public TaskTag(Tag tag, Task task) {
        this.tag = tag;
        this.task = task;
    }
}
