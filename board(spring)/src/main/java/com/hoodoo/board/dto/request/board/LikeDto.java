package com.hoodoo.board.dto.request.board;

import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
@NoArgsConstructor
public class LikeDto {
    @Min(1)
    private int boardNumber;

    
}