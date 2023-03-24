package com.hoodoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoodoo.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer>{

    public BoardEntity findByBoardNumber(int boardNumber);
    // ^ 역순으로 == 최신순으로 
    public List<BoardEntity> findByOrderByBoardWriteDatetimeDesc();
    public List<BoardEntity> findByWriterEmailOrderByBoardWriteDatetimeDesc(String writerEmail);


    public List<BoardEntity> findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(String boardTitle,String boardContnet);

    // ^ 쿼리문 사용으로 복잡한 sql 명령문 사용가능 

    public List<BoardEntity> findTop3ByOrderByLikeCountDesc();
    
    
}
