package me.shj.javaspring;


import me.shj.javaspring.repository.JdbcMemberRepository;
import me.shj.javaspring.repository.MemberRepository;
import me.shj.javaspring.repository.MemoryMemberRepository;
import me.shj.javaspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberSerivce(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }

}
