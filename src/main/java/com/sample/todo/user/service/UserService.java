package com.sample.todo.user.service;

import com.sample.todo.user.domain.User;
import com.sample.todo.user.domain.UserCnd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 회원 관련 서비스
 */
public interface UserService {

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
     * 회원가입시 아이디 중복 검사
     * @param id
     * @return
     */
    public boolean hasDuplicatedId( String id );

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
    public User join( User user );

    /**
     * 회원 로그인
     * 성공시 success: true, 유저 정보를 반환.
     * 실패시 success: false, 에러메시지를 반환
     * @param user
     * @return
     */
    public Map<String, Object> login( User user );

    /**
     * 회원 정보 수정
     * @param user
     * @return 변경된 user 정보. 실패시 null 유저 객체
     */
    public User updateUser( User user );

    /**
     * 회원 상태 수정
     * @param user
     * @return 변경된 user 정보. 실패시 null 유저 객체
     */
    public User updateUserStatus( User user );


}
