package me.shj.javaspring;


import me.shj.javaspring.repository.MemberRepository;
import me.shj.javaspring.repository.MemoryMemberRepository;
import me.shj.javaspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberSerivce(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
