package com.koreait.board.dto.response.departement;

import java.util.ArrayList;
import java.util.List;

import com.koreait.board.Entity.DepartementEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDepartementListResponseDto {
   
    
    // ? 부서코드
    private String departementCode;
    // ? 부서명 
    private String name;
    // ? 부서장 사번 
    private int chief;
    // ? 부서 전화번호 
    private String telNumber;

    public GetAllDepartementListResponseDto(DepartementEntity departementEntity){
        this.departementCode = departementEntity.getDepartementCode();
        this.name = departementEntity.getName();
        this.chief = departementEntity.getChief();
        this.telNumber = departementEntity.getTelNumber();
    }


    public static List<GetAllDepartementListResponseDto> copyList(List<DepartementEntity> departementEntities){
        
        List<GetAllDepartementListResponseDto> result = new ArrayList<GetAllDepartementListResponseDto>();

        for(DepartementEntity departementEntity: departementEntities){
            
            GetAllDepartementListResponseDto item = new GetAllDepartementListResponseDto(departementEntity);

            result.add(item);
        }

        return result;
    }
    
}
