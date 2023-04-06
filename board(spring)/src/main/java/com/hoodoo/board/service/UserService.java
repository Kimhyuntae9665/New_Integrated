package com.hoodoo.board.service;

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

public interface UserService {

    public ResponseDto<PatchProfileResponseDto> patchProfile(String email,PatchProfileDto dto);
    public ResponseDto<GetUserResponseDto> getUser(String email);
    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto);
    public ResponseDto<ValidateNicknameResponseDto> validateNickname(ValidateNicknameDto dto);
    public ResponseDto<ValidateTelNumberResponseDto> validateTelNumber(ValidateTelNumberDto dto);
    
}
