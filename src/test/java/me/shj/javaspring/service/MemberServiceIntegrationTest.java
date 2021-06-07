package me.shj.javaspring.service;

import me.shj.javaspring.domain.Member;
import me.shj.javaspring.repository.MemberRepository;
import me.shj.javaspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest //Spring컨터이너를 같이 띄워서 테스트
@Transactional  //db에 커밋하지 않고 롤백
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    //    @Commit //이거 하면, 저장이 됨
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
