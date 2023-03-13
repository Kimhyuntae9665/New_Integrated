package com.koreait.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
//- @Getter, @Setter, @ToString, @RequiredArgsConstructor, @EqualsAndHashCode 어노테이션이 합쳐진 어노테이션
@AllArgsConstructor
//- 매개변수가 없는 생성자를 작성해주는 어노테이션
@NoArgsConstructor
//- 모든 멤버변수를 매개변수로 받는 생성자를 작성해주는 어노테이션

public class GetTestResponseDto {
	
	
	private int number;
	private String text;
	
	
	
	
	
	
	

}
