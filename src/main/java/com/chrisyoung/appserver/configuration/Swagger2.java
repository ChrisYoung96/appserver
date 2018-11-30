package com.chrisyoung.appserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 09:37
 * @description: Swagger配置类
 **/

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("com.chrisyoung.demo.controller"))
                .build();
    }
    /*
    对Swagger2进行基本配置
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构件RESTful APIs") //文档标题
                .description("aaaa")  //文档
                .version("1.0")  //文档版本
                .build();
    }
}
