package com.agustinvon.JibberJabber.controller;

import com.agustinvon.JibberJabber.model.Post;
import com.agustinvon.JibberJabber.model.PostForm;
import com.agustinvon.JibberJabber.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody PostForm postForm, Principal principal) {
        Post postCreated = postService.createNewPost(postForm, principal.getName());
        return ResponseEntity.ok(postCreated);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.listAllPosts();
    }
}
