package com.minidooray.taskapi.member.service;

import com.minidooray.taskapi.member.dto.request.RequestMemberDto;
import com.minidooray.taskapi.member.dto.request.RequestUpdateMemberDto;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.DuplicateMemberSeqException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.member.service.impl.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void deleteMember() {
        //given
        Long memberSeq = 1L;

        //when
        memberService.deleteMember(memberSeq);

        //then
        verify(memberRepository).deleteById(memberSeq);
    }

    @Test
    @DisplayName("createMember 성공했을때")
    void createMember() {
        //given
        RequestMemberDto dto = new RequestMemberDto(10L, "test");

        //when
        when(memberRepository.existsById(dto.getSeq()))
                .thenReturn(false);

        memberService.createMember(dto);

        //then
        verify(memberRepository).existsById(dto.getSeq());
    }

    @Test
    @DisplayName("createMember 실패했을때")
    void createMemberFail() {
        //given
        RequestMemberDto dto = new RequestMemberDto(10L,"test");

        //when
        when(memberRepository.existsById(dto.getSeq()))
                .thenReturn(true);

        try {
            memberService.createMember(dto);
        } catch (DuplicateMemberSeqException e) {
            //then
            Assertions.assertThat(e.getClass()).isEqualTo(DuplicateMemberSeqException.class);
        }
    }

    @Test
    void updateMemberName() {
        //given
        Long memberSeq = 10L;
        RequestUpdateMemberDto dto = new RequestUpdateMemberDto("test");

        Optional<Member> member = Optional.of(new Member());

        //when
        when(memberRepository.findById(memberSeq))
                .thenReturn(member);
        memberService.updateMemberName(dto, memberSeq);

        //then
        verify(memberRepository).findById(memberSeq);
    }
}