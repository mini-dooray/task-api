package com.minidooray.taskapi.task.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@Access(AccessType.FIELD)
public class TaskPeriod {
    @Column(name = "registered_date")
    private LocalDate registeredDate;
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

    public LocalDate getRecentDate() {
        return Objects.isNull(lastUpdateDate) ? registeredDate : lastUpdateDate;
    }

    @Builder
    public TaskPeriod(LocalDate registeredDate, LocalDate lastUpdateDate) {
        this.registeredDate = registeredDate;
        this.lastUpdateDate = lastUpdateDate;
    }
}
