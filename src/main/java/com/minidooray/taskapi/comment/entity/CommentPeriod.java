package com.minidooray.taskapi.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentPeriod {
    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

//    @PrePersist
//    public LocalDate getRecentDate() {
//        return Objects.isNull(lastUpdateDate) ? registeredDate : lastUpdateDate;
//    }
}
