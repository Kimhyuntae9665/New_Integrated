package com.hoodoo.board.dto.request.board;

import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Api(value="좋아요 기능 Request Body")
@Data
@NoArgsConstructor
public class LikeDto {
    @ApiModelProperty(value="게시물 번호",example = "1",required = true)
    @Min(1)
    private int boardNumber;

    
}