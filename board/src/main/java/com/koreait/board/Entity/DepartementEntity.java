package com.koreait.board.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Departement")
@Table(name="Departement")

public class DepartementEntity {
    @Id
    // ? 부서코드
    private String departementCode;
    // ? 부서명 
    private String name;
    // ? 부서장 사번 
    private int chief;
    // ? 부서 전화번호 
    private String telNumber;

    
}
