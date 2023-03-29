package com.hoodoo.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoodoo.board.common.constant.ApiPattern;
import com.hoodoo.board.dto.request.user.PatchProfileDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.user.GetUserResponseDto;
import com.hoodoo.board.dto.response.user.PatchProfileResponseDto;
import com.hoodoo.board.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(description="유저 모듈")
@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {

    @Autowired private UserService userService;


    private final String GET_USER="/";
    private final String PATCH_PROFILE = "/profile";

                                                        // ^ 새로운 정보 User의 정보가 Token에 있으므로 email  사용  
                                                        // ^ email 이 PK이니까 
    @ApiOperation(value="유저 프로필 URL 수정",notes="Request Header Authorization에 Bearer JWT를 포함하고 Request Body에 profile을 포함하여 요청을 하면, 성공시 유저 정보를 반환, 실패시 실패 메시지를 반환")
    @PatchMapping(PATCH_PROFILE)                                               
    public ResponseDto<PatchProfileResponseDto> patchProfile(
        @ApiParam(hidden = true)
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody PatchProfileDto requestBody){

            ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(email,requestBody);
            return response;

    }

    @ApiOperation(value="유저 정보 불러오기",notes="Request Header Authorization에 Bearer Token을 포함하여 요청을 하면, 성공 시 유저 정보를 반환, 실페시 실패 메시지를 반환")
    @GetMapping(GET_USER)                          //^ 쿠키 값이 유지 되니까 Token 인증 자동 패스 
    // ^ 토큰에 있는 값 가져오기 ==> @AuthenticationPrincipal
    // ^ 웹 브라우저를 끄고 다시 들어 와도 User의 정보를 유지하는 함수 
    // ^ 웹 브라우저를 끄고 들어 와도 마이페이지 즉, 로그인 된 상태를 유지 
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String email){
        ResponseDto<GetUserResponseDto> response = userService.getUser(email);
        return response;
    }
}
