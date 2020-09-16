package com.shen.redditclone.controller;

import com.shen.redditclone.domain.Comment;
import com.shen.redditclone.domain.Link;
import com.shen.redditclone.services.CommentService;
import com.shen.redditclone.services.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LinkController {

    private LinkService linkService;
    private static final Logger log = LoggerFactory.getLogger(LinkController.class);
    private CommentService commentService;

    public LinkController(LinkService linkService, CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    // list
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("links",linkService.findAll());
        return "link/list";
    }

    // CRUD

    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id,Model model) {
        Optional<Link> optionalLink = linkService.findById(id);
        if( optionalLink.isPresent() ) {
            Link link = optionalLink.get();
            Comment comment = new Comment();
            comment.setLinkID(link.getId());
            model.addAttribute("comment",comment);
            model.addAttribute("linkID",comment.getLinkID());
            model.addAttribute("link",link);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        } else {
            return "redirect:/";
        }
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
            linkService.save(link);
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
        }
        return "redirect:/link/{id}";
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/link/comments")
    public String addComment(Comment comment, BindingResult bindingResult) {
        Long linkID=comment.getLinkID();
        Optional<Link> link=linkService.findById(linkID);
        if( bindingResult.hasErrors()||!link.isPresent()) {
            log.info(bindingResult.toString());
            log.info("Something went wrong.");
        } else {
            log.info("New Comment Saved!");
            comment.setLink(link.get());
            commentService.save(comment);
//            comment.getLink().addComment(comment);
        }
        return "redirect:/link/"+comment.getLinkID();
    }
}
