package com.shen.redditclone.services;

import com.shen.redditclone.domain.Link;
import com.shen.redditclone.repositery.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository){
        this.linkRepository=linkRepository;
    }

    public List<Link> findAll(){
        return linkRepository.findAll();
    }

    public Link save(Link link){
        return linkRepository.save(link);
    }

    public Optional<Link> findById(Long id){
        return linkRepository.findById(id);
    }

}
