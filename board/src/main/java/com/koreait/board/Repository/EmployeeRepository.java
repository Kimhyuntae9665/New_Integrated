package com.koreait.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koreait.board.Entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer>{   //JpaRepository 상속해야지  ORM 사용 가능 
                                            // ^ 두번째 Generic은 EmployeeEntity의 PrimaryKey의 type을 적어준다 
    

    public boolean existsByTelNumber(String telNumber);
    public boolean existsByDepartement(String departement);

    public EmployeeEntity findByEmployeeNumber(int employeeNumber);
}
