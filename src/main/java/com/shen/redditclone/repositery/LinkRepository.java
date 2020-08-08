package com.shen.redditclone.repositery;

import com.shen.redditclone.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
