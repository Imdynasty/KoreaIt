package exam02.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProxyCalculator {
    @Pointcut("execution(* exam02.aopex..*(..))")
    public void publicTarget(){}

    @Around("publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{
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
