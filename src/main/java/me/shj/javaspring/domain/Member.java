package me.shj.javaspring.domain;

import javax.persistence.*; //jpa 설정

@Entity //jpa가 관리하는 entity다
public class Member {
    //pk 설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //전략 identity = db가 자동으로 id를 생성해준다
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
