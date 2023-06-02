package com.minidooray.taskapi.milestone.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Access(AccessType.FIELD)
public class MilestonePeriod {
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_Date")
    private LocalDate endDate;
}
