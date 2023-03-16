package com.hoodoo.board.dto.response.board;

import java.util.List;

import com.hoodoo.board.entity.BoardEntity;
import com.hoodoo.board.entity.CommentEntity;
import com.hoodoo.board.entity.LikeyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchBoardResponseDto {

    private BoardEntity board;
    private List<CommentEntity> commentList;
    private List<LikeyEntity> likeyList;
    
}
