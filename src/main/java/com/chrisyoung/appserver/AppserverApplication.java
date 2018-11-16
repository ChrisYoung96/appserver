package com.chrisyoung.appserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("dao")
//@ComponentScan(basePackages = "com.chrisyoung.*")  //指定Bean扫描位置
public class AppserverApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppserverApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class applicationClass =AppserverApplication.class;
}
