package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.Service.HumanResourceService;

@RestController
@RequestMapping("/apis/hr")
public class HumanResourceController {
    

    @Autowired private HumanResourceService humanResourceService;

    @PostMapping("/")
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(@Valid @RequestBody PostHumanResourceRequestDto requestBody){   //@RequestBody 는 POst와 같은 method에는 Body에 정보가 붙어 오기 떼문에 이 어노테이션을 ㅆ서 ㅓ일겅 주는거 
        ResponseDto<PostHumanResourceResponseDto> response = humanResourceService.postHumanResource(requestBody);

        return response;
    }
}
