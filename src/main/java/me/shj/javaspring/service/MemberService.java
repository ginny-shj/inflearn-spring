package me.shj.javaspring.service;

import me.shj.javaspring.domain.Member;
import me.shj.javaspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//서비스 제공할 것 구현
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired //다음 필요한 repository 연결해줌
    public MemberService (MemberRepository memberRepository){
        this.memberRepository = memberRepository; // 생성자 : memberRepository를 인스턴스 필드로
    }
    /*
    1. 회원가입
     */
    public Long join(Member member){
        //중복회원반환
        validateDulicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDulicateMember(Member member) {
        memberRepository.findByName(member.getName()) //oprional 반환 -> ifNull 이 아니라, ifPresent 쓸 수 있음
                .ifPresent( m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
        /*
    2. 전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
