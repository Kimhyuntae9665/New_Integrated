package com.hoodoo.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoodoo.board.common.constant.ApiPattern;
import com.hoodoo.board.dto.request.board.LikeDto;
import com.hoodoo.board.dto.request.board.PatchBoardDto;
import com.hoodoo.board.dto.request.board.PostBoardDto;
import com.hoodoo.board.dto.request.board.PostCommentDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.board.DeleteBoardResponseDto;
import com.hoodoo.board.dto.response.board.GetBoardResponseDto;
import com.hoodoo.board.dto.response.board.GetListResponseDto;
import com.hoodoo.board.dto.response.board.GetMyListResponseDto;
import com.hoodoo.board.dto.response.board.GetSearchListResponseDto;
import com.hoodoo.board.dto.response.board.GetTop15RelatedSearchWordResponseDto;
import com.hoodoo.board.dto.response.board.GetTop15SearchWordResponseDto;
import com.hoodoo.board.dto.response.board.LikeResponseDto;
import com.hoodoo.board.dto.response.board.PatchBoardResponseDto;
import com.hoodoo.board.dto.response.board.PostBoardResponseDto;
import com.hoodoo.board.dto.response.board.PostCommentResponseDto;
import com.hoodoo.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="게시물 모듈")
@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired private BoardService boardService;

    private final String POST_BOARD = "";
                                    // ^ PathVariable 사용 
    private final String POST_COMMENT = "/comment";
    private final String LIKE = "/like";
    private final String GET_BOARD = "/{boardNumber}";
    private final String GET_LIST = "/list";
    private final String GET_MY_LIST = "my-list";
    private final String GET_SEARCH_LIST = "/search-list/{searchWord}";
    private final String GET_SEARCH_LIST_PREVIOUS = "/search-list/{searchWord}/{previousSearchWord}";
    private final String PATCH_BOARD = "";
    private final String DELETE_BOARD = "/{boardNumber}";
    private final String GET_TOP15_SEARCH_WORD = "/top15-search-word";
    private final String GET_TOP15_RELATED_SEARCH_WORD = "/top15-related-search-word/{searchWord}";


    @ApiOperation(value="게시물 작성",notes="제목 ,내용,이미지를 전송하면 게시물 작성 결과로 작성된 게시물 정보를 반환, 실패시 실패 메시지를 반환")
    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(
        // ^ 토큰을 받아서 인증 후에 사용하는 어노테이션 
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostBoardDto requestBody
    ){

        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email,requestBody);
        return response;

        
    }

    @ApiOperation(value="댓글 작성",notes="Requset Header Athorization에 Bearer JWT를 포함하고 Request Body에 bodyNumber,content를 포함하여 요청을 하면, 성공시에 게시물 전체 데이터를 반환, 실패시 실패 메시지를 반환")
    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostCommentDto requestBody
    ){
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(email,requestBody);
        return response;

    }

    @ApiOperation(value="좋아요 기능",notes="Request Header Authorization에 Bearer JWT를 포함하고 Request Body에 boardNumber를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메시지를 반환")
    @PostMapping(LIKE)
    public ResponseDto<LikeResponseDto>like(
        // ^ 로그인 된 사람만 좋아요 누를 수 있다(검증된 인스턴스만 좋앙 누를 수 있다 )
        @AuthenticationPrincipal String email,
        @Valid @RequestBody LikeDto requestBody){
        // ^ 로그인 된 사람만 좋아요 누를 수 있다 

        ResponseDto<LikeResponseDto> response = boardService.like(email,requestBody);
        return response;

    }

    
    @ApiOperation(value="",notes="")
    @GetMapping(GET_BOARD)                              //^ // ^ PathVariable 사용 
    public ResponseDto<GetBoardResponseDto> getBoard(
        @ApiParam(value="게시물 번호",example="2",required=true)
        @PathVariable("boardNumber") int boardNumber){
        
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

    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<GetMyListResponseDto>> getMyList(@AuthenticationPrincipal String email){
        
        ResponseDto<List<GetMyListResponseDto>> response = boardService.getMyList(email);
        return response;
    }

    // @GetMapping(GET_SEARCH_LIST)
    // public ResponseDto

    // ^ URL 설정 2개도 가능 
    @GetMapping(value={GET_SEARCH_LIST_PREVIOUS,GET_SEARCH_LIST})
    public ResponseDto<List<GetSearchListResponseDto>>  getSearchList(


        @PathVariable("searchWord") String searchWord,
        @PathVariable(name="previousSearchWord",required=false) String previousSearchWord
    ){
        ResponseDto<List<GetSearchListResponseDto>> response = boardService.getSearchList(searchWord,previousSearchWord);
        return response;

       

    }

    
    @GetMapping(GET_TOP15_SEARCH_WORD)
    public ResponseDto<GetTop15SearchWordResponseDto> getTop15SearchWord(){
        
        ResponseDto<GetTop15SearchWordResponseDto> response = boardService.getTop15SearchWord();
        return response;

    }


    @GetMapping(GET_TOP15_RELATED_SEARCH_WORD)
    public ResponseDto<GetTop15RelatedSearchWordResponseDto> getTop15RelatedSearchWord(@PathVariable("searchWord") String searchWord){
        ResponseDto<GetTop15RelatedSearchWordResponseDto> response = boardService.getTop15RelatedSearchWord(searchWord);
        return response;
    }

    @PatchMapping(PATCH_BOARD)
    public ResponseDto<PatchBoardResponseDto> patchBoard(@AuthenticationPrincipal String email,@Valid @RequestBody PatchBoardDto requestBody){
        ResponseDto<PatchBoardResponseDto> response = boardService.patchBoard(email,requestBody);
        return response;
    }

    @DeleteMapping(DELETE_BOARD)
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(
        // ^ 게시물 작성자 확인을 위해 
        @AuthenticationPrincipal String email,
        @PathVariable("boardNumber") int boardNumber
    ){
        ResponseDto<DeleteBoardResponseDto> response = boardService.deleteBoard(email,boardNumber);
        return response;
    }


    
}
