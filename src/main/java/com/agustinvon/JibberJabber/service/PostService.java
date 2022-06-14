package com.agustinvon.JibberJabber.service;

import com.agustinvon.JibberJabber.model.Post;
import com.agustinvon.JibberJabber.model.Reply;
import com.agustinvon.JibberJabber.model.dto.PostDTO;
import com.agustinvon.JibberJabber.model.forms.PostForm;
import com.agustinvon.JibberJabber.model.forms.ReplyForm;
import com.agustinvon.JibberJabber.repository.PostRepository;
import com.agustinvon.JibberJabber.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;
    private ReplyRepository replyRepository;

    @Autowired
    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
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

    public Page<Post> listPostsFromUser(String username, int page) {
        Pageable request = PageRequest.of(page, 10);
        return postRepository.findByUsername(request, username);
    }

    public Post getPost(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post does not exist"));
    }

    public Post deletePost(UUID postId) {
        Post postToDelete = getPost(postId);
        postRepository.deleteById(postId);
        return postToDelete;
    }

    public Reply replyToPost(UUID postId, ReplyForm replyForm, String name) {
        Post postToReply = getPost(postId);
        Reply reply = new Reply(name, replyForm.getContent(), LocalDateTime.now(), postToReply);
        replyRepository.save(reply);
        List<Reply> replies = new ArrayList<>(postToReply.getReplies());
        replies.add(reply);
        postToReply.setReplies(replies);
        return reply;
    }
}
