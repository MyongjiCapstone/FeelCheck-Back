package com.capstone.feelcheck.repository;

import com.capstone.feelcheck.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}
