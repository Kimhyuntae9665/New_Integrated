package com.hoodoo.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.hoodoo.board.entity.resultSet.SearchWordResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTop15SearchWordResponseDto {

    private List<String> getTop15SearchWordList;

    public static GetTop15SearchWordResponseDto copyList(List<SearchWordResultSet> list){

        List<String> result = new ArrayList<>();
        for(SearchWordResultSet item:list){
            result.add(item.getSearchWord());
        }

        return new GetTop15SearchWordResponseDto(result);

    }
    
}
