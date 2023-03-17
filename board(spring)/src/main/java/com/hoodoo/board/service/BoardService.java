package com.hoodoo.board.service;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
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
import com.hoodoo.board.dto.response.board.LikeResponseDto;
import com.hoodoo.board.dto.response.board.PatchBoardResponseDto;
import com.hoodoo.board.dto.response.board.PostBoardResponseDto;
import com.hoodoo.board.dto.response.board.PostCommentResponseDto;
import com.hoodoo.board.dto.response.board.PostCommentResponseDto;
import com.hoodoo.board.entity.BoardEntity;
import com.hoodoo.board.entity.CommentEntity;
import com.hoodoo.board.entity.LikeyEntity;
import com.hoodoo.board.entity.RelatedSearchWordEntity;
import com.hoodoo.board.entity.SearchWordLogEntity;
import com.hoodoo.board.entity.UserEntity;
import com.hoodoo.board.repository.BoardRepository;
import com.hoodoo.board.repository.CommentRepository;
import com.hoodoo.board.repository.LikeyRepository;
import com.hoodoo.board.repository.RelatedSearchWordRepository;
import com.hoodoo.board.repository.SearchWordLogRepository;
import com.hoodoo.board.repository.UserRepository;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private LikeyRepository likeyRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private SearchWordLogRepository searchWordLogRepository;
    @Autowired private RelatedSearchWordRepository relatedSearchWordRepository;

                                                 // ^ email은 글쓴이에 대한 정보를 위한 PK , PostBoardDto는 게시물 정보  
    public ResponseDto <PostBoardResponseDto> postBoard(String email,PostBoardDto dto){
        
        PostBoardResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByEmail(email);

            if(userEntity==null){
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            BoardEntity boardEntity = new BoardEntity(userEntity,dto);
            boardRepository.save(boardEntity);
            // ^ 게시물을 Post 하는것에 대한 결과를 담은 Data 객체  이므로 PostBoardResponseDto이다 
            data =  new PostBoardResponseDto(boardEntity);


            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto) {

        LikeResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            LikeyEntity LikeyEntity = likeyRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            // ! 좋아요 명단에 유저의 email이 없으면 좋아요 누르면 +1 
            if (LikeyEntity == null) {
                LikeyEntity = new LikeyEntity(userEntity, boardNumber);
                likeyRepository.save(LikeyEntity);
                boardEntity.increaseLikeCount();
            }
            // !   좋아요 명단에 유저의 email이 있으면 좋아요를 한번 더 누르면 좋아요 -1 되도록 
            else {
                likeyRepository.delete(LikeyEntity);
                boardEntity.decreaseLikeCount();
            }
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<LikeyEntity> likeList = likeyRepository.findByBoardNumber(boardNumber);

            data = new LikeResponseDto(boardEntity, commentList, likeList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);

    }


    public ResponseDto<PostCommentResponseDto> postComment(String email,PostCommentDto dto){
        
        PostCommentResponseDto data =null;

        int boardNumber = dto.getBoardNumber();

        try{

            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity ==null){
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity==null){
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);
            }

            CommentEntity commentEntity = new CommentEntity(userEntity,dto);
            commentRepository.save(commentEntity);


            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<LikeyEntity> likeyList = likeyRepository.findByBoardNumber(boardNumber);

            data = new PostCommentResponseDto(boardEntity, commentList, likeyList);




        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }




    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber) {

        GetBoardResponseDto data = null;

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);
            List<LikeyEntity> likyList = likeyRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

            data = new GetBoardResponseDto(boardEntity, commentList, likyList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);

    }



    public ResponseDto<List<GetListResponseDto>> getList(){

        List<GetListResponseDto> data =null;

        try{

            List<BoardEntity> boardEntityList = boardRepository.findByOrderByBoardWriteDatetimeDesc();
            data = GetListResponseDto.copyList(boardEntityList);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchBoardResponseDto> patchBoard(String email,PatchBoardDto dto){
        PatchBoardResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try{
            // ^ 게시물 번호 존재하는지 부터 먼저 확인  
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity==null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            // ^ 수정 시도하는 사람이랑 게시물 작성자와 같은지 확인하는 작업 
            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if(!isEqualWriter) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // ^ 갈아끼우는 수정하는 작업 
            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            List<LikeyEntity> likeyList = likeyRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            data = new PatchBoardResponseDto(boardEntity,commentList,likeyList);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

        }
        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email,int boardNumber ){
        DeleteBoardResponseDto data=null;

        try{

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity==null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if(!isEqualWriter){
                return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);
            }

            boardRepository.delete(boardEntity);
            data = new DeleteBoardResponseDto(true);


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email){
        List<GetMyListResponseDto> data = null;
        try{

            List<BoardEntity> boardList = boardRepository.findByWriterEmailOrderByBoardWriteDatetimeDesc(email);
            data = GetMyListResponseDto.copyList(boardList);


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }


    public ResponseDto<List<GetSearchListResponseDto>> getSearchList(String searchWord,String previousSearchWord){

        List<GetSearchListResponseDto> data = null;

        try{

            SearchWordLogEntity searchWordLogEntity = new SearchWordLogEntity(searchWord);
            searchWordLogRepository.save(searchWordLogEntity);

            if(!previousSearchWord.isBlank() && searchWord != null){
                RelatedSearchWordEntity relatedSearchWordEntity = new RelatedSearchWordEntity(searchWord,previousSearchWord);
                relatedSearchWordRepository.save(relatedSearchWordEntity);

            }

            List<BoardEntity> boardList = boardRepository.findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(searchWord, searchWord);

            data = GetSearchListResponseDto.copyList(boardList);
            
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);

    }
}
