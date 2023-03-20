package com.hoodoo.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.hoodoo.board.entity.resultSet.RelatedSearchWordResultSet;
import com.hoodoo.board.entity.resultSet.SearchWordResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTop15RelatedSearchWordResponseDto {

    private List<String> getTop15SearchWordList;

    public static GetTop15RelatedSearchWordResponseDto copyList(List<RelatedSearchWordResultSet> list){

        List<String> result = new ArrayList<>();
        for(RelatedSearchWordResultSet item:list){
            result.add(item.getPreviousSearchWord());
        }

        return new GetTop15RelatedSearchWordResponseDto(result);

    }
    
}
