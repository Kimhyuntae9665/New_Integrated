package com.hoodoo.board.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="회원가입 Request Body")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @ApiModelProperty(value = "사용자 이메일",example="rlagusxo96652@naver.com",required = true)
    @NotBlank
    @Email
    @Length(max=45)
    private String email;
    
    @ApiModelProperty(value="사용자 비밀번호",example="rlagusxo@9665",required = true)
    @NotBlank
    @Length(min=8,max=20)
    private String password;

    @ApiModelProperty(value="사용지 닉네임 ",example="hoodoo",required = true)
    @NotBlank
    @Length(min=3,max=20)
    private String nickname;


    @ApiModelProperty(value="사용자 휴대전화번호",example="010-8482-1490",required = true)
    @NotBlank
    @Length(min=11,max=13)
    private String telNumber;

    @ApiModelProperty(value="사용자 주소",example="부산광역시",required = true)
    @NotBlank
    private String address;
    


    
}
