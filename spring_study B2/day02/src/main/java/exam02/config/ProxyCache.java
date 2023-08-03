package exam02.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(1)
public class ProxyCache {

    private Map<Long, Object> cache = new HashMap<>();
    /*
    @Pointcut("execution(* exam02.aopex..*(..))")
    public void publicTarget() {}
    */
    //@Around("publicTarget()")
    //@Around("execution(* exam02.aopex..*(..))")
    @Around("exam02.config.CommonPointcut.publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Long num = (Long)args[0];
        if (cache.containsKey(num)) {
            System.out.println("캐시 사용!!");
            return cache.get(num); // 캐시에 저장된 결과 사용
        }

        Object result = joinPoint.proceed(); // 다음 프록시 호출 -> proceed() -> factorial(...)
        cache.put(num, result); // 캐시에 저장
        System.out.println("캐시 저장!!");
        return result;
    }
}

