package com.my.admin.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
//@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("nihao")
//                .host("local")//请求时的baseurl
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.my.admin.controller"))//该路径下的controller类
//                .apis(RequestHandlerSelectors.any())//任何controller类
//                .apis(RequestHandlerSelectors.none())//都不行
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//类上有RestController注解的可以使用
//                .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))//类上有RestController注解的可以使用，若设置多个注解，则需要都满足
//                .apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.none())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact(contactInfo())
                .version("1.0")
                .license("许可证")
                .licenseUrl("http://www.baidu.com")
                .build();
    }

    private Contact contactInfo(){
        return new Contact("huangyun", "www.baidu.com", "huangyun34@163.com");
    }
}
