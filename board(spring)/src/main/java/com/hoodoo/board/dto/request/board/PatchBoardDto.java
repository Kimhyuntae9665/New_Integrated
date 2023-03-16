package com.hoodoo.board.dto.request.board;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchBoardDto {
    @Min(1)
    private int boardNumber;
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;
    private String boardImgUrl;
    
    
}
