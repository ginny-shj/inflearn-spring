package me.shj.javaspring.controller;

public class MemberForm {
    private String name; //members/createForm.html 파일의 10번째 줄 name="name"과 스프링이 연결시켜줌

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
