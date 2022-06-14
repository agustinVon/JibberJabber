package com.agustinvon.JibberJabber.repository;

import com.agustinvon.JibberJabber.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {
    List<Post> findAll();
    Page<Post> findByUsername(Pageable pageable,@Param("username") String username);
}
