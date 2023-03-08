package com.koreait.board.dto.request.humanResource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchHumanResourceRequestDto {

    //? 사번 
    @Min(1)
    private int employeeNumber; 

     // ? 직급 
     @NotBlank
     @Length(min=0,max=5)
     private String position;
     
     // ?이름
     @NotBlank
     @Length(min=0,max=10)
     private String name;
     
     // ? 나이 
     @Range(min=0,max=120)
     private int age;
     
     // ? 성별
     @NotBlank
     @Length(min=0,max=5)
     private String gender;
     
     // ? 학력
     @NotBlank
     @Length(min=0,max=10)
     private String academicAbility;
 
     // ? 생년월일
     @NotBlank
     private String birth;
     
     // ? 전화번호
     @NotBlank
     private String telNumber;
     
     // ? 주소
     @NotBlank
     private String address;
     
     // ? 상세 주소
     @NotBlank 
     private String addressDetail;
     
     // ? 입사일
     @NotBlank
     private String joinDate;
     
     // ? 퇴사일 
     private String resignationDate;
     
     // ? 부서
     private String departement;
     
     // ? 연봉
     @Min(0) 
     private int annualIncome;
     
     // ? 비고 
     private String note;

     public PatchHumanResourceRequestDto (EmployeeEntity dto){
        
        this.employeeNumber = dto.getEmployeeNumber();
        this.position = dto.getPosition();
        this.name = dto.getName();
        this.age = dto.getAge();
        this.gender = dto.getGender();
        this.academicAbility = dto.getAcademicAbility();
        this.birth = dto.getBirth();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.joinDate = dto.getJoinDate();
        this.resignationDate = dto.getResignationDate();
        this.departement = dto.getDepartement();
        this.annualIncome = dto.getAnnualIncome();
        this.note = dto.getNote();
    }
}
