package com.capstone.feelcheck.repository;

import com.capstone.feelcheck.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
