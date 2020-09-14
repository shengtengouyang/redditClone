package com.shen.redditclone.repositery;

import com.shen.redditclone.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
