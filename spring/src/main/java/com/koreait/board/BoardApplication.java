package com.koreait.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//**@SpringBootApplication** 
//- 해당 어노테이션이 달린 클래스가 있는 패키지를 베이스 패키지로 간주
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
