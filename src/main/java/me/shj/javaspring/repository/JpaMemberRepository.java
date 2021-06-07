package me.shj.javaspring.repository;

import me.shj.javaspring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //이렇게 간단한 문이 sql, 저장, setId 모든걸 다 해줌
        em.persist(member); //persist(영구) 저장한다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //pk값인 id 넣으면 됨
        return Optional.ofNullable(member); //값이 없을 수도 있기 때문에
    }

    //pk기반이 아닌 다른 것들은, createQuery로 쿼리를 작성해줘야 -> 스프링 데이터 JPA 는 이것도 안해도 됨
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class) //테이블에서 아니라, 객체에서 찾기
                .getResultList();
    }
}
