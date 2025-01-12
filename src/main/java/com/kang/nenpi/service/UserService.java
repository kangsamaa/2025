package com.kang.nenpi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kang.nenpi.entity.User;
import com.kang.nenpi.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllusers(){
        List<User> users = userRepository.findAll();
        // 데이터 확인용 로그 출력
        System.out.println("Users: " + users);
        return users;
    }
}
