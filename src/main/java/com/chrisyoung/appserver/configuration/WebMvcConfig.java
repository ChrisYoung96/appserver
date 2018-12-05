package com.chrisyoung.appserver.configuration;

import com.chrisyoung.appserver.constant.UploadImageParams;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @program: imageuploademo
 * @author: Chris Young
 * @create: 2018-12-05 09:34
 * @description: 文件上传配置类
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(DataSize.ofBytes(UploadImageParams.MAX_FILE_SIZE));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofBytes(UploadImageParams.MAX_REQUEST_SIZE));
        return factory.createMultipartConfig();
    }


}