package com.koreait.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koreait.board.Entity.DepartementEntity;

@Repository
public interface DepartementRepository extends JpaRepository<DepartementEntity,String>{ //두번째 제너릭 자리에는 DepartementEntity의 PK의 dataType을 쓴다 
    
}
