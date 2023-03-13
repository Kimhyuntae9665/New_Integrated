package com.koreait.board.dto.request.departement;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDepartementRequestDto {
    
    // ! 필수값 확인 , 길이 확인은 Dto 에서 확인 한다 

    @NotBlank
    @Length(min = 0,max =5)
    private String departementCode;
    
    @NotBlank
    @Length(min=0,max=50)
    private String name;

    @Min(1)
    private int chief;

    @NotBlank
    @Length(min=0,max=15)
    private String telNumber;

}
