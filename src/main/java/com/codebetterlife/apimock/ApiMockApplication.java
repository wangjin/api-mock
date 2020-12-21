package com.codebetterlife.apimock;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangjin
 */
@EnableKnife4j
@SpringBootApplication
@MapperScan("com.codebetterlife.apimock.mapper")
public class ApiMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMockApplication.class, args);
    }

}
