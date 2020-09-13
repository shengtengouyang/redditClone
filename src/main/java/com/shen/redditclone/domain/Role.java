package com.shen.redditclone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Role{
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
