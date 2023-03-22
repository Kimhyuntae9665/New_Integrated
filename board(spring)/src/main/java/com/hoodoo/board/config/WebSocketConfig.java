package com.hoodoo.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.hoodoo.board.provider.SocketProvider;

// ^ Configuration 은 환경설정 
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Autowired private SocketProvider socketProvider;

    // ^ 빠른 수정을 클릭하면 구현하지 않은 함수 자동 수정 
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketProvider, "/web-socket").setAllowedOrigins("*");
    }
    
}
