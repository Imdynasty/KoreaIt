package exam02.main;

import exam02.aopex.Calculator;
import exam02.aopex.RecCalculator;
import exam02.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Calculator cal  = ctx.getBean(Calculator.class);

        long re = cal.factorial(10);
        System.out.println(re);
        long re1 = cal.factorial(10);
        System.out.println(re1);


        ctx.close();
    }
}
