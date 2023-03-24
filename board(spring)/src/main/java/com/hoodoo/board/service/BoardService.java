package com.hoodoo.board.service;

import java.util.List;

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
import com.hoodoo.board.dto.response.board.GetTop3ListResponseDto;
import com.hoodoo.board.dto.response.board.LikeResponseDto;
import com.hoodoo.board.dto.response.board.PatchBoardResponseDto;
import com.hoodoo.board.dto.response.board.PostBoardResponseDto;
import com.hoodoo.board.dto.response.board.PostCommentResponseDto;


// ^ Interface 이기 때문에 내용이 구현이 되어 있진 않더라도 선언부만 선언 되어 있더라도 바로 사용사능 Ex.) boardService.getTop3List() 이렇게 사용가능 
// ^ 팀 프로젝트를 할때 아주 유용 
public interface BoardService {

    public ResponseDto <PostBoardResponseDto> postBoard(String email,PostBoardDto dto);
    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto);
    public ResponseDto<PostCommentResponseDto> postComment(String email,PostCommentDto dto);
    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber);
    public ResponseDto<List<GetListResponseDto>> getList();
    public ResponseDto<List<GetTop3ListResponseDto>> getTop3List();
    public ResponseDto<GetTop15SearchWordResponseDto> getTop15SearchWord();
    public ResponseDto<GetTop15RelatedSearchWordResponseDto> getTop15RelatedSearchWord(String searchWord);
    public ResponseDto<PatchBoardResponseDto> patchBoard(String email,PatchBoardDto dto);
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email,int boardNumber );
    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email);
    public ResponseDto<List<GetSearchListResponseDto>> getSearchList(String searchWord,String previousSearchWord);
    
}
