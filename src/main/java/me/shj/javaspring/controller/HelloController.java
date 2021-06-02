package me.shj.javaspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //controller 매소드
public class HelloController {

    //1. 정적컨텐츠
    @GetMapping("hello") //get 매소드 연결
    public String hello(Model model){ //controller 매소드는 model 객체를 파라미터로 받을 수 있음
        model.addAttribute("data", "hello!");
        return "hello";
    }

    //2. MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(value="name", required=false) String name, Model model){
        //새로운 파라미터 name 을 받는다, parameter 필요한 거 뭐있는지 보기
        //http://localhost:8080/hello-mvc?name=hi (get방식으로 name에 data넣는 방법)
        model.addAttribute("name", name);
        return "hello-template";
    }

    //3-1. API : string 형식
    // View가 필요 없다! reuturn 값을 그래도 웹브라우저에 내려줌
    @GetMapping("hello-string")
    @ResponseBody //API에서는 http body에 그대로 전송
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //String 값을 그대로 내려줌
    }

    //3-2. API : class 형식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 반환 -> json 형식으로 자동으로 변환되어 내림
    }

    static public class Hello { //static : class 안의 class
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }


}
