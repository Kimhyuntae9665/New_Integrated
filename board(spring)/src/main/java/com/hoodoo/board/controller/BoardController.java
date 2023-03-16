package com.hoodoo.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoodoo.board.common.constant.ApiPattern;
import com.hoodoo.board.dto.request.board.PostBoardDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.board.GetBoardResponseDto;
import com.hoodoo.board.dto.response.board.GetListResponseDto;
import com.hoodoo.board.dto.response.board.PostBoardResponseDto;
import com.hoodoo.board.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired private BoardService boardService;

    private final String POST_BOARD = "";
                                    // ^ PathVariable 사용 
    private final String GET_BOARD = "/{boardNumber}";
    private final String GET_LIST = "/list";

    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(
        // ^ 토큰을 받아서 인증 후에 사용하는 어노테이션 
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostBoardDto requestBody
    ){

        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email,requestBody);
        return response;

        
    }

    @GetMapping(GET_BOARD)                              //^ // ^ PathVariable 사용 
    public ResponseDto<GetBoardResponseDto> getBoard(@PathVariable("boardNumber") int boardNumber){
        ResponseDto<GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;

    }

    // ^모든  BoardList를 가져오는 작업 
    // ^ React Frontend에서 요구하는 데이터 타입과 똑같은 형태로 반환해야해 IPreview 형태 ==> 만들어야 
    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList(){
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }


    
}
