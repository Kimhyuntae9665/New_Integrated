package com.hoodoo.board.service;

import com.hoodoo.board.dto.request.user.PatchProfileDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.user.PatchProfileResponseDto;

public interface UserService {

    public ResponseDto<PatchProfileResponseDto> patchProfile(String email,PatchProfileDto dto);
    
}
