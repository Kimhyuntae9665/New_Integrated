package com.hoodoo.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
import com.hoodoo.board.dto.request.user.PatchProfileDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.user.PatchProfileResponseDto;
import com.hoodoo.board.entity.UserEntity;
import com.hoodoo.board.repository.UserRepository;

@Service
public class UserService {

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

            data = new PatchProfileResponseDto(userEntity);



            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }
    
}
