package com.capstone.feelcheck.controller;

import com.capstone.feelcheck.dto.CommentDeleteDto;
import com.capstone.feelcheck.dto.CommentDto;
import com.capstone.feelcheck.dto.CommentGetDto;
import com.capstone.feelcheck.dto.ResponseDto;
import com.capstone.feelcheck.model.Comment;
import com.capstone.feelcheck.model.Emotion;
import com.capstone.feelcheck.service.CommentService;
import com.capstone.feelcheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    @Autowired
    public CommentController(CommentService commentService, UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/api/comments")
    public ResponseDto postComment(@RequestBody CommentDto commentDto){
        Boolean validateCheck = userService.validateCheck(commentDto.getNickname());
        if (!validateCheck){
            return new ResponseDto(HttpStatus.UNAUTHORIZED.value(), "닉네임 " + commentDto.getNickname() + "이 존재하지 않습니다.", null);
        }
        commentService.postComment(commentDto);
        return new ResponseDto(HttpStatus.OK.value(), "댓글이 성공적으로 작성되었습니다.", null);
    }
    @GetMapping("/api/comments")
    public ResponseDto getComments(CommentGetDto commentGetDto){
        List<Comment> comments = commentService.getComments(commentGetDto);
        return new ResponseDto(HttpStatus.OK.value(), null, comments);
    }
    @DeleteMapping("/api/comments")
    public ResponseDto deleteComment(@RequestBody CommentDeleteDto commentDeleteDto){
        Boolean validateCheck = userService.validateCheck(commentDeleteDto.getNickname());
        if (!validateCheck){
            return new ResponseDto(HttpStatus.UNAUTHORIZED.value(), "사용자가 존재하지 않습니다", null);
        }
        Boolean commentValidateCheck = commentService.validateCheck(commentDeleteDto);
        if (!commentValidateCheck){
            return new ResponseDto(HttpStatus.NOT_FOUND.value(), "댓글이 존재하지 않습니다.", null);
        }
        Boolean commentWriterCheck = commentService.commentWriterCheck(commentDeleteDto);
        if (!commentWriterCheck){
            return new ResponseDto(HttpStatus.FORBIDDEN.value(), "잘못된 접근입니다.", null);
        }
        commentService.deleteComment(commentDeleteDto);
        return new ResponseDto(HttpStatus.OK.value(), "댓글이 성공적으로 삭제되었습니다.", null);
    }
}
