package com.bruce_allan.custom_lexicon.controllers;

import com.bruce_allan.custom_lexicon.models.LexPost;
import com.bruce_allan.custom_lexicon.models.User;
import com.bruce_allan.custom_lexicon.repo.LexPostRepository;
import com.bruce_allan.custom_lexicon.repo.UserRepository;
import com.bruce_allan.custom_lexicon.services.EmailServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LexPostController {

    private final LexPostRepository postDao;
    private final EmailServices emailService;
    private final UserRepository userDao;


    public LexPostController(LexPostRepository postDao, UserRepository userDao, EmailServices emailService) {
        this.postDao = postDao;
        this.emailService = emailService;
        this.userDao = userDao;
    }

    @GetMapping("/lex_posts")
    public String allPosts(Model viewModel) {
        List<LexPost> postsFromDB = postDao.findAll();
        viewModel.addAttribute("posts", postsFromDB);
        return "lex_posts/index";
    }

    @GetMapping("/lex_posts/{id}")
    public String individualPosts(@PathVariable Long id, Model vModel) {
        vModel.addAttribute("post", postDao.getOne(id));
        return "lex_posts/show";
    }


    @GetMapping("/lex_posts/create")
    public String createPosts(Model model) {
        model.addAttribute("post", new LexPost());
        return "lex_posts/create";
    }

    @PostMapping("/lex_posts/create")
    public String createPostsHere(@ModelAttribute LexPost postToCreate) {

        User userToAdd = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // save the post
        postDao.save(postToCreate);
        // set the user
        postToCreate.setOwner(userToAdd);
        LexPost savedPost = postDao.save(postToCreate);
        emailService.prepareAndSend(savedPost,"Here is the title", "Here is the body");
        return "redirect:/lex_posts";
    }


    @GetMapping("/lex_posts/{id}/update")
    public String updatePostForm(Model model, @PathVariable Long id){
        model.addAttribute("post", postDao.getOne(id));
        return "lex_posts/create";
    }

    @PostMapping("/lex_post/{id}/update")
    public String updatePost(@ModelAttribute LexPost postToUpdate, @PathVariable Long id){

        User userToAdd = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        postToUpdate.setId(id);

        // set the user
        postToUpdate.setOwner(userToAdd);

        // save the post
        postDao.save(postToUpdate);

        return "redirect:/lex_posts";
    }

    @PostMapping("/lex_posts/{id}/delete")
    @ResponseBody
    public String deletePost(@PathVariable Long id) {
        postDao.deleteById(id);
        return "post deleted";
    }

    @GetMapping("/lex_posts/search")
    public String searchByKeyword(Model model, @RequestParam(name = "search") String term){
        List<LexPost> posts = postDao.searchByBodyLike(term);
        model.addAttribute("posts", posts);

        return "lex_posts/index";
    }
}
