package com.capstone.feelcheck.service;

import com.capstone.feelcheck.dto.NicknameChangeDto;
import com.capstone.feelcheck.model.User;
import com.capstone.feelcheck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Long anonymousJoin(User user){
        Long userCount = userRepository.count();
        user.setNickname("익명"+userCount);
        userRepository.save(user);
        return userCount;
    }
    public void join(User user){
        userRepository.save(user);
    }

    public Boolean duplicateCheck(String nickname){
        return userRepository.findByNickname(nickname) == null;
    }
    public Boolean validateCheck(String nickname){
        return userRepository.findByNickname(nickname) != null;
    }

    public void changeNickname(NicknameChangeDto nicknameChangeDto){
        User user = userRepository.findByNickname(nicknameChangeDto.getNickname());
        user.setNickname(nicknameChangeDto.getNewNickname());
        userRepository.save(user);
    }
}
