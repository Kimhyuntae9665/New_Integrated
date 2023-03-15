package com.hoodoo.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoodoo.board.common.constant.ResponseMessage;
import com.hoodoo.board.dto.request.board.PostBoardDto;
import com.hoodoo.board.dto.request.board.PostBoardResponseDto;
import com.hoodoo.board.dto.response.ResponseDto;
import com.hoodoo.board.entity.UserEntity;
import com.hoodoo.board.repository.BoardRepository;
import com.hoodoo.board.repository.UserRepository;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;


    public ResponseDto<PostBoardResponseDto > postBoard(String email,PostBoardDto dto){
        
        PostBoardResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByEmail(email);

            if(userEntity==null){
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSucess(ResponseMessage.SUCCESS, data);
    }

    
}
