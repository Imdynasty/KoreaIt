package exam02.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class ProxyCalculator {
    /*
    aopex.* : aopex 패키지의 모든 하위 클래스
    aopex..* : aopex 패키지를 포함한 하위 패키지의 모든 클래스(+모든메서드)
    aopex.*.* : 패키지의 모든 하위클래스의 모든 메서드
    aopex..*.* : 패키지를 포함한 하위패키지의 모든클래스, 모든 메서드
    
    메서드
    (..):0개 이상의 매개변수
    () : 매개변수가 없는 형태
    (*): 매개변수가 1개
    (*,*) : 매개변수가 2개
    */
     
    @Pointcut("execution(* exam02.aopex..*(..))")
    public void publicTarget(){}

    @Around("publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{

        /**메서드 정보*/
        //System.out.println("호출한 메서드 정보 : " + joinPoint.getSignature().toLongString());

        /**매개변수로 사용된 값*/
        Object[] args = joinPoint.getArgs();
        long num =(Long)args[0];
       // System.out.println("num :" + num);

        long stime = System.nanoTime();
        try{
            Object result = joinPoint.proceed();//factorial 핵심 기능 대신 수행

            return result;
        }finally {
            long etime = System.nanoTime();
            System.out.println("걸린시간 : " + (etime - stime));
        }

    }

}
