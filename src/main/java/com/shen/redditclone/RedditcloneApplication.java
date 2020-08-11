package com.shen.redditclone;

import com.shen.redditclone.domain.Comment;
import com.shen.redditclone.domain.Link;
import com.shen.redditclone.repositery.CommentRepository;
import com.shen.redditclone.repositery.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RedditcloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditcloneApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
        return args -> {
            Link link=new Link("Getting started with spring boot 2",  "https://therealdanvega.com/spring-boot-2");
            linkRepository.save(link);
            Comment comment=new Comment("This spring boot 2 link is awsome", link);
            commentRepository.save(comment);
            link.addComment(comment);
            System.out.println("We just inserted a link and a comment");
        };
    }
}
