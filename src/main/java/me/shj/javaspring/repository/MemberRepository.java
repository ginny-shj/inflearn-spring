package me.shj.javaspring.repository;

import me.shj.javaspring.domain.Member;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;


//Member의 프로퍼티 : id, name
//DB 연결하기 위한것 구현
public interface MemberRepository {

    //1. 회원 save 한다
    Member save(Member member); //함수 save : 입력Member, 리턴Member

    //2, 회원 find 한다. 단, null일 때를 대비해서 Optional. 결과값은 항상 member로
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    //3. 회원을 모두 찾는다 (리스트형식)
    List<Member> findAll();

}
