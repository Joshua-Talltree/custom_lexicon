package com.bruce_allan.custom_lexicon.repo;


import com.bruce_allan.custom_lexicon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
