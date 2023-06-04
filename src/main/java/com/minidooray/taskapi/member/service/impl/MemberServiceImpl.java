package com.minidooray.taskapi.member.service.impl;

import com.minidooray.taskapi.member.dto.RequestMemberDto;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.DuplicateMemberSeqException;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO 1 : account api에서 맴버 이름이 변경,삭제되거나 맴버가 한명 추가될때마다 memberService 도 동작을 해야함.
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new NotFoundMemberException();
        }
        memberRepository.deleteById(id);
    }

    public void createMember(RequestMemberDto dto) {
        if (memberRepository.existsById(dto.getSeq())) {
            throw new DuplicateMemberSeqException();
        }
        memberRepository.saveAndFlush(new Member(dto.getSeq(), dto.getName()));
    }

    public void updateMemberName(RequestMemberDto dto) {
        Member member = memberRepository.findById(dto.getSeq())
                .orElseThrow(NotFoundMemberException::new);
        member.setName(dto.getName());
    }
}
