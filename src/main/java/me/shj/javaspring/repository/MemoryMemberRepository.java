package me.shj.javaspring.repository;

import me.shj.javaspring.domain.Member;

import java.util.*;

//interface에 있는 모든 매소드 override
public class MemoryMemberRepository implements MemberRepository{

    //주인공 : store, 정체는 map!
    //Save-> key, value를 받는 Map 이 있어야
    private static Map<Long, Member> store = new HashMap<>(); //id, Member객체{id, name} //static 을 써줘야 클래스변수가 됨!!
    private static Long sequence = 0L; //id 를 이거로 넣을 것


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //sequence로 id 저장
        store.put(sequence, member); //저장기능
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values() // 모든 member가 컬렉션객체[]로 나옴
                    .stream() //하나씩 루프를 돌면서
                    .filter(member -> member.getName().equals(name)) //member객체의 name이 받은 name과 같다면 필터링 해서
                    .findAny(); //찾은걸 뱉어라
                                // 두개 이상이면 어떻게??? 자료구조가?
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

    //잘 개발했는지 확인하기 위해서, Test case 작성하기
}
