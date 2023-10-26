package com.sample.todo.user.repository;

import com.sample.todo.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
public interface UserMapper {

    /**
     * 회원 OID로 회원 정보 조회
     * @param userOid
     * @return
     */
    public User getUserByOid( String userOid );

    /**
     * 회원 정보 전체 조회
     * @return
     */
    public List< User > listAllUser();

    /**
     * 회원 정보 등록 ( 가입 )
     * @param user
     * @return
     */
    public int insertUser( User user );
}
