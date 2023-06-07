package com.minidooray.taskapi.member.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * task, project 생성 및 참조, 당담자, 작성자 일때 필요한 맴버정보를 가지고 있는 객체입니다.
 */
@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_seq")
    private Long seq;

    private String name;

    public void modifyMemberName(String name){
        this.name = name;
    }
}
