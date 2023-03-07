package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.Service.HumanResourceService;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;

@RestController
@RequestMapping("/apis/hr")
public class HumanResourceController {
    

    @Autowired private HumanResourceService humanResourceService;

    @PostMapping("/")
    //? POST 방식 http://localhost:4040/apis/hr/
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(@Valid @RequestBody PostHumanResourceRequestDto requestBody){   //@RequestBody 는 POst와 같은 method에는 Body에 정보가 붙어 오기 떼문에 이 어노테이션을 써서 연결 시켜  주는거 
        ResponseDto<PostHumanResourceResponseDto> response = humanResourceService.postHumanResource(requestBody);

        return response; //^ Controller의 역할은 Service에서 가공된 계산을 사용하여 Client에게 정보 전달역할  
    }
}
