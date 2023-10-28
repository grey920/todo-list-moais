package com.sample.todo.user.service;

import com.sample.todo.user.domain.User;
import com.sample.todo.user.domain.UserCnd;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static com.sample.todo.user.domain.User.STATUS_WITHDRAW;

@Slf4j
@SpringBootTest
@ExtendWith( SpringExtension.class)
class UserServiceTest {

    @Autowired private UserService userService;

    @Test
    public void init() {
        log.info("init");
    }

    @Test
    @DisplayName( "회원 단건 조회" )
    public void getUser1(){

        /* Given */
        UserCnd userCnd = new UserCnd();
        userCnd.setId( "grey" );
        userCnd.setStatus( User.STATUS_JOIN );

        /* When */
        User user = userService.getUser( userCnd );

        /* Then */
        Assertions.assertThat( user ).isNotNull();
        Assertions.assertThat( user.getId() ).isEqualTo( userCnd.getId() );
        Assertions.assertThat( user.getStatus() ).isEqualTo( userCnd.getStatus() );

        System.out.println( "user = " + user );

    }

    @Test
    @DisplayName( "회원 단건 조회 > 없는 회원 조회시" )
    public void getUser2(){

        /* Given */
        UserCnd userCnd = new UserCnd();
        userCnd.setId( "grey92" );
        userCnd.setStatus( User.STATUS_JOIN );

        /* When */
        User user = userService.getUser( userCnd );

        /* Then */
        Assertions.assertThat( user.getUserOid() ).isNull();
        System.out.println( "user = " + user ); // Null 사용자입니다. 출력

    }


    @Test
    @DisplayName( "회원가입시 아이디 중복검사" )
    public void hasDuplicatedId(){

        /* Given */
        String id = "grey";

        /* when */
        boolean result = userService.hasDuplicatedId( id );

        /* then */
        Assertions.assertThat( result ).isTrue();

    }


    @Test
    @DisplayName( "회원 전체 조회" )
    public void listAllUser() {

        /* Given */
        UserCnd userCnd = new UserCnd();
        // 회원 상태가 가입
        userCnd.setStatus( User.STATUS_JOIN );

        /* When */
        List< User > userList = userService.listAllUser( userCnd );

        /* Then */
        Assertions.assertThat( userList ).isNotNull();
        Assertions.assertThat( userList.get( 0 ).getStatus() ).isEqualTo( User.STATUS_JOIN );

        userList.stream().forEach( System.out::println );
    }

    @Test
    @DisplayName( "회원 가입" )
    public void insertUser() {

        /* Given */
        User user = new User();
        user.setId( "dawa" );
        user.setPassword( "1234qwer" );
        user.setEmail( "bbb@gmail.com" );
        user.setNickName( "다와쨩" );

        /* When */
        User result = userService.join( user );

        /* Then */
        Assertions.assertThat( result.getUserOid() ) .isNotNull();
        Assertions.assertThat( result.getId() ).isEqualTo( user.getId() );


    }

    @Test
    @DisplayName( "로그인 성공" )
    public void login() {

        /* Given */
        User user = new User();
        user.setId( "dawa" );
        user.setPassword( "1234qwer" );

        /* When */
        Map< String, Object > result = userService.login( user );

        System.out.println( "result = " + result );

        /* Then */
        Assertions.assertThat( ( Boolean ) result.get( "success" ) ).isEqualTo( true );
        Assertions.assertThat( result.get( "message") ).isInstanceOf( User.class );

    }

    @Test
    @DisplayName( "로그인 실패 > 비밀번호 미입력" )
    public void login2() {

        /* Given */
        User user = new User();
        user.setId( "grey" );
        user.setPassword( null );

        /* When */
        Map< String, Object > result = userService.login( user );

        System.out.println( "result = " + result );

        /* Then */
        Assertions.assertThat( ( Boolean ) result.get( "success" ) ).isEqualTo( false );
        Assertions.assertThat( result.get( "message") ).isEqualTo( "아이디 또는 비밀번호를 입력해주세요." );

    }

    @Test
    @DisplayName( "로그인 실패 > 존재하지 않는 회원" )
    public void login3() {

        /* Given */
        User user = new User();
        user.setId( "test" );
        user.setPassword( "qwer1234" );

        /* When */
        Map< String, Object > result = userService.login( user );

        System.out.println( "result = " + result );

        /* Then */
        Assertions.assertThat( ( Boolean ) result.get( "success" ) ).isEqualTo( false );
        Assertions.assertThat( result.get( "message") ).isEqualTo( "회원 정보가 없습니다." );

    }

    @Test
    @DisplayName( "로그인 실패 > 비밀번호 오류" )
    public void login4() {

        /* Given */
        User user = new User();
        user.setId( "grey" );
        user.setPassword( "1234" );

        /* When */
        Map< String, Object > result = userService.login( user );

        System.out.println( "result = " + result );

        /* Then */
        Assertions.assertThat( ( Boolean ) result.get( "success" ) ).isEqualTo( false );
        Assertions.assertThat( result.get( "message") ).isEqualTo( "비밀번호가 일치하지 않습니다." );

    }

    @Test
    @DisplayName( "회원 정보 수정 > 닉네임 수정" )
    public void updateUser(){

        /* Given */
        // 1. grey라는 아이디를 가진 회원 조회
        UserCnd cnd = new UserCnd();
        cnd.setId( "grey" );
        User userGrey = userService.getUser( cnd );
        Assertions.assertThat( userGrey ).isNotNull();

        // 2. 닉네임 변경
        userGrey.setNickName( "grey92" );

        /* When */
        User result = userService.updateUser( userGrey );

        /* Then */
        Assertions.assertThat( result ).isNotNull();
        Assertions.assertThat( result.getNickName() ).isEqualTo( userGrey.getNickName() );

    }

    @Test
    @DisplayName( "회원 정보 수정 > 회원 상태 수정" )
    public void updateUserStatus(){

        /* Given */
        UserCnd cnd = new UserCnd();
        cnd.setId( "grey" );

        // 회원 grey 조회
        User userGrey = userService.getUser( cnd );
        Assertions.assertThat( userGrey ).isNotNull();

        // 탈퇴 상태로 설정
        userGrey.setStatus( STATUS_WITHDRAW );

        /* When */
        User result = userService.updateUserStatus( userGrey );

        /* Then */
        Assertions.assertThat( result ).isNotNull();
        Assertions.assertThat( result.getStatus()).isEqualTo( STATUS_WITHDRAW );

    }



}