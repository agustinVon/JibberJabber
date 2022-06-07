package com.agustinvon.JibberJabber.service;

import com.agustinvon.JibberJabber.model.Post;
import com.agustinvon.JibberJabber.model.PostForm;
import com.agustinvon.JibberJabber.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostService() {
    }

    public Post createNewPost(PostForm postForm, String userName) {
        Post postCreated = new Post(postForm.getContent(), userName, LocalDateTime.now());
        postRepository.save(postCreated);
        return postCreated;
    }

    public List<Post> listAllPosts() {
        return postRepository.findAll();
    }
}
