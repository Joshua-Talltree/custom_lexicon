package com.bruce_allan.custom_lexicon.controllers;

import com.bruce_allan.custom_lexicon.models.Post;
import com.bruce_allan.custom_lexicon.models.User;
import com.bruce_allan.custom_lexicon.repo.PostRepository;
import com.bruce_allan.custom_lexicon.repo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model viewModel) {
        List<Post> postsFromDB = postDao.findAll();
        viewModel.addAttribute("posts", postsFromDB);
        return "posts/index";
    }

    @GetMapping("/posts/create")
    public String createPosts(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPostsHere(@ModelAttribute Post postToCreate) {
        User userToAdd = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // save the post
        postDao.save(postToCreate);
        // set the user
        postToCreate.setOwner(userToAdd);
        Post savedPost = postDao.save(postToCreate);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/update")
    public String updatePostForm(Model model, @PathVariable Long id){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/create";
    }

    @PostMapping("/posts/search")
    public String searchPosts(@RequestParam(name = "word") String term, Model vModel) {
        vModel.addAttribute("posts", postDao.findAllByContentContains(term));
        vModel.addAttribute("word", term);
        return "posts/index";
    }
}
