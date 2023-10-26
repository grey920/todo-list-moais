package com.sample.todo.user.service;

import com.sample.todo.user.domain.User;
import com.sample.todo.user.repository.UserMapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Resource(name = "userMapper")
    private final UserMapper userMapper;

    @Override
    public List< User > listAllUser() {
        return userMapper.listAllUser();
    }
}
