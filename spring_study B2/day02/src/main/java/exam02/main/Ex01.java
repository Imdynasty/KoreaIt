package exam02.main;

import exam02.aopex.Calculator;
import exam02.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Calculator cal = ctx.getBean(Calculator.class);
        long result = cal.factorial(10);

        System.out.println("cal:"+ result);

        ctx.close();
    }
}
