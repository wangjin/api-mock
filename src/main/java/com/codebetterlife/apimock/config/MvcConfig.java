package com.codebetterlife.apimock.config;

import com.codebetterlife.apimock.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-12-21 6:09 下午
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**");
        interceptorRegistration
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/users/register")
                .excludePathPatterns("/users/login");
    }
}
