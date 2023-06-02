package com.minidooray.taskapi.comment.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class CommentPeriod {
    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

    public LocalDate getRecentDate() {
        return Objects.isNull(lastUpdateDate) ? registeredDate : lastUpdateDate;
    }
}
