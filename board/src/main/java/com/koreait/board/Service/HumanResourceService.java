package com.koreait.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.Repository.DepartementRepository;
import com.koreait.board.Repository.EmployeeRepository;

@Service
public class HumanResourceService {
        
    @Autowired  private EmployeeRepository employeeRepository;//객체 생성작업을 framework에 넘긴다 
    @Autowired private DepartementRepository departementRepository; //객체 생성작업을 framework에 넘긴다
}
