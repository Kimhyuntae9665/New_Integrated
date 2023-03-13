package com.hoodoo.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SearchWordLog")
@Table(name = "SearchWordLog")
public class SearchWordLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int sequence;
    private String searchWord;
    
}
