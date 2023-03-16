package com.hoodoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoodoo.board.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{
                                // ^ 보드 넘버를 최근 작성한 순서대로 가져온다 
    public List<CommentEntity> findByBoardNumberOrderByWriteDatetimeDesc(int boardNumber); 
}
