package com.koreait.board.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.Entity.Example_Entity;
import com.koreait.board.Repository.ExampleRespository;
import com.koreait.board.dto.GetTestResponseDto;
import com.koreait.board.dto.PostTestRequestDto;
import com.koreait.board.dto.ResponseDto;


// # Service 

// ^Service는 위에서 언급했듯이 Repository에서 얻어온 정보를 바탕으로 자바 문법을 이용하여 가공 후 다시 Controller에게 정보를 보내는 곳입니다.
// ? 실제 비즈니스 로직을 담당하는 레이어 
// ? 일반적인 연산작업
// ? Controller로 사용자의 입력을 받아오면 해당기능을 진행하기 위해 
// ? Repository에서 데이터를 가져와 작업을 진행함 
// ? Service에서 Controller로 넘겨준디 
@Service
public class MainService {
    @Autowired //인스턴스 생성없이 MainService에서 ExmapleRepository 인스턴스를 바로 쓸수 있게 해주는 @Autowired
    private ExampleRespository exampleRespository;
    // // # Service 

// ^Service는 위에서 언급했듯이 Repository에서 얻어온 정보를 바탕으로 자바 문법을 이용하여 가공 후 다시 Controller에게 정보를 보내는 곳입니다.

    

    public ResponseDto<String> getMain(){

        // ^ Example_Entity(인스턴스)를 ExampleRepository(데이터베이스)에 집어넣는 과정 
        Example_Entity example_Entity = Example_Entity.builder().comment("Hello").number(10).build();
        exampleRespository.save(example_Entity);
        // ^ 실제 Mysql 데이터베이스에 데이터가 들어가 있다 

        ResponseDto<String> result = ResponseDto.setSuccess("sucess", "Hello hoodoo");
        return result;
    }

    
    
   

    // ? 자바에서 자바를 호출 하는 것 
    public ResponseDto<String> getVariable(String dataa){
        
        Example_Entity example_Entity  = exampleRespository.findByComment(dataa);
        String string = example_Entity.toString();

        ResponseDto<String> result = ResponseDto.setSuccess("sucess", string);
        return result;
    }


    public  ResponseDto<String> postMain(){
        ResponseDto<String> result = ResponseDto.setSuccess("sucess", "Post mail");
        return result;
    }
    public ResponseDto<String> postRequestBody(String data){
        String string = "Post body data is "+data ;
        ResponseDto<String> result = ResponseDto.setSuccess("sucess", string);

         return result;
    }

    public ResponseDto<String> patchMain(){
        String string = "Patch 메서드는 수정 작업을 지정한 메서드 입니다 ";
        ResponseDto<String> result = ResponseDto.setSuccess("sucess", string);
        return result;
    }

    public ResponseDto<String> deleteMain(){
        String string = "delete 메서드는 삭제 작업을 지정한 메서드 입니다 ";
        ResponseDto<String> result = ResponseDto.setSuccess("sucess", string);
        return result;
        
       

    }

    public ResponseDto<String> postTest(PostTestRequestDto dto){
        String string = dto.toString();
        ResponseDto<String> result = ResponseDto.setSuccess("sucess", string);
        return result;
    }

    public ResponseDto<GetTestResponseDto> getTest(){
        GetTestResponseDto data = new  GetTestResponseDto(10,"hoodoo");
        ResponseDto<GetTestResponseDto> result = ResponseDto.setSuccess("sucess", data);
        return result;
    }

    public void descriptionJpaMethod(){
        // # JpaRepository 기본 메서드 
        // ! findById(PK).get();
        // ? 해당 테이블에서 PK를 기준으로 값을 검색 해옴
        Example_Entity example_Entity = exampleRespository.findById(0).get();

        // ! findAll();
        // ? 해당 테이블의 모든 레코드를 검색한 결과를 반환
        List <Example_Entity> example_List = exampleRespository.findAll();
 
        // ! save(entityInstance)
        // ? 해당 테이블에 특정 레코드를 삽입 혹은 수정
        // ? Primary Key를 기준으로 Primary key에 해당하는 레코드가 없으면 '삽입'
        // ? Primary Key에 해당하는 레코드가 있으면 해당 레코드를 '수정' 
        exampleRespository.save(example_Entity);

        // ! existsById(PK);
        // ? 해당 테이블에 PK를 기준으로 레코드가 존재한다면 true를 반환,
        // ? 존재하지 않는다면 false를 반환 
        boolean hasEntity = exampleRespository.existsById(0);
        
        // !deleteById(PK);
        // ? 해당 테이블에 PK를 기준으로 특정 레코드를 삭제 
        exampleRespository.deleteById(0);
    }
}
