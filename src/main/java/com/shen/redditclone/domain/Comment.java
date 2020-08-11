package com.shen.redditclone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable{

    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String body;

    @ManyToOne
    @NonNull
    private Link link;
}
