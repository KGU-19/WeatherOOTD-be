package com.example.weatherootd.service;

import com.example.weatherootd.domain.Member;
import com.example.weatherootd.reposotory.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository = new MemberRepository();

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){
        validateDuplicate(member);

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicate(Member member) {
        if(memberRepository.findByName(member.getName()) != null){
            throw new IllegalArgumentException("이미 사용중인 이름");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 아이디로 조회
     * @param memberId
     * @return
     */
    public Member findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId);
        if(member == null){
            throw new IllegalArgumentException("해당 ID의 멤버를 찾을 수 없음");
        }
        return member;
    }

    /**
     * 회원 이름으로 조회
     * @param username
     * @return
     */
    public Member findMemberByUsername(String username) {
        Member member = memberRepository.findByName(username);

        if(member == null){
            throw new IllegalArgumentException("해당 이름의 멤버를 찾을 수 없음");
        }
        return member;
    }

    /**
     * 멤버 수정
     * @param memberId
     * @param inputMember
     */
    public void updateMember(Long memberId, Member inputMember){
        Member member = findMemberById(memberId);
        memberRepository.update(memberId, inputMember);
    }

    /**
     * 멤버 삭제
     * @param memberId
     */
    public void deleteMember(Long memberId){
        Member memberById = findMemberById(memberId);
        memberRepository.delete(memberId);
    }
}
