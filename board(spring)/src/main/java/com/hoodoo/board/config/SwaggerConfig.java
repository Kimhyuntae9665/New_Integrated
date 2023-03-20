package com.hoodoo.board.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                    .title("hoodoo board REST API")
                    .description("Spring boot로 작성한 REST API 명세서")
                    .version("1.0.0")
                    .build();
    }
    
}
