package com.agustinvon.JibberJabber.controller;

import com.agustinvon.JibberJabber.model.Post;
import com.agustinvon.JibberJabber.model.dto.FullPostDTO;
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
    public ResponseEntity<PostDTO> addPost(@RequestBody PostForm postForm, Principal principal) {
        PostDTO postCreated = postService.createNewPost(postForm, principal.getName());
        return ResponseEntity.ok(postCreated);
    }

    @GetMapping
    public Page<PostDTO> getMyPosts(@RequestParam Optional<Integer> page, Principal principal) {
        return postService.listPostsFromUser(principal.getName(), page.orElse(0));
    }

    @GetMapping("/{username}")
    public Page<PostDTO> getPostsFromUser(@PathVariable String username, @RequestParam Optional<Integer> page) {
        return postService.listPostsFromUser(username, page.orElse(0));
    }

    @GetMapping("/all")
    public Page<PostDTO> getAllPosts(@RequestParam Optional<Integer> page) {
        return postService.listAllPosts(page.orElse(0));
    }

    /*
    @GetMapping("/follows")
    public List<Post> getPostsFromFollowing(@RequestHeader(value="Authorization") String authHeader , Principal principal) {
        FollowResponse response = restService.getFollows(followersUri, authHeader);
        return postService.getPostsFromUsers(response.getFollowList());
    }
     */

    @GetMapping("/details/{postId}")
    public FullPostDTO getPostById(@PathVariable UUID postId) {
        return postService.getFullPost(postId);
    }

    @DeleteMapping("/{postId}")
    public FullPostDTO deletePost(@PathVariable UUID postId, Principal principal) {
        return postService.deletePost(postId, principal.getName());
    }

    @PostMapping("/{postId}/reply")
    public FullPostDTO replyPost(@PathVariable UUID postId, @RequestBody ReplyForm replyForm, Principal principal) {
        return postService.replyToPost(postId, replyForm, principal.getName());
    }
}
