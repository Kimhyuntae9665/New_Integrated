package com.koreait.board.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.board.Entity.DepartementEntity;
import com.koreait.board.Repository.DepartementRepository;
import com.koreait.board.Repository.EmployeeRepository;
import com.koreait.board.common.constant.ResponseMessage;
import com.koreait.board.dto.request.departement.PostDepartementRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.departement.GetAllDepartementListResponseDto;
import com.koreait.board.dto.response.departement.PostDepartementResponseDto;

@Service
public class DepartementService {

    @Autowired EmployeeRepository employeeRepository;
    @Autowired DepartementRepository departementRepository;
    
    public ResponseDto<PostDepartementResponseDto> postDepartement(PostDepartementRequestDto dto){

        PostDepartementResponseDto data = null;


        int cheifEmployeeNumber = dto.getChief();

        try{

            boolean hasEmployee = employeeRepository.existsById(cheifEmployeeNumber);
            if(!hasEmployee){
                return ResponseDto.setFail(ResponseMessage.NOT_EXIST_EMPLOYEE_NUMBER);
            }

            DepartementEntity departementEntity = new DepartementEntity(dto);
            departementRepository.save(departementEntity);

            data = new PostDepartementResponseDto(departementEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<List<GetAllDepartementListResponseDto>> getAllDepartementList(){
        // ^ List는 interface이다 객체 생성불가 ==> ArrayList이용 
        

        List<GetAllDepartementListResponseDto> data = new ArrayList<GetAllDepartementListResponseDto>();


        try{

           List<DepartementEntity> departementList =  departementRepository.findAll();

           data = GetAllDepartementListResponseDto.copyList(departementList);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
        
    }
    
}
