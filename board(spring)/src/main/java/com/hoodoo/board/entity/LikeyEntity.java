package com.hoodoo.board.entity;

import javax.annotation.Generated;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class LikeyEntity {


    
    @EmbeddedId
    private LikeyPK likeyPK;
    
    private String userProfile;
    private String userNickname;
    
    
}
