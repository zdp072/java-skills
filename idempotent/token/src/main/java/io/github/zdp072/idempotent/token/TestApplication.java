package io.github.zdp072.idempotent.token;

import io.github.zdp072.idempotent.token.interceptor.ApiIdempotentInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableScheduling
public class TestApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor());
        super.addInterceptors(registry);
    }

    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }

}