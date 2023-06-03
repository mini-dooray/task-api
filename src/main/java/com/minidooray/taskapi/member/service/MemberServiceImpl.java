package com.minidooray.taskapi.member.service;

import com.minidooray.taskapi.member.dto.request.RequestCreateMemberDto;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO 1 : account api에서 맴버 이름이 변경,삭제되거나 맴버가 한명 추가될때마다 memberService 도 동작을 해야함.
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new NotFoundMemberException();
        }
        memberRepository.deleteById(id);
    }
    public void updateMember(RequestCreateMemberDto dto){
        
    }
}
