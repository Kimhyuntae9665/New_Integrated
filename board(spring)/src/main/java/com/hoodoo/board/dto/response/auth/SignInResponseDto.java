package com.hoodoo.board.dto.response.auth;

import com.hoodoo.board.entity.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value="로그인 Response Body - data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    @ApiModelProperty(value = "사용자 이메일",example = "rlagusxo96652@naver.com",required = true)
    private String email;
    @ApiModelProperty(value="사용자 닉네임",example = "박호두",required = true)
    private String nickname;
    @ApiModelProperty(value="사용자 휴대폰 번호",example = "010-8482-1490",required = true)
    private String telNumber;
    @ApiModelProperty(value="사용자 주소",example = "부산광역시",required = true)
    private String address;
    @ApiModelProperty(value="사용자 프로필 사진",example = "http://~",required = true)
    private String profile;
    @ApiModelProperty(value="JWT",example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",required = true)
    private String token;
    @ApiModelProperty(value="토큰 만료기간",example = "3600000",required = true)
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
