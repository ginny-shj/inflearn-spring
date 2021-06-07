package me.shj.javaspring.repository;

import me.shj.javaspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//스프링 데이터 Jpa가 자동으로 인터페이스에서 구현체로 만들어주고, Bean까지 등록해줌 -> Config파일 가서, bean 삭제해줘야
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //interface -> interface 여러개 상속 가능, implements(X) extends(O)

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
