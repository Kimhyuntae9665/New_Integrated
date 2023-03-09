package com.koreait.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koreait.board.Entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer>{   //JpaRepository 상속해야지  ORM 사용 가능 
                                            // ^ 두번째 Generic은 EmployeeEntity의 PrimaryKey의 type을 적어준다 
    
    // ^ 사용자 구현 함수라도 JPA가 함수명을 일고 그에따라 함수 기능 구현 
    // ! 하지만 함수 이름에도 규칙이있다 existBy필드명 이런식으로 정확하게 함수명을 적어줘야한다 
    public boolean existsByTelNumber(String telNumber);
    public boolean existsByDepartement(String departement);

    public EmployeeEntity findByEmployeeNumber(int employeeNumber);
}
