package com.koreait.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.Repository.DepartementRepository;
import com.koreait.board.Repository.EmployeeRepository;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;

@Service
public class HumanResourceService {
        
    @Autowired  private EmployeeRepository employeeRepository;//객체 생성작업을 framework에 넘긴다  ==> 바로 인스턴스 사용 가능 
    @Autowired private DepartementRepository departementRepository; //객체 생성작업을 framework에 넘긴다 바로 인스턴스 사용 가능
    
    public ResposeDto<PostHumanResourceResponseDto> postHumanResource(PostHumanResourceRequestDto dto){

        String telNumber = dto.getTelNumber();

        try{
            boolean hasTelNumber = employeeRepository.existByTelNumber(telNumber);
            if(hasTelNumber){
                return ResponseDto.setFail("Existed TelPhone Number");
            }
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail("Database Error");
        }

        PostHumanResourceResponseDto data = new PostHumanResourceResponseDto();

        return ResponseDto.setSuccess("Sucess",data);
    }
}
