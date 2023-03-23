package com.hoodoo.board.dto.request.board;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="댓글 작성 Request Body")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {

    // ^ 이거 두개만 요청해도 나머지는 email을 통해서 writer에 대한 정보가 자동으로 온다 
    @ApiModelProperty(value="게시물 번호",example = "1",required = true)
    @Min(1)
    private int boardNumber;

    @ApiModelProperty(value="댓글 내용",example ="This is Comment",required = true)
    @NotBlank
    private String commentContent;

    
}
