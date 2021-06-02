package me.shj.javaspring.service;

import me.shj.javaspring.domain.Member;
import me.shj.javaspring.repository.MemberRepository;
import me.shj.javaspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트에 해당되는 class 부르기
    MemberService memberService;
    MemoryMemberRepository memberRepository;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //서로 다른 DB를 보고 있다는 것이 위험

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 이렇게 생성자를 만들고 나면, 위의 필드에 값이 들어감!
        //Dependency Injection : 의존성 주입 (memberService는 memberRepository에 의존??)
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("ginny");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(findMember.getName(), member.getName());

    }

    @Test
    void 중복확인(){
        //given
        Member member1 = new Member();
        member1.setName("ginny");

        Member member2 = new Member();
        member2.setName("ginny");

        //when
        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try{
//            memberService.join(member2);
//        } catch(IllegalStateException e) {
//            Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage())
//        }


    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}