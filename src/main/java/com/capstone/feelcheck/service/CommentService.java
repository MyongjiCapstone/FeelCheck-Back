package com.capstone.feelcheck.service;

import com.capstone.feelcheck.dto.CommentDeleteDto;
import com.capstone.feelcheck.dto.CommentDto;
import com.capstone.feelcheck.dto.CommentGetDto;
import com.capstone.feelcheck.model.Comment;
import com.capstone.feelcheck.model.Emotion;
import com.capstone.feelcheck.model.User;
import com.capstone.feelcheck.repository.CommentRepository;
import com.capstone.feelcheck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public void postComment(CommentDto commentDto){
        User user = userRepository.findByNickname(commentDto.getNickname());
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setEmotion(commentDto.getEmotion());
        comment.setComment(commentDto.getComment());
        commentRepository.save(comment);
    }
    public List<Comment> getComments(CommentGetDto commentGetDto){
        int page = commentGetDto.getPage() - 1;
        int pageAmount = 8;
        Pageable pageable = PageRequest.of(page, pageAmount, Sort.by(Sort.Direction.DESC, "createDate"));
        return commentRepository.findAllByEmotion(commentGetDto.getEmotion(), pageable).getContent();
    }
    public void deleteComment(CommentDeleteDto commentDeleteDto) {
        Long commentId = commentDeleteDto.getCommentId();
        commentRepository.deleteById(commentId);
    }
    public Boolean validateCheck(CommentDeleteDto commentDeleteDto){
        return commentRepository.findById(commentDeleteDto.getCommentId()).isPresent();
    }
    public Boolean commentWriterCheck(CommentDeleteDto commentDeleteDto){
        User user = userRepository.findByNickname(commentDeleteDto.getNickname());
        Optional<Comment> comment = commentRepository.findById(commentDeleteDto.getCommentId());
        return comment.get().getUser().getId().equals(user.getId());
    }
}
