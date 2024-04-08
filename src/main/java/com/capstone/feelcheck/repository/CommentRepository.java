package com.capstone.feelcheck.repository;

import com.capstone.feelcheck.model.Comment;
import com.capstone.feelcheck.model.Emotion;
import com.capstone.feelcheck.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByEmotion(Emotion emotion);
}
