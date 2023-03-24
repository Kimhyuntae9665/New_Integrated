package com.hoodoo.board.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
import com.hoodoo.board.dto.request.auth.SignInDto;
import com.hoodoo.board.dto.request.auth.SignUpDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.auth.SignInResponseDto;
import com.hoodoo.board.dto.response.auth.SignUpResponseDto;
import com.hoodoo.board.entity.UserEntity;
import com.hoodoo.board.provider.TokenProvider;
import com.hoodoo.board.repository.UserRepository;
import com.hoodoo.board.service.AuthService;

@Service //^ interface를 구현하는 class 
public class AuthServiceImplements implements AuthService { 
    @Autowired private TokenProvider tokenProvider;
    @Autowired private UserRepository userRepository;
    
    // ^ 비밀번호 암호화 
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // ^ 데이터 베이스에 집어넣는 작업 
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto){

        SignUpResponseDto data = null;

        String email = dto.getEmail();
        String telNumber = dto.getTelNumber();
        String password = dto.getPassword();

        UserEntity userEntity = null;

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
            // ^ 유저 비밀번호를 암호화된 상태로 바꿔준다 
            dto.setPassword(encodedPassword);

            // ^ 바뀐 암호화된 비밀번호와 이메일을 다시 UserEntity와 UserRepository로 전달저장한다 
            // ^ 데이터 베이스에 집어넣는 주된 작업 
             userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            // ^  인스턴스를 반환 
            data = new SignUpResponseDto(true);

            


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
        
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
        
        SignInResponseDto data = null;

        // ^ 개인정보 보호법상 둘 중에 하나만 잘못되어도 로그인 안되지만 뭐가 틀린건지 가르쳐 주면 안되 
        // ^ 뭐가 틀린건지 가르쳐 주면 안되  
        String email = dto.getEmail();
        String password  = dto.getPassword();

        UserEntity userEntity = null;
        
        try{
            //  ^ 받아온 이메일이 존재하는지 부터 확인 
            userEntity = userRepository.findByEmail(email);
            if(userEntity ==null){
                return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
            }

            boolean isEqualPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if(!isEqualPassword){
                return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
            }



        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        try{

            String token = tokenProvider.create(email);

            data =  new SignInResponseDto(userEntity,token);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }
    
}
