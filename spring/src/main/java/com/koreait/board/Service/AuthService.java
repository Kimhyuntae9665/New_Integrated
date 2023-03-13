package com.koreait.board.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service 
public class AuthService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public String bcrypt(String text){

        

        String bcryptedText = null;

        try{

            // ? 암호화 .encode()함수 
            // ? Hash 암호화를 사용하기 떄문에 같은 값을 줘도 사용할 떄마다 다른 값이 나온다 
            
            bcryptedText = passwordEncoder.encode(text);

        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }


        return bcryptedText;

    }

    public boolean decrypt(String text){

        boolean isMatch = false;

        try{
                                                    // ? 앞에가 원문 뒤에가 암호화된 String 
            isMatch = passwordEncoder.matches("password", text);

        }catch(Exception exception){
            exception.printStackTrace();
            return false; 
        }

        

        return isMatch;
    }

    
}
