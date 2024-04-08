package com.capstone.feelcheck.controller;

import com.capstone.feelcheck.dto.NicknameChangeDto;
import com.capstone.feelcheck.dto.ResponseDto;
import com.capstone.feelcheck.model.User;
import com.capstone.feelcheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseDto userCreate(@RequestBody User user){
        // 닉네임을 입력하지 않은 경우
        Boolean isAnonymous = user.getNickname().equals("");
        if (isAnonymous){
            Long userCount = userService.anonymousJoin(user);
            return new ResponseDto(HttpStatus.OK.value(), "익명 로그인", "익명"+userCount);
        }
        // 닉네임을 입력한 경우
        Boolean nicknameCheck = userService.duplicateCheck(user.getNickname());
        if (!nicknameCheck){
            return new ResponseDto(HttpStatus.CONFLICT.value(), "이미 존재하는 닉네임입니다.", null);
        }
        userService.join(user);
        return new ResponseDto(HttpStatus.OK.value(), "닉네임이 등록되었습니다.", user.getNickname());
    }

    @PutMapping("/api/users")  //본인 닉네임, 바꾸고싶은 닉네임을 받음
    public ResponseDto nicknameChange(@RequestBody NicknameChangeDto nicknameChangeDto){
        Boolean validateCheck = userService.validateCheck(nicknameChangeDto.getNickname());
        if (!validateCheck) {
            return new ResponseDto(HttpStatus.UNAUTHORIZED.value(), "사용자가 존재하지 않습니다.", null);
        }
        Boolean nicknameCheck = userService.duplicateCheck(nicknameChangeDto.getNewNickname());
        if (!nicknameCheck){
            return new ResponseDto(HttpStatus.CONFLICT.value(), "이미 존재하는 닉네임입니다.", null);
        }
        userService.changeNickname(nicknameChangeDto);
        return new ResponseDto(HttpStatus.OK.value(), "닉네임이 수정되었습니다.", nicknameChangeDto.getNewNickname());
    }
}