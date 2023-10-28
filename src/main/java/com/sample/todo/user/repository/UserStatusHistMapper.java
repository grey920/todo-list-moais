package com.sample.todo.user.repository;

import com.sample.todo.user.domain.UserStatusHist;

public interface UserStatusHistMapper {

    /**
     * 회원 상태이력 등록
     * @param userStatusHist
     * @return
     */
    public int insertUserStatusHist( UserStatusHist userStatusHist );

}
