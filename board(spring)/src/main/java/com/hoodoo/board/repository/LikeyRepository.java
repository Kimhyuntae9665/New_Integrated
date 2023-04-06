package com.hoodoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hoodoo.board.entity.LikeyEntity;
import com.hoodoo.board.entity.primaryKey.LikeyPK;

@Repository
public interface LikeyRepository extends JpaRepository<LikeyEntity,LikeyPK>{
    
    public List<LikeyEntity> findByBoardNumber(int boardNumber);
    public LikeyEntity findByUserEmailAndBoardNumber(String userEmail,int boardNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);
}
