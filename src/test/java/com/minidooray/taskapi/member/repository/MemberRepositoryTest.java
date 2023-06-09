package com.minidooray.taskapi.member.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
class MemberRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        //given
        Member member = TestUtils.member();

        //when
        memberRepository.save(member);

        //then
        Member find = entityManager.find(Member.class, member.getSeq());

        assertThat(find.getName()).isEqualTo(member.getName());
        assertThat(find.getSeq()).isEqualTo(member.getSeq());
    }

    @Test
    void deleteById() {
        //given
        Member member = TestUtils.member();

        entityManager.persist(member);

        //when
        memberRepository.deleteById(member.getSeq());
        Member find = entityManager.find(Member.class, member.getSeq());

        //then
        assertThat(find).isNull();
    }

    @Test
    void existsById() {
        //given
        Member member = TestUtils.member();

        entityManager.persist(member);

        //when
        boolean check = memberRepository.existsById(1L);

        //then
        assertThat(check).isTrue();
    }

    @Test
    void findById() {
        //given
        Member member = TestUtils.member();

        entityManager.persist(member);

        //when
        Member find = memberRepository.findById(member.getSeq())
                .orElseThrow(NotFoundMemberException::new);

        //then
        assertThat(find.getSeq()).isEqualTo(member.getSeq());
        assertThat(find.getName()).isEqualTo(member.getName());
    }
}