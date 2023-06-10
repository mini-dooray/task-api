package com.minidooray.taskapi.member.service.impl;

import com.minidooray.taskapi.member.dto.request.RequestMemberDto;
import com.minidooray.taskapi.member.dto.request.RequestUpdateMemberDto;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.DuplicateMemberSeqException;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public void deleteMember(Long memberSeq) {
        memberRepository.deleteById(memberSeq);
    }

    public void createMember(RequestMemberDto dto) {
        if (memberRepository.existsById(dto.getSeq())) {
            throw new DuplicateMemberSeqException();
        }
        memberRepository.saveAndFlush(new Member(dto.getSeq(), dto.getName()));
    }

    public void updateMemberName(RequestUpdateMemberDto dto, Long memberSeq) {
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(NotFoundMemberException::new);
        member.modifyMemberName(dto.getName());
    }
}
