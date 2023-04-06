package com.hoodoo.board.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
import com.hoodoo.board.dto.request.user.PatchProfileDto;
import com.hoodoo.board.dto.request.user.ValidateEmailDto;
import com.hoodoo.board.dto.request.user.ValidateNicknameDto;
import com.hoodoo.board.dto.request.user.ValidateTelNumberDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.user.GetUserResponseDto;
import com.hoodoo.board.dto.response.user.PatchProfileResponseDto;
import com.hoodoo.board.dto.response.user.ValidateEmailResponseDto;
import com.hoodoo.board.dto.response.user.ValidateNicknameResponseDto;
import com.hoodoo.board.dto.response.user.ValidateTelNumberResponseDto;
import com.hoodoo.board.entity.UserEntity;
import com.hoodoo.board.repository.UserRepository;
import com.hoodoo.board.service.UserService;

@Service
public class UserServiceImplements implements UserService {

    @Autowired private UserRepository userRepository;

    public ResponseDto<PatchProfileResponseDto> patchProfile(String email,PatchProfileDto dto){

        PatchProfileResponseDto data = null;

        String profile = dto.getProfile();

        try {
            // ^ 이메일을 기준으로 유저를 찾아온다 
            UserEntity userEntity = userRepository.findByEmail(email);
            // 
            if(userEntity==null){
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            // ^ 프로파일을 변경 
            userEntity.setProfile(profile);
            // ^ 데이터 베이스에서도 변경 
            userRepository.save(userEntity);
            // ^ 바뀐 user 정보를 가진 인스턴스를 data로 옮긴다 
            data = new PatchProfileResponseDto(userEntity);
            // ^ 성공시에 Sucess 메시지와 함께 data 정보를 전송한다 


            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetUserResponseDto> getUser(String email) {


        
        GetUserResponseDto data = null;

        try{

            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity==null){
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            data = new GetUserResponseDto(userEntity);
            
            

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }
    

    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto) {
        ValidateEmailResponseDto data = null;

        String email = dto.getEmail();

        try{

            boolean hasEmail = userRepository.existsByEmail(email);
            data = new ValidateEmailResponseDto(hasEmail);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }


    public ResponseDto<ValidateNicknameResponseDto> validateNickname(ValidateNicknameDto dto) {

    

        ValidateNicknameResponseDto data = null;

        String nickname = dto.getNickname();

        try{

            boolean hasNickname = userRepository.existsByNickname(nickname);
            data = new ValidateNicknameResponseDto(hasNickname);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);



    }

   
   
    public ResponseDto<ValidateTelNumberResponseDto> validateTelNumber(ValidateTelNumberDto dto){

        ValidateTelNumberResponseDto data = null;

        String telNumber = dto.getTelNumber();


        try{

            boolean hasTelNumber = userRepository.existsByNickname(telNumber);
            data = new ValidateTelNumberResponseDto(hasTelNumber);



        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);

    }



}

    

    

    
