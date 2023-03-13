package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.Service.HumanResourceService;
import com.koreait.board.common.constant.ApiMappingPattern;
import com.koreait.board.dto.request.humanResource.PatchHumanResourceRequestDto;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PatchHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;

@RestController
@RequestMapping(ApiMappingPattern.HR)
public class HumanResourceController {
    

    @Autowired private HumanResourceService humanResourceService;


    // ^ 코드상에 literal 없애서 깔끔하게 보인다 
    private static final String POST_HUMAN_RESOURCE = "/";
    private static final String GET_HUMAN_RESOURCE = "/{employeeNumber}";
    private static final String PATCH_HUMAN_RESOURCE = "/";

    @PostMapping("/")
    //? POST 방식 http://localhost:4040/apis/hr/
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(@Valid @RequestBody PostHumanResourceRequestDto requestBody){   //@RequestBody 는 POst와 같은 method에는 Body에 정보가 붙어 오기 떼문에 이 어노테이션을 써서 연결 시켜  주는거 
        ResponseDto<PostHumanResourceResponseDto> response = humanResourceService.postHumanResource(requestBody);

        return response; //^ Controller의 역할은 Service에서 가공된 계산을 사용하여 Client에게 정보 전달역할  
    }

    @GetMapping(GET_HUMAN_RESOURCE)  //^ t사번을 Path (URL) 을 통해서 받아온다 
    // ? GET 방식으로 http://localhost:4040/apis/hr/사번
    public ResponseDto<GetHumanResourceResponseDto> getHumanResource(@AuthenticationPrincipal String sub,@PathVariable("employeeNumber") int employeeNumber){
    
        ResponseDto<GetHumanResourceResponseDto> response = 
        humanResourceService.getHumanResource(employeeNumber);

        return response;
    }

    @PatchMapping(PATCH_HUMAN_RESOURCE) //^ PATCH 가 정보를 수정하는 메서드 
    // ?PATCH http://localhost:4040/apis/hr/
    // ^ PATCH는 데이터를 담아올 적에 RequestBody에 데이터를 받아온다 POST와 동일 
    // ^ @Valid는 유효성 검사 때문에 사용 
    public ResponseDto<PatchHumanResourceResponseDto> patchHumanResource(@Valid@RequestBody PatchHumanResourceRequestDto requestBody){
        
        ResponseDto<PatchHumanResourceResponseDto> response = humanResourceService.patchHumanResource(requestBody);
        return response;
    }
}
