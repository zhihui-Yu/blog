package xyz.yzh.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author simple
 */
public class SwaggerConfig {
    // url: http://localhost:8888/swagger-ui/index.html
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .groupName("Login")
            .select()
            //指定扫描的包或者注解
            .apis(RequestHandlerSelectors.basePackage("xyz.yzh.controller").or(RequestHandlerSelectors.withClassAnnotation(RestController.class)))
            //过滤，只扫描带有下面请求路径的接口
//                .paths(PathSelectors.ant("/yzh/**"))
            .build();
    }

    //配置Swagger的apiInfo
    private ApiInfo apiInfo() {

        Contact contact = new Contact("yzh", "https://blog.csdn.net/Crezfikbd?type=blog", "yzh@qq.com");

        return new ApiInfo("blog-admin的接口文档",
            "示例接口文档",
            "v1.0",
            "https:127.0.0.1",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>()
        );

    }
}
