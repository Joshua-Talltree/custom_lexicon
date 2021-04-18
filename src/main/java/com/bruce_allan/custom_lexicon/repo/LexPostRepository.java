package com.bruce_allan.custom_lexicon.repo;

import com.bruce_allan.custom_lexicon.models.LexPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LexPostRepository extends JpaRepository<LexPost, Long> {

    @Query("from LexPost lex_post where lex_post.lex_definition like %:term%")
    List<LexPost> searchByBodyLike(@Param("term") String term);

}

