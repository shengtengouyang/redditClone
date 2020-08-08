package com.shen.redditclone.repositery;

import com.shen.redditclone.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
