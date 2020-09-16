package com.shen.redditclone.controller;

import com.shen.redditclone.domain.Link;
import com.shen.redditclone.domain.Vote;
import com.shen.redditclone.services.LinkService;
import com.shen.redditclone.services.VoteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VoteController {
    @NonNull
    private VoteService voteService;
    @NonNull
    private LinkService linkService;

    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/voteCount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount){
        Optional<Link> optionalLink = linkService.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            Vote vote=new Vote(direction, link);
            voteService.save(vote);
            int updateVoteCount = voteCount + direction;
            link.setVoteCount(updateVoteCount);
            linkService.save(link);
            return updateVoteCount;
        }
        return voteCount;
    }
}
