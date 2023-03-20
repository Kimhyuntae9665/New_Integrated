package com.hoodoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hoodoo.board.entity.RelatedSearchWordEntity;
import com.hoodoo.board.entity.resultSet.RelatedSearchWordResultSet;
import com.hoodoo.board.entity.resultSet.SearchWordResultSet;
import com.hoodoo.board.entity.resultSet.SearchWordResultSet;

@Repository
public interface RelatedSearchWordRepository extends JpaRepository<RelatedSearchWordEntity,Integer>{

    // ^ 조건문 너무 복잡해서 규칙대로 생성하는 Jpa 함수 사용 불가 하니 

    @Query(value="SELECT previous_search_word AS previousSearchWord, count(previous_search_word) AS count "+
    "FROM Relatedsearchwordlog "+
    "WHERE search_word=?1 "+
    "GROUP BY previous_search_word "+ 
    "ORDER BY count "+
    "DESC LIMIT 15",nativeQuery=true)
    public List<RelatedSearchWordResultSet> findTop15(String searchWord); 
    
}
