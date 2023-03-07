package com.koreait.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.Entity.EmployeeEntity;
import com.koreait.board.Repository.DepartementRepository;
import com.koreait.board.Repository.EmployeeRepository;
import com.koreait.board.common.constant.ResponseMessage;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;

import static com.koreait.board.common.constant.ResponseMessage.EXIST_TELEPHONE_NUMBER;

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
            if(hasTelNumber) return ResponseDto.setFail(EXIST_TELEPHONE_NUMBER);
            if(hasTelNumber){
                return ResponseDto.setFail("Existed TelPhone Number");
            }

            if(departementCode != null){
                boolean hasDepartement = departementRepository.existsById(departementCode);
                if(!hasDepartement) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_DEPARTEMENT_CODE);
            }

            EmployeeEntity employeeEntity = new EmployeeEntity(dto);
            employeeRepository.save(employeeEntity);

            data = new PostHumanResourceResponseDto(employeeEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        

        return ResponseDto.setSuccess("Sucess",data);
    }

    public ResponseDto<GetHumanResourceResponseDto> getHumanResource(int employeeNumber){
        
        GetHumanResourceResponseDto data = null;


        try{
            // boolean hasEmployee = employeeRepository.existsById(employeeNumber); //사번이 존재하는지 부터 먼저 검사
            // if(!hasEmployee) return ResponseDto.setFail("존재 X");
            // EmployeeEntity employeeEntity = employeeRepository.findById(employeeNumber).get();
            // ^ 데이터 베이스에 2번 접근 소모가 크다 

            EmployeeEntity employeeEntity = employeeRepository.findByEmployeeNumber(employeeNumber);
            if(employeeEntity == null){
                return ResponseDto.setFail(ResponseMessage.NOT_EXIST_EMPLOYEE_NUMBER);
            }

            data = new GetHumanResourceResponseDto(employeeEntity);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
