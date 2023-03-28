package com.hoodoo.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // # CORS (Cross - Origin Resource Sharing) 정책 
    // ? 다른 출처 (localhost:4040,localhost:3000 등 )자원을 공유할 수 있도록 설정 하는 권한 정책 
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
                    // ^ addMapping 은 내 애플리케이션에서 정보 방출이 허용되는 URL의 pattern을 말한다 
                    // ^ addMapping("/api") 라고 하면 내 애플리케이션인 "http//:localhost:4040/api/product"에서는 다른 웹 이나 클라이언트의 요청을 받고 대답을 할 수 있다 
        corsRegistry.addMapping("/**")
                    // ^ 내 애플리케이션에 요청을 할 수 있는 도메인(google,facebook)을 설정 
                    // ^ 여기에 등록되지 못하면 요청조차 불가능 : 보안상의 문제
                    // ^ 보안상의 문제를 초기에 차단, 접근 조차 못하게  
                    .allowedOrigins("*")
                    // ^ HTTP 메서드를 말하는것 GET,POST,DELETE 등 
                    .allowedMethods("*");
    }
    
}
