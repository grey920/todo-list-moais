package com.sample.todo.user.service;

import com.sample.todo.user.domain.User;

import java.util.List;


public interface UserService {

    /**
     * 회원 정보 전체 조회
     * @return
     */
    public List< User > listAllUser();
}
