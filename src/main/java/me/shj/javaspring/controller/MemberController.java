package me.shj.javaspring.controller;

import me.shj.javaspring.domain.Member;
import me.shj.javaspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//<스프링의 정형화된 패턴>
//1. Controller : 외부 요청을 받고
//2. Service : 비즈니스 로직 만들고
//3. Repository : 데이터 저장하고

//@Component (@Controller, @Service, @Repository: 찾아서 객체 하나씩 생성)
//@Autowired : 다음 객체로 연결해주는 화살표 기능 = 의존관계 생성
@Controller
public class MemberController {

//    private final MemberService memberService = new MemberService(); //이렇게 하면 매번 인스턴스를 새로 만들기 때문에

//    @Autowired  private final MemberService memberService; //의존관계 주입 -> 필드 주입방법
    private final MemberService memberService;

    @Autowired //다음 필요한 memberService 연결해줌
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    /*
    1. 회원 등록
     */
    @GetMapping("/members/new") //여기로 url엔터 쳤으면
    public String createForm(){
        return "members/createForm"; //여기 html파일로 가라
    }

    @PostMapping("/members/new") //여기html파일에서 form -> post 가 호출이 되었으면
    public String create(MemberForm form){
        //1. 맴버 생성
        Member member = new Member();
        member.setName(form.getName());
        //2. 가입시키기
        memberService.join(member);
        //3. 홈화면으로 보내
        return "redirect:/";
    }

    /*
    2. 회원 조회
     */
    @GetMapping("/members")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


}
