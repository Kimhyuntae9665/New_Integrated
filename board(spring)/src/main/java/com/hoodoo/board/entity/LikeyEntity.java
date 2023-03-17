package com.hoodoo.board.entity;

import javax.annotation.Generated;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.hoodoo.board.entity.primaryKey.LikeyPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Likey")
@Table(name=  "Likey")
@IdClass(LikeyPK.class)
public class LikeyEntity {

    @Id
    private String userEmail;
    @Id
    private int boardNumber;
    
    private String userProfileUrl;
    private String userNickname;

    public LikeyEntity(UserEntity userEntity,int boardNumber){
        this.userEmail = userEntity.getEmail();
        this.boardNumber = boardNumber;
        this.userProfileUrl = userEntity.getProfile();
        this.userNickname = userEntity.getNickname();
    }
    
    
}
