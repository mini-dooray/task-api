package com.minidooray.taskapi.member.repository;

import com.minidooray.taskapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Member 테이블을 데이터베이스에서 다루기위한 Repository 클래스 입니다.
 *
 */
public interface MemberRepository extends JpaRepository<Member,Long> {

}
