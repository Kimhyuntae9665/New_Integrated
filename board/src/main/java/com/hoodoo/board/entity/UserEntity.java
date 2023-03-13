package com.hoodoo.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {
    
    @Id
    private String email;
    private String password;
    private String nickname;
    private String telNumber;
    private String address;
    private String profile;


    
}
