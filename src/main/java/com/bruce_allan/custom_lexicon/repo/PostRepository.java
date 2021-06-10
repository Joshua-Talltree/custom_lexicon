package com.bruce_allan.custom_lexicon.repo;

import java.util.List;

import com.bruce_allan.custom_lexicon.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByDefinitionContains(String term);
    
}

