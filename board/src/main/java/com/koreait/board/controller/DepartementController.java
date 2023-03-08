package com.koreait.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.Service.DepartementService;
import com.koreait.board.common.constant.ApiMappingPattern;
import com.koreait.board.dto.request.departement.PostDepartementRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.departement.GetAllDepartementListResponseDto;
import com.koreait.board.dto.response.departement.PostDepartementResponseDto;


// ! URL PATH는 Controller 담당 
@RestController
@RequestMapping(ApiMappingPattern.DEPARTEMENT)
public class DepartementController {

    @Autowired DepartementService departementService;
    
    private static final String POST_DEPARTEMENT = "/";
    private static final String GET_ALL_DEPARTEMENT_LIST = "/all";


    @PostMapping(POST_DEPARTEMENT)
    //POST http://localhost:4040/apis/departement/                  //   ^RequestBody이니까  앞에 Valid 까지 붙여주기 
    public ResponseDto<PostDepartementResponseDto> postDepartement(@Valid @RequestBody PostDepartementRequestDto requestBody){ //^ Post의 INput은 @RequestBody 거의 필수 
        
        ResponseDto<PostDepartementResponseDto> response = departementService.postDepartement(requestBody);

        return response;
    }

    @GetMapping(GET_ALL_DEPARTEMENT_LIST)
    public ResponseDto<List<GetAllDepartementListResponseDto>> getAllDepartementList(){
        



         ResponseDto<List<GetAllDepartementListResponseDto>> response = departementService.getAllDepartementList();
         return response;

    }
}
