package com.agustinvon.JibberJabber.repository;

import com.agustinvon.JibberJabber.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Double> {
    List<Post> findAll();
}
