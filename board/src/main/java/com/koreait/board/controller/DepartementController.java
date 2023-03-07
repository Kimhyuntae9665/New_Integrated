package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.common.constant.ApiMappingPattern;
import com.koreait.board.dto.request.departement.PostDepartementRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.departement.PostDepartementResponseDto;


// ! URL PATH는 Controller 담당 
@RestController
@RequestMapping(ApiMappingPattern.DEPARTEMENT)
public class DepartementController {
    
    private static final String POST_DEPARTEMENT = "/";


    @PostMapping(POST_DEPARTEMENT)          //^ 앞에 Valid 까지 붙여주기 
    public ResponseDto<PostDepartementResponseDto> postDepartement(@Valid @RequestBody PostDepartementRequestDto requestBody){ //^ Post의 INput은 @RequestBody 거의 필수 


        return null;
    }
}
