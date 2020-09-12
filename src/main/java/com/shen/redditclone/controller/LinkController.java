package com.shen.redditclone.controller;

import com.shen.redditclone.RedditcloneApplication;
import com.shen.redditclone.domain.Link;
import com.shen.redditclone.repositery.LinkRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LinkController {

    private LinkRepository linkRepository;
    private static final Logger log = LoggerFactory.getLogger(LinkController.class);
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    // list
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("links",linkRepository.findAll());
        return "link/list";
    }

    // CRUD
    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }
    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkRepository.findById(id);
        if(link.isPresent()){
            model.addAttribute("link", link.get());
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }
        else{
            return "redirect:/";
        }
    }

    @PutMapping("/{id}")
    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        linkRepository.deleteById(id);
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model){
        model.addAttribute("link", new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(
            @Valid Link link, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors()){
            log.info("validation found error when submitting a link");
            model.addAttribute("link", link);
            return "link/submit";
        }
        else{
            log.info("validation success when submitting a link");
            linkRepository.save(link);
            redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success", true);
        }
        return "redirect:/link/{id}";
    }
}
