package me.shj.javaspring.repository;

import me.shj.javaspring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//public 안해줘도 됨 -> test니까 다른 곳에서 쓸 일이 없음
class MemoryMemberRepositoryTest {

    //객체를 만들고
    MemoryMemberRepository repository = new MemoryMemberRepository(); //repository인스턴스는 store를 갖고 있음

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){ //앞의 save 함수가 잘 작동하는지
        //given
        Member member = new Member();
        member.setName("ginny");

        //when
        repository.save(member); //test하고자하는 함수 save
        Member result = repository.findById(member.getId()).get(); //값 확인
        // .get() : Optional 반환타입때문

        //then
        Assertions.assertEquals(result, member);
    }
    @Test
    public void findById(){ //앞의 save 함수가 잘 작동하는지
        //given
        Member member1 = new Member();
        member1.setName("ken");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("bean");
        repository.save(member2);

        //when
        Member result = repository.findById(member1.getId()).get();

        //then
        Assertions.assertEquals(result, member1);


    }
    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("ken");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("bean");
        repository.save(member2);

        //when
        Member result = repository.findByName(member1.getName()).get();

        //then
        Assertions.assertEquals(result, member1);

    }
    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("ken");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("bean");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        Assertions.assertEquals(result.size(), 2); //결과를 직접 확인안해도, SIZE로 판단할 수도
    }

}
