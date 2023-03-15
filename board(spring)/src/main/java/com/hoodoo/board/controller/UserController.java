package com.hoodoo.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoodoo.board.common.constant.ApiPattern;
import com.hoodoo.board.dto.request.user.PatchProfileDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.user.PatchProfileResponseDto;
import com.hoodoo.board.service.UserService;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {

    @Autowired private UserService userService;

    private final String PATCH_PROFILE = "/profile";

                                                        // ^ 새로운 정보 User의 정보가 Token에 있으므로 email  사용  
                                                        // ^ email 이 PK이니까 
    @PatchMapping(PATCH_PROFILE)                                               
    public ResponseDto<PatchProfileResponseDto> patchProfile(
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody PatchProfileDto requestBody){

            ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(email,requestBody);
            return response;

    }
    
}
