package com.koreait.board.dto.response.departement;

import com.koreait.board.Entity.DepartementEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDepartementResponseDto {

    private String departementCode;
    private String name;
    private int chief;
    private String telNumber;

    public PostDepartementResponseDto(DepartementEntity departementEntity){
        this.departementCode = departementEntity.getDepartementCode();
        this.name = departementEntity.getName();
        this.chief = departementEntity.getChief();
        this.telNumber = departementEntity.getTelNumber();
    }
    
}
