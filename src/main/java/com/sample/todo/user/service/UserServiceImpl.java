package com.sample.todo.user.service;

import com.sample.todo.user.domain.User;
import com.sample.todo.user.domain.UserCnd;
import com.sample.todo.user.domain.UserStatusHist;
import com.sample.todo.user.repository.UserMapper;
import com.sample.todo.user.repository.UserStatusHistMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserStatusHistMapper userStatusHistMapper;

    /**
     * 회원 단건 조회
     *
     * @param cnd 회원 검색 조건
     * @return 회원 정보
     */
    @Override
    public User getUser( UserCnd cnd ) {

        // 유저가 없으면 NullUser 객체 반환 ( NPE 방지 )
        return userMapper.getUser( cnd ) != null ? userMapper.getUser( cnd ) : User.empty();
    }

    /**
     * 회원 OID로 회원 정보 단건 조회
     *
     * @param userOid 회원 OID
     * @return 회원 정보. 없으면 null 유저 객체 반환
     */
    @Override
    public User getUserByOid( String userOid ) {

        // userOid null 체크
        if ( userOid == null || userOid.isBlank() ) {
            throw new IllegalArgumentException( "userOid is null or empty" );
        }

        UserCnd cnd = new UserCnd();
        cnd.setUserOid( userOid );

        User user = getUser( cnd );
        return user.getUserOid() != null ? user : User.empty();
    }

    /**
     * 회원가입시 아이디 중복 검사
     *
     * @param id
     * @return 중복 여부 boolean
     */
    @Override
    public boolean hasDuplicatedId( String id ) {

        // DB에 동일한 id가 있는지 확인
        UserCnd userCnd = new UserCnd();
        userCnd.setId( id );
        userCnd.setStatus( User.STATUS_JOIN );

        User dbUser = getUser( userCnd );

        return dbUser != null && dbUser.getId().equals( id );
    }

    /**
     * 회원 리스트 조회
     *
     * @param cnd 회원 검색 조건
     * @return 회원 리스트
     */
    @Override
    public List< User > listAllUser( UserCnd cnd ) {
        return userMapper.listAllUser( cnd );
    }

    /**
     * 회원 정보 등록 ( 가입 )
     *
     * @param user
     * @return
     */
    @Override
    public User join( User user ) {

        // userOid 생성
        if ( user.getUserOid() == null || user.getUserOid().isBlank() ){
            user.setUserOid( UUID.randomUUID().toString() );
        }

        // validation
        if ( user.getId() == null || user.getId().isBlank() ) {
            throw new IllegalArgumentException( "회원 id가 비어있습니다." );
        }
        if ( user.getPassword() == null || user.getPassword().isBlank() ) {
            throw new IllegalArgumentException( "회원 비밀번호가 비어있습니다." );
        }
        if ( user.getNickName() == null || user.getNickName().isBlank() ) {
            throw new IllegalArgumentException( "회원 닉네임이 비어있습니다." );
        }

        // status가 비어있으면 가입으로 설정
        if ( user.getStatus() == null || user.getStatus().isBlank() ) {
            user.setStatus( User.STATUS_JOIN );
        }

        // TODO. 비밀번호 암호화


        /* 회원 등록*/
        User newUser = userMapper.insertUser( user ) > 0 ? user : User.empty();

        /* 회원 상태 이력 등록 */
        insertUserStatusHist( newUser );

        return newUser;
    }

    /**
     * 회원 로그인
     * 성공시 success: true, 유저 정보를 반환.
     * 실패시 success: false, 에러메시지를 반환
     * @param user
     * @return
     */
    @Override
    public Map< String, Object > login( User user ) {

        HashMap<String, Object > resultMap = new HashMap<>();
        resultMap.put( "success", false );
        resultMap.put( "message", "" );


        // validation : 입력값 (id, password) 확인
       if ( ! validateLoginInput( user, resultMap ) ) {
           return resultMap;
       };


        //  DB에 유효한 회원 정보 존재하는지 확인 ( id, password 일치하고 가입상태인 경우 )
        UserCnd cnd = new UserCnd();
        cnd.setId( user.getId() );
        cnd.setPassword( user.getPassword() );
        cnd.setStatus( User.STATUS_JOIN );

        User dbUser = getUser( cnd );

        // validation : 회원 정보가 없으면 에러 메시지 담기
        if ( ! isValidUser( dbUser, resultMap ) ) {
            return resultMap;
        };

        resultMap.put( "success", true );
        resultMap.put( "message", dbUser );

        return resultMap;
    }



    /**
     * 회원 정보 수정
     *
     * @param user
     * @return 변경된 user 정보. 실패시 null 유저 객체
     */
    @Override
    public User updateUser( User user ) {

        // oid가 없으면 null객체 반환
        if ( user.getUserOid() == null || user.getUserOid().isBlank() ) {
            return User.empty();
        }

        return userMapper.updateUser( user ) > 0 ? user : User.empty();
    }

    /**
     * 회원 상태 수정. ( 가입, 탈퇴 )
     *
     * @param user
     * @return 변경된 user 정보. 실패시 null 유저 객체
     */
    @Override
    public User updateUserStatus( User user ) {

        // oid, status가 없으면 null객체 반환
        if ( user.getUserOid() == null || user.getUserOid().isBlank()
                || user.getStatus() == null || user.getStatus().isBlank() ) {
            return User.empty();
        }

        // user의 상태만 변경
        User updateUser = new User();
        updateUser.setUserOid( user.getUserOid() );
        updateUser.setStatus( user.getStatus() );

        // update 수행
        User result = updateUser( updateUser );

        // 변경 이력 등록
        insertUserStatusHist( user );

        return result;
    }


    /**
     * 로그인 입력값 검증
     * @param user
     * @return 유효하면 true, 아이디 또는 비밀번호가 비어있으면 false
     */
    private boolean validateLoginInput( User user, HashMap<String, Object > resultMap ) {

        if ( user.getId() == null || user.getId().isBlank() || user.getPassword() == null || user.getPassword().isBlank()) {
            resultMap.put( "message", "아이디 또는 비밀번호를 입력해주세요." );
            return false;
        }

        return true;

    }


    /**
     * db에 유효한 회원 정보가 있는지 확인
     * @param result
     * @return 유효하면 true, 유효하지 않으면 false
     */
    private boolean isValidUser( User dbUser, HashMap<String, Object > resultMap ) {

        // 회원 정보가 없으면 에러 메시지 담기
        if ( dbUser.getUserOid() == null || dbUser.getUserOid().isBlank() ) {
            resultMap.put( "message",  "회원 정보가 없습니다." );
            return false;
        }

        // 회원 상태가 가입이 아니면 에러 메시지 담기
        if ( ! User.STATUS_JOIN.equals( dbUser.getStatus() ) ) {
            resultMap.put( "message", "회원 상태가 가입이 아닙니다." );
            return false;
        }

        return true;
    }

    private void insertUserStatusHist( User user ) {

        UserStatusHist statusHist = new UserStatusHist();
        statusHist.setUserStatusHistOid( UUID.randomUUID().toString() ); // oid 생성
        statusHist.setUserOid( user.getUserOid() );
        statusHist.setStatus( user.getStatus() );

        int result = userStatusHistMapper.insertUserStatusHist( statusHist );

        if ( result <= 0 ) {
            log.error( "insertUserStatusHist ::: 회원 '{}' 이력 등록 실패 - {}", user.getStatus(), user.getUserOid() );
        }
    }
}
