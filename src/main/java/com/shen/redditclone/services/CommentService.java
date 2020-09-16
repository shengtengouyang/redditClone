package com.shen.redditclone.services;

import com.shen.redditclone.domain.Comment;
import com.shen.redditclone.repositery.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }
}
