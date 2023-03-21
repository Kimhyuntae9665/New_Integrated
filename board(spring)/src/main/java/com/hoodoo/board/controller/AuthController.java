package com.hoodoo.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoodoo.board.common.constant.ApiPattern;
import com.hoodoo.board.dto.request.auth.SignInDto;
import com.hoodoo.board.dto.request.auth.SignUpDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.auth.SignInResponseDto;
import com.hoodoo.board.dto.response.auth.SignUpResponseDto;
import com.hoodoo.board.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(ApiPattern.AUTH)
@Api(description = "인증모듈")
public class AuthController {

    @Autowired private AuthService authService;

    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    @ApiOperation(value="회원가입",notes="이메일,비밀번호,닉네임,전화번호,주소를 입력하여 회원을 등록하고, 성공 시에는 회원가입 성공 여부에 true가 반환됨")
    @PostMapping(SIGN_UP)
    // ^ ResponseDto와 ResponseEntity 차이 
    // ^ @Valid로 NN인지 아닌지 검사 가능 NUll 이면 자동으로 거른다  
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpDto requestBody) {

        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
        
    }

    @PostMapping(SIGN_IN)
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody SignInDto requestBody){

        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response; 
    }

    
}