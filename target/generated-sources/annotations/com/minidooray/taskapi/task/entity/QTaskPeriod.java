package com.minidooray.taskapi.task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTaskPeriod is a Querydsl query type for TaskPeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTaskPeriod extends BeanPath<TaskPeriod> {

    private static final long serialVersionUID = 2066676357L;

    public static final QTaskPeriod taskPeriod = new QTaskPeriod("taskPeriod");

    public final DatePath<java.time.LocalDate> lastUpdateDate = createDate("lastUpdateDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> registeredDate = createDate("registeredDate", java.time.LocalDate.class);

    public QTaskPeriod(String variable) {
        super(TaskPeriod.class, forVariable(variable));
    }

    public QTaskPeriod(Path<? extends TaskPeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskPeriod(PathMetadata metadata) {
        super(TaskPeriod.class, metadata);
    }

}

