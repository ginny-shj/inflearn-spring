package me.shj.javaspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //이걸 해야 Aop 뜽록 -> Spring bean에도 등록하기 (config파일)
@Component //bean을 등록하거나, component로 어노테이션 쓰던가
public class TimeTraceAop {

    //어디 파일에 적용할 건지?
    @Around("execution(* me.shj.javaspring..*(..))") //me.shj.javaspring 패키지안의 모든 class에 적용한다
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); //실행된 매서드의 이름

        try{
            return joinPoint.proceed(); //,proceed() : 매서드를 실행해라

        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
