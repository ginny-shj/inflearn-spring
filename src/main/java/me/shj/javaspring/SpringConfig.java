package me.shj.javaspring;


import me.shj.javaspring.aop.TimeTraceAop;
import me.shj.javaspring.repository.*;
import me.shj.javaspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
////jdbc
//    private final DataSource dataSource;
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//// jpa
//    private final EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    //스프링데이터jpa
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberSerivce(){
        return new MemberService(memberRepository);
    }


////스프링데이터jpa는 자동으로 빈등록하기 때문에, 삭제
//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);정
//        return new JpaMemberRepository(em);
//    }

//    //AOP   왜 오류나지?????
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
