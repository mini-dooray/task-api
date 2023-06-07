package com.minidooray.taskapi.milestone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMilestonePeriod is a Querydsl query type for MilestonePeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMilestonePeriod extends BeanPath<MilestonePeriod> {

    private static final long serialVersionUID = -1829972703L;

    public static final QMilestonePeriod milestonePeriod = new QMilestonePeriod("milestonePeriod");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QMilestonePeriod(String variable) {
        super(MilestonePeriod.class, forVariable(variable));
    }

    public QMilestonePeriod(Path<? extends MilestonePeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMilestonePeriod(PathMetadata metadata) {
        super(MilestonePeriod.class, metadata);
    }

}

