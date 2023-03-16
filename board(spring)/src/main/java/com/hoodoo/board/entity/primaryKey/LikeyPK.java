package com.hoodoo.board.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;


// ^ 복합키 클래스 만드는 방법 
@Data
public class LikeyPK implements Serializable {


    @Column(name = "user_email")
    private String userEmail;


    @Column(name = "board_number")
    private int boardNumber;
    
}
