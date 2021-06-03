# inflearn-spring
# 1. 프로젝트 환경설정

### 프로젝트 생성

- spring boot 환경설정 : start.spring.io
- main 함수 실행하면, 
@SpringBootApplication : 스프링부트 앱을 실행 (tomcat웹서버 내장 → run 하면 실행하면서 웹서버 활성화)
localhost:8080 쳐보기
- 설정 preference : gradle이 아니라, intellij로 해야 좀더 빠름
- **빌드를 위한 버전 정리** 
start.spring.io가 아니라, new project에서 가져옴
    - [gradle-wrapper.properties](http://gradle-wrapper.properties) > gradle-6.7.1-bin.zip
    (8버전은 intelliJ에서 지원 안함)
    - build.gradle.kts > plugins > springframework.boot version 2.4.2
    (gradle에 맞는 springboot버전)
    - java version 8로 했음 (11도 되나 확인)

### 라이브러리

- 라이브러리 몇개 없음 : 하지만 사실 gradle이 일을 다 하고 있었던 것
- logging 라이브러리 : pirntln 하면 안됨!! log로 남겨야 심각한 에러만 관리할 수 있음 
springboot logging → logback, slf 두 라이브러리를 많이 씀
- test 라이브러리 : 자바(JUnit 5버전) assertj, ...
spring-test : 스프링과 통합해서 테스트해주는 라이브러리

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c0b78dfa-f531-4107-9fc9-9072ea063dfc/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c0b78dfa-f531-4107-9fc9-9072ea063dfc/Untitled.png)

### View 환경설정

- welcome 페이지 만들기 
resources/static/index.html 에 만들면, welcome페이지로 만들어줌
spring,io > reference 찾으면 다 나와있음! 메뉴얼에서 검색할 줄 알아야 (resources디렉토리에서 index.html을 먼저 찾는다)
- static : 정적 파일 = 루프도 없고 코딩이 아닌 것
- thymeleaf : 템플릿 엔진 종류 → 동적으로 바꿀 수 있음 (thymelaef.org)

### 빌드하고 실행 (Terminal)

1. ./gradlew build 
쉘스크립트 명령어 써있음  -  build 시작
2. build/libs 에 jar 파일 생긴 것을 볼 수 있음
3. java -jar 파일명.jar   ⇒  실행가능한 jar파일

# 2. 스프링 웹개발 기초

### 정적 컨텐츠

- 서버에서 하는게 없이 파일을 그대로 웹브라우저에 내려주는 것 (index.html처럼)
- 하는 방법 : 
>> intelliJ에서 resorces > static > file.html 넣기 
>> 웹브라우저에서 [localhost:8080/file.html](http://localhost:8080/file.html)
- 돌아가는 방식 )
    1. 웹브라우저 localhost:8080/file.html
    2. controller우선순위 : file 관련 controller를 스프링 컨테이너에서 찾는다
    3. 그다음 우선순위인 static/file.html을 찾는다

**정적컨텐츠가 아니라면, 이걸 html로 내리느냐 = mvc, 데이터로 바로 내리느냐 =  api**

### MVC와 템플릿 엔진

- html을 서버에서 프로그램한 뒤, 동적으로 바꿔서 내려주는 것 (controller, view, ...)
- MVC 방식 
1. controller : 서버와 비즈니스 로직
2. view  : 화면 출력
- 실습 java-spring 파일 : 새로운 컨트롤러와 뷰 만들기
- 돌아가는 방식 )
1. 웹브라우저 localhost:8080/hello-mvc
2. 스프링 컨테이너에서 @Getmapping (hello-mvc) 찾음
3. return hello-template, model(name:hi) : model을 통해 key value값을 줌
4. 스프링 컨테이너에서 viewResolver : templates/hello-template.html파일을 찾는다
5. html변환 후, 웹브라우저에 줌

### API

- 클라이언트(아이폰, 갤럭시 등)
- xml등이 아니라, json  dataformat으로 데이터를 내려주는 것
- 방식1 : view, react, 등을 쓸 때도 api로 데이터를 주면, 화면을 클라이엍느가 내림
- 방식2 : 서버끼리 통신 - html필요가 없고, 데이터만 흐르면 되기 때문에
- View 가 필요가 없다!
- 돌아가는 방식 )
1. 웹브라우저 localhost:8080/hello-api
2. 스프링 컨테이너에서 @Getmapping (hello-api) 찾음
3. return hello(name:me) : @ResponseBody 를 통해 key value값을 줌
4. 스프링 컨테이너에서 HttpMessageConverter : json변환기, 또는 String 변환기를 통해 key, value 값을 보냄
5. {key : value} json형식으로 웹브라우저에 직접 줌

# 3. 백앤드 개발 - 회원관리 예제

### 비즈니스 요구사항 정리

가장 단순한거로!

- 데이터 (**리포지토리, 도메인**): 회원id, 이름
- 기능 (**서비스**) : 회원가입, 조회
- DB 미정이라는 시나리오 : interface로 리포지토리 설계

웹어플리케이션 계층구조

- 컨트롤러 → 서비스 → 리포지토리 → DB  + (도메인)
    - **도메인** : 비즈니스 도메인 객체 (회원 등을 DB에 저장하고 관리)
    - **서비스** : 핵심 비즈니스 로직 (회원가입, 조회)
    - **리포지토리** : DB접근, 도메인 객체를 DB에 저장및관리

클래스 의존관계

- MemeberService ⇒ MemberRepository (interface) ←MemoryMemberRepository

### 회원 domain, repository, service 만들기 및 테스트

- domain > Member 데이터클래스
- repository > MemberRepository & test만들기
- service > 
(repository를 중심으로 비즈니스 로직 구현)

# 4. 스프링 빈과 의존관계

스프링 빈 등록!

### 컴포넌트 스캔과 자동 의존관계 설정

어노테이션을 찾아서, 자동으로 컴포넌트 스캔과 의존관계 설정해줌

1. @Controller, @Service, @Repository : 컨테이너 등록
2. @Autowired : 화살표 등록 (의존관계 설정)

- **memberService를 인스턴스 여러개 둘 필요가 없음** 
따라서, 스프링 컨터이너에 등록을 하고 쓰기 → 인스턴스 만들지 말고, 생성자로 + @Autowired + 자바클래스에 @Service, @Repository
- 실행 기준 : main 함수의 package 
package me.shj.javaspring; 
여기 파일에서만 Component 스캔함
- 스프링 빈을 등록할 때, 기본은 모두 싱글톤이다 = 인스턴스로 유일하다

### 자바코드로 직접 스프링 빈 등록하기

Config파일에서 @Bean으로 설정

- 원래는 자바코드가 아니라, xml파일로 설정했었음
- Dependency Injection의 3가지 방법
    1. 생성자 호출하면서 ⇒ 지금의 방법
    2. 필드 주입 : 생성자 쓰지 않고, @Autowired private MemberService memberSerivce;
    하지만 별로 좋지 않은 방법 
    3. 세터 주입 : 세터와 생성을 통한 주입 
    단점 : public이기 때문에 노출되어서 위험이 있음 

<두가지 방법 중에 무엇을 선택하나?>

- 정형화된 상황 = 컴포넌트 스캔
- 비정형화된 상황, 상황에 따라 구현 클래스를 변경해야하는 상황 = 자바 코드로 직접 설정
나중에 우리가 Repository를 바꿀 것이기 때문에 자바코드로 할 것임! = 다른 코드를 전혀 손 볼 필요가 없이 가볍게 바꿔치기 할 수 있음

# 5. 웹 MVC 개발

: 회원관리 예제

MemberController 활용

실습

- 홈화면 추가 : HomeController
- 등록 : MemberController, MembrerForm
    - get : url 엔터, 조회할 때
    - post : 정보를 입력해서 전달할 때
- 조회 : MemberController (Model 이용)

# 6. 스프링 DB 접근 기술

### H2 DB 설치

### 순수 JDBC

- 톰켓서버와 db연결하는 기술

### 스프링 통합 테스트

- 스프링이 sql을 편리하게 날릴 수 있도록 도와주는것

### 스프링 Jdbc Template

### JPA

- sql을 직접 하는것도 아니고 객체를 cb에 쿼리없이 저장할 수 있음

### 스프링 데이터 JPA

- JPA 를 편리하게 쓸 수 있도록 한 것
