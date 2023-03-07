package com.koreait.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.Entity.EmployeeEntity;
import com.koreait.board.Repository.DepartementRepository;
import com.koreait.board.Repository.EmployeeRepository;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;

@Service
public class HumanResourceService {
        
    @Autowired  private EmployeeRepository employeeRepository;//객체 생성작업을 framework에 넘긴다  ==> 바로 인스턴스 사용 가능 
    @Autowired private DepartementRepository departementRepository; //객체 생성작업을 framework에 넘긴다 바로 인스턴스 사용 가능
    
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(PostHumanResourceRequestDto dto){

        PostHumanResourceResponseDto data = null;

        String telNumber = dto.getTelNumber();
        String departementCode = dto.getDepartement();

        try{
            boolean hasTelNumber = employeeRepository.existsByTelNumber(telNumber);
            if(hasTelNumber){
                return ResponseDto.setFail("Existed TelPhone Number");
            }

            if(departementCode != null){
                boolean hasDepartement = departementRepository.existsById(departementCode);
                if(!hasDepartement) return ResponseDto.setFail("Does not exist Departement Code");
            }

            EmployeeEntity employeeEntity = new EmployeeEntity(dto);
            employeeRepository.save(employeeEntity);

            data = new PostHumanResourceResponseDto(employeeEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail("Database Error");
        }

        

        return ResponseDto.setSuccess("Sucess",data);
    }

    public ResponseDto<GetHumanResourceResponseDto> getHumanResource(int employeeNumber){


        try{

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail("Data Error");
        }
    }
}
