package com.minidooray.taskapi.task.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Setter
@Access(AccessType.FIELD)
public class TaskPeriod {
    @Column(name = "registered_date")
    private LocalDate registeredDate;
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

    public LocalDate getRecentDate(){
        return Objects.isNull(lastUpdateDate) ? registeredDate : lastUpdateDate;
    }
}
