package exam02.config;

import exam02.aopex.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppCtx {
    @Bean
    public ProxyCalculator proxyCalculator(){
        return new ProxyCalculator();
    }

    @Bean
    public Calculator calculator(){
        return new RecCalculator();
    }

}
