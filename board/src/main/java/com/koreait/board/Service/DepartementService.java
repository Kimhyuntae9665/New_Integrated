package com.koreait.board.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.board.dto.response.ResponseDto;

@Service
public class DepartementService {
    
    private static final POST_DEPARTEMENT = "/";


    @PostMapping()
    public ResponseDto<?> postDepartement(){

    }
}
