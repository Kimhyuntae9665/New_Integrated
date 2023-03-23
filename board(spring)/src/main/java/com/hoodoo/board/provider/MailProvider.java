package com.hoodoo.board.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailProvider {
            // ^ interface 에 Autowired 쓰기하면 밑에서 interface를 따로 구현 안 해도 ㅇㅋ
    @Autowired private JavaMailSender javaMailSender;

    public boolean sendMail(){

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("122030@donga.ac.kr");
            simpleMailMessage.setTo("rlagusxo96652@naver.com");
            simpleMailMessage.setSubject("제목");
            simpleMailMessage.setText("Html 형식의 내용이 온다");
            javaMailSender.send(simpleMailMessage);

        }catch(Exception exception){
            exception.printStackTrace();
            return false;
        }

       

        return true;
    }
    
}
