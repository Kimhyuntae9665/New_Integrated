package com.hoodoo.board.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketProvider extends TextWebSocketHandler {
    
    // ^ 연결되었을때 발동되는 함수 
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession){
        System.out.println("Socket Connected!");
        System.out.println(webSocketSession.toString());
    }

    // ^연결 다 된 후  Message가 왔을 때 수행해야하는 작업 
    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage){
        String messagePayload = textMessage.getPayload();
        System.out.println(messagePayload.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession,CloseStatus closeStatus){
        System.out.println("Socket Closed!");
        System.out.println(webSocketSession.toString());
        System.out.println(closeStatus.toString());
    }
    
}
