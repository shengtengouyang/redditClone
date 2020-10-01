package com.shen.redditclone.domain;

import com.shen.redditclone.services.BeanUtil;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Link extends Auditable{

    @Id
    @GeneratedValue
    private long id;

    @NonNull @NotEmpty(message = "Please enter a title")
    private String title;

    @NonNull
    private String body;

    // comments
    @OneToMany(mappedBy = "link")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "link")
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne
    private User user;

    private int voteCount = 0;
    
    public void addComment(Comment comment){
        comments.add(comment);
    }

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}

