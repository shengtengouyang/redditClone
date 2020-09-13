package com.shen.redditclone;

import com.shen.redditclone.domain.Comment;
import com.shen.redditclone.domain.Link;
import com.shen.redditclone.repositery.CommentRepository;
import com.shen.redditclone.repositery.LinkRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RedditcloneApplication {
    private static final Logger log = LoggerFactory.getLogger(RedditcloneApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RedditcloneApplication.class, args);
    }
    @Bean
    PrettyTime prettyTime(){
        return new PrettyTime();
    }
}
