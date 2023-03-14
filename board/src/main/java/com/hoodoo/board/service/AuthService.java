package com.hoodoo.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
import com.hoodoo.board.dto.request.auth.SignUpDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.repository.UserRepository;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    
    // ^ 비밀번호 암호화 
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // ^ 데이터 베이스에 집어넣는 작업 
    public ResponseDto<SignUpDto> signUp(SignUpDto dto){

        SignUpDto data = null;

        String email = dto.getEmail();
        String telNumber = dto.getTelNumber();
        String password = dto.getPassword();

        // ^ 이메일과 전화번호가 UNIQUE인지 확인하는 작업을 가장 먼저 한다 
        try{

            boolean hasEmail = userRepository.existsByEmail(email);
            if(hasEmail){
                return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);
            }
            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            if(hasTelNumber){
                return ResponseDto.setFailed(ResponseMessage.EXIST_TEL_NUMBER);
            }

            // ^ 비밀번호 암호화 작업 .encode()함수 
            String encodedPassword = passwordEncoder.encode(password);

            


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
        
    }
    
}
