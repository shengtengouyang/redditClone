package com.shen.redditclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Vote {
    
    private long id;
    private int vote;

    //user
    //link
}
