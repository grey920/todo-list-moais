package com.sample.todo.user.repository;

import com.sample.todo.user.domain.User;
import com.sample.todo.user.domain.UserCnd;

import java.util.List;

public interface UserMapper {

    /**
     * 회원 단건 조회
     *
     * @param cnd 회원 검색 조건
     * @return 회원 정보
     */
    public User getUser( UserCnd cnd );

    /**
     * 회원 OID로 회원 정보 단건 조회
     *
     * @param userOid 회원 OID
     * @return 회원 정보
     */
    public User getUserByOid( String userOid );

    /**
     * 회원 리스트 조회
     * @param cnd 회원 검색 조건
     * @return 회원 리스트
     */
    public List< User > listAllUser( UserCnd cnd );

    /**
     * 회원 정보 등록 ( 가입 )
     * @param user
     * @return
     */
    public int insertUser( User user );


    /**
     * 회원 정보 수정
     * @param user
     * @return
     */
    public int updateUser( User user );
}
