package com.agustinvon.JibberJabber.service;

import com.agustinvon.JibberJabber.exceptions.NotYourPostException;
import com.agustinvon.JibberJabber.model.Post;
import com.agustinvon.JibberJabber.model.Reply;
import com.agustinvon.JibberJabber.model.dto.FullPostDTO;
import com.agustinvon.JibberJabber.model.dto.PostDTO;
import com.agustinvon.JibberJabber.model.dto.ReplyDTO;
import com.agustinvon.JibberJabber.model.dto.UserDTO;
import com.agustinvon.JibberJabber.model.forms.PostForm;
import com.agustinvon.JibberJabber.model.forms.ReplyForm;
import com.agustinvon.JibberJabber.model.responses.Follow;
import com.agustinvon.JibberJabber.model.responses.UserReply;
import com.agustinvon.JibberJabber.repository.PostRepository;
import com.agustinvon.JibberJabber.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private ReplyRepository replyRepository;
    private RestService restService;

    @Value("${client.secret}")
    private String clientSecret;
    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Autowired
    public PostService(PostRepository postRepository, ReplyRepository replyRepository, RestService restService) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
        this.restService = restService;
    }

    public PostService() {
    }

    private FullPostDTO getFullDTOFromPost(Post post) {
        String token = restService.getAdminAccessToken(clientSecret, keycloakUrl + "/realms/master/protocol/openid-connect/token");
        UserReply user = restService.getUserInformation(keycloakUrl + "/admin/realms/" + keycloakRealm + "/users/", token, post.getUserID());
        UserDTO userDTO = new UserDTO(post.getUserID(), user.getFirstName(), user.getUsername(), "placeholder");
        return new FullPostDTO(post.getId(), post.getContent(), post.getTimestamp(),getReplyDTOFromReplyList(post.getReplies()) , userDTO);
    }

    private PostDTO getDTOFromPost(Post post) {
        FullPostDTO fullPostDTO = getFullDTOFromPost(post);
        return new PostDTO(fullPostDTO.getId(), fullPostDTO.getContent(), fullPostDTO.getLocalDateTime(), fullPostDTO.getUser());
    }

    private Page<FullPostDTO> getFullDTOFromPostPage(Page<Post> posts) {
        String token = restService.getAdminAccessToken(clientSecret, keycloakUrl + "/realms/master/protocol/openid-connect/token");
        UserReply[] replies = restService.getAllUsersInformation(keycloakUrl + "/admin/realms/JibberJabber/users", token);
        return posts.map(post -> {
            String userId = post.getUserID();
            Optional<UserReply> userReplyFromPost = Optional.empty();
            for (UserReply reply: replies) {
                if(reply.getId().equals(userId)) {
                    userReplyFromPost = Optional.of(reply);
                }
            }
            if (userReplyFromPost.isPresent()) {
                UserReply reply= userReplyFromPost.get();
                UserDTO user = new UserDTO(reply.getId(), reply.getFirstName(), reply.getUsername(), "placeholder");
                return new FullPostDTO(post.getId(), post.getContent(), post.getTimestamp(), getReplyDTOFromReplyList(post.getReplies()), user);
            } else {
                return null;
            }
        });
    }

    private Page<PostDTO> getDTOFromPostPage(Page<Post> posts) {
        Page<FullPostDTO> fullPostDTOS = getFullDTOFromPostPage(posts);
        return fullPostDTOS.map(fullPostDTO -> new PostDTO(fullPostDTO.getId(), fullPostDTO.getContent(), fullPostDTO.getLocalDateTime(), fullPostDTO.getUser()));
    }

    private List<ReplyDTO> getReplyDTOFromReplyList(List<Reply> postReplies) {
        String token = restService.getAdminAccessToken(clientSecret, keycloakUrl + "/realms/master/protocol/openid-connect/token");
        UserReply[] userReplies = restService.getAllUsersInformation(keycloakUrl + "/admin/realms/JibberJabber/users", token);
        return postReplies.stream().map(postReply -> {
            String userId = postReply.getUserID();
            Optional<UserReply> userReplyFromPost = Optional.empty();
            for (UserReply reply: userReplies) {
                if(reply.getId().equals(userId)) {
                    userReplyFromPost = Optional.of(reply);
                }
            }
            if (userReplyFromPost.isPresent()) {
                UserReply reply= userReplyFromPost.get();
                UserDTO user = new UserDTO(reply.getId(), reply.getFirstName(), reply.getUsername(), "placeholder");
                return new ReplyDTO(postReply.getId(), postReply.getContent(), postReply.getDateTime(), user);
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    public PostDTO createNewPost(PostForm postForm, String userName) {
        Post postCreated = new Post(postForm.getContent(), userName, LocalDateTime.now());
        postRepository.save(postCreated);
        return getDTOFromPost(postCreated);
    }

    public Page<PostDTO> listAllPosts(int page) {
        Pageable request = PageRequest.of(page, 10);
        Page<Post> posts = postRepository.findAll(request);
        return getDTOFromPostPage(posts);
    }

    public Page<PostDTO> listPostsFromUser(String username, int page) {
        Pageable request = PageRequest.of(page, 10);
        Page<Post> posts = postRepository.findByUserID(request, username);
        return getDTOFromPostPage(posts);
    }

    public FullPostDTO getFullPost(UUID postId) {
        return getFullDTOFromPost(getPost(postId));
    }

    public Post getPost(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post does not exist"));
        return post;
    }

    public FullPostDTO deletePost(UUID postId, String userId) {
        Post postToDelete = getPost(postId);
        if (postToDelete.getUserID().equals(userId)) throw new NotYourPostException("Post is from other user");
        postRepository.deleteById(postId);
        return getFullDTOFromPost(postToDelete);
    }

    public FullPostDTO replyToPost(UUID postId, ReplyForm replyForm, String name) {
        Post postToReply = getPost(postId);
        Reply reply = new Reply(name, replyForm.getContent(), LocalDateTime.now(), postToReply);
        replyRepository.save(reply);
        return getFullDTOFromPost(postToReply);
    }

    public List<Post> getPostsFromUsers(List<Follow> followList) {
        List<Post> posts = new ArrayList<>();
        followList.forEach(list -> {
            posts.addAll(postRepository.findByUserID(list.getFollowedUser()));
        });
        return posts;
    }
}
