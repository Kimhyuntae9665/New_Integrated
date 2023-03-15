package com.hoodoo.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoodoo.board.common.constant.ApiPattern;
import com.hoodoo.board.dto.request.board.PostBoardDto;
import com.hoodoo.board.dto.request.board.PostBoardResponseDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired private BoardService boardService;

    private final String POST_BOARD = "";

    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(
        // ^ 토큰을 받아서 인증 후에 사용하는 어노테이션 
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostBoardDto requestBody
    ){

        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email,requestBody);
        return response;

        
    }


    
}
