package com.capstone.feelcheck.controller;

import com.capstone.feelcheck.dto.CommentDto;
import com.capstone.feelcheck.dto.ResponseDto;
import com.capstone.feelcheck.model.Comment;
import com.capstone.feelcheck.model.User;
import com.capstone.feelcheck.repository.CommentRepository;
import com.capstone.feelcheck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/comments")
    public ResponseDto postComment(@RequestBody CommentDto commentDto){
        User user = userRepository.findByNickname(commentDto.getNickname());
        if (user == null){
            return new ResponseDto(HttpStatus.UNAUTHORIZED.value(), "닉네임 '" + commentDto.getNickname() + "' 이 존재하지 않습니다.");
        }
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setEmotion(commentDto.getEmotion());
        comment.setComment(commentDto.getComment());
        commentRepository.save(comment);
        return new ResponseDto(HttpStatus.OK.value(), "댓글이 성공적으로 작성되었습니다.");
    }
}
