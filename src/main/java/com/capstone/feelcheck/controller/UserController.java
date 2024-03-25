package com.capstone.feelcheck.controller;

import com.capstone.feelcheck.model.Comment;
import com.capstone.feelcheck.model.User;
import com.capstone.feelcheck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public ResponseEntity<?> userCreate(@RequestBody User user){
        Boolean nicknameCheck = (userRepository.findByNickname(user.getNickname())) == null;
        if (!nicknameCheck){
            return ResponseEntity.badRequest().body("이미 존재하는 닉네임입니다.");
        }
        userRepository.save(user);
        return ResponseEntity.ok("닉네임이 등록되었습니다.");
    }
}
