package com.koreait.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "set")
// 뒤에 staticName을 적어서 이 fullArgment 생성자의 별명을 set이라소 설정하는 것 
@NoArgsConstructor
public class ResponseDto<D> {
    // ? <>안의 설정 type은 
    
    // Response의 결과 상태 (정상 : true,비정상 : false )
    private boolean status;
    // Response 의 결과 메시지 
    private String message;
    // Response의 결과 데이터 
    // D는 나중에 data type 결정 하겠다는 표시 
    private D data;

    // 생성자 이기때문에 인스턴스 없이도 사용해야 해서 static 붙인다 
    // 인스턴스를 리턴하는 함수 



    //# 성공시에 대한 인스턴스를 생성해주는 static 생성자 
    // ? <D> Response <D> : 먼저 오는 <D>는 해당 메서드에서 독립적으로 사용할 제너릭을 지칭
    // ? 뒤에 오는 <D>는 ResponseDto 클래스에서 타입에 필요로 하는 제너릭을 지칭 
    public static <D> ResponseDto<D> setSuccess(String message,D data){
        return ResponseDto.set(true, message, data);
    }


    // # 실패시에 해당 인스턴스를 생성해주는 static 생성자 
    public static <D> ResponseDto<D> setFail(String message){
        return ResponseDto.set(false, message, null);

    }


}
