package com.shen.redditclone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private short direction;

    @NonNull
    @ManyToOne
    private Link link;
    //user
    //link
}
