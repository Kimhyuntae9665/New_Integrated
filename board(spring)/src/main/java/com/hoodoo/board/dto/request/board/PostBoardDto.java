package com.hoodoo.board.dto.request.board;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardDto {
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;
    private String boardImgUrl;

    
}
