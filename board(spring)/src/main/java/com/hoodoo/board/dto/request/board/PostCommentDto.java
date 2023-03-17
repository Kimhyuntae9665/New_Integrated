package com.hoodoo.board.dto.request.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {

    // ^ 이거 두개만 요청해도 나머지는 email을 통해서 writer에 대한 정보가 자동으로 온다 
    private int boardNumber;
    private String commentContent;

    
}
