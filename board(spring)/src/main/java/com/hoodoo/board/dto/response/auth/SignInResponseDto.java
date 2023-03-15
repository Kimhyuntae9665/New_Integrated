package com.hoodoo.board.dto.response.auth;

import com.hoodoo.board.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {

    private String email;
    private String nickname;
    private String telNumber;
    private String address;
    private String profile;
    private String token;
    private int expiredTime;

    public SignInResponseDto(UserEntity userEntity,String token){
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.telNumber = userEntity.getTelNumber();
        this.address = userEntity.getAddress();
        this.profile = userEntity.getProfile();
        this.token = token;
        expiredTime = 3600000;
    }

    
    
}
