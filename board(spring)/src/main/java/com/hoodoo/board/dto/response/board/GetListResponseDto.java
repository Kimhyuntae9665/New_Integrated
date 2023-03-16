package com.hoodoo.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.hoodoo.board.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListResponseDto {

    // ^ React의 IPreview 형태의 Dto 만들기 
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImgUrl;
    private String boardWriteDatetime;
    private int viewCount;
    private String writerNickname;
    private String writerProfileUrl; 
    private int commentCount;
    private int likeCount;

    public GetListResponseDto(BoardEntity boardEntity){
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.boardContent = boardEntity.getBoardContent();
        this.boardImgUrl = boardEntity.getBoardImgUrl();
        this.boardWriteDatetime = boardEntity.getBoardWriteDatetime();
        this.viewCount = boardEntity.getViewCount();
        this.writerNickname =boardEntity.getWriterNickname();
        this.writerProfileUrl = boardEntity.getWriterProfileUrl();
        this.commentCount = boardEntity.getCommentCount();
        this.likeCount = boardEntity.getLikeCount();

    }

    public static List<GetListResponseDto> copyList(List<BoardEntity> boardEntityList){

        List<GetListResponseDto> list =  new ArrayList<>();

        for(BoardEntity boardEntity:boardEntityList){
                // ^ 여기서 담을 수 있도록 형 변환을 해 주는 느낌    
                GetListResponseDto dto = new GetListResponseDto(boardEntity);
                list.add(dto);
        }

        return list;
    }
    
}
