package com.hoodoo.board.service;

import com.hoodoo.board.dto.request.auth.SignInDto;
import com.hoodoo.board.dto.request.auth.SignUpDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.auth.SignInResponseDto;
import com.hoodoo.board.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto);
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
}
