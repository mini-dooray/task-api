package com.minidooray.taskapi.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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


    //라스터 업데이트 날짜 출력 메소드

    public LocalDate getRecentDate() {
        return Objects.isNull(lastUpdateDate) ? registeredDate : lastUpdateDate;
    }
}
