package com.shen.redditclone.domain;

import com.shen.redditclone.services.BeanUtil;
import lombok.*;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class Comment extends Auditable{

    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String body;

    @ManyToOne
    @NonNull
    private Link link;

    @NonNull
    private long linkID;

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
