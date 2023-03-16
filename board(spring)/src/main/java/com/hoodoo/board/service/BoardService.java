package com.hoodoo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
import com.hoodoo.board.dto.request.board.PostBoardDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.dto.response.board.GetBoardResponseDto;
import com.hoodoo.board.dto.response.board.GetListResponseDto;
import com.hoodoo.board.dto.response.board.PostBoardResponseDto;
import com.hoodoo.board.entity.BoardEntity;
import com.hoodoo.board.entity.CommentEntity;
import com.hoodoo.board.entity.LikeyEntity;
import com.hoodoo.board.entity.UserEntity;
import com.hoodoo.board.repository.BoardRepository;
import com.hoodoo.board.repository.CommentRepository;
import com.hoodoo.board.repository.LikeyRepository;
import com.hoodoo.board.repository.UserRepository;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private LikeyRepository likeyRepository;
    @Autowired private CommentRepository commentRepository;

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

    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber){
        GetBoardResponseDto data = null;

        try{

            // ^ 1.  
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity==null){
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);
            }

            List<LikeyEntity> likeyList = likeyRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            data = new GetBoardResponseDto(boardEntity,commentList,likeyList);

            // ^ 2. 




        }catch(Exception exception){
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

    
}
