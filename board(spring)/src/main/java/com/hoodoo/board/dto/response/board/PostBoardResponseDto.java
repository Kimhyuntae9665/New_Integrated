package com.hoodoo.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.hoodoo.board.entity.BoardEntity;
import com.hoodoo.board.entity.CommentEntity;
import com.hoodoo.board.entity.LikeyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardResponseDto {

    private BoardEntity board;
    private List<CommentEntity> commentList;
    private List<LikeyEntity> likeyList;

    public PostBoardResponseDto(BoardEntity board){
        this.board = board;
        this.commentList = new ArrayList<>();
        this.likeyList = new ArrayList<>();
    }
    
}
