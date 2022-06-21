package com.agustinvon.JibberJabber.controller;

import com.agustinvon.JibberJabber.model.Post;
import com.agustinvon.JibberJabber.model.dto.PostDTO;
import com.agustinvon.JibberJabber.model.forms.PostForm;
import com.agustinvon.JibberJabber.model.Reply;
import com.agustinvon.JibberJabber.model.forms.ReplyForm;
import com.agustinvon.JibberJabber.model.responses.FollowResponse;
import com.agustinvon.JibberJabber.service.PostService;
import com.agustinvon.JibberJabber.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final RestService restService;
    @Value("${followers.uri}")
    private String followersUri;

    @Autowired
    public PostController(PostService postService, RestService restService) {
        this.restService = restService;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody PostForm postForm, Principal principal) {
        Post postCreated = postService.createNewPost(postForm, principal.getName());
        return ResponseEntity.ok(postCreated);
    }

    @GetMapping
    public Page<Post> getMyPosts(@RequestParam Optional<Integer> page, Principal principal) {
        return postService.listPostsFromUser(principal.getName(), page.orElse(0));
    }

    @GetMapping("/{username}")
    public Page<PostDTO> getPostsFromUser(@PathVariable String username, @RequestParam Optional<Integer> page) {
        return postService.listPostsFromUser(username, page.orElse(0)).map(post -> new PostDTO(post.getContent(), post.getUsername(), post.getTimestamp()));
    }

    @GetMapping("/follows")
    public List<Post> getPostsFromFollowing(@RequestHeader(value="Authorization") String authHeader , Principal principal) {
        FollowResponse response = restService.getFollows(followersUri, authHeader);
        return postService.getPostsFromUsers(response.getFollowList());
    }

    @GetMapping("/details/{postId}")
    public Post getPostById(@PathVariable UUID postId) {
        return postService.getPost(postId);
    }

    @DeleteMapping("/{postId}")
    public Post deletePost(@PathVariable UUID postId) {
        return postService.deletePost(postId);
    }

    @PostMapping("/{postId}/reply")
    public Reply replyPost(@PathVariable UUID postId, @RequestBody ReplyForm replyForm, Principal principal) {
        return postService.replyToPost(postId, replyForm, principal.getName());
    }
}
