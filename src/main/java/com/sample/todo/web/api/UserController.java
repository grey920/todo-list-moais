package com.sample.todo.web.api;

import com.sample.todo.service.user.domain.User;
import com.sample.todo.service.user.domain.UserCnd;
import com.sample.todo.service.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * 사용자 컨트롤러.
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/user" )
public class UserController {
    // 세션 조회용 리스트
    public static Hashtable sessionList = new Hashtable();

    private final UserService userService;

    /**
     * 회원가입
     * @param userInfo
     * @return
     */
    @PostMapping( "/join" )
    public ResponseEntity<?> join( @RequestBody User userInfo ) {
        if ( userInfo == null ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "" );
        }

        // 가입
        User user = userService.join( userInfo );
        log.info( "회원가입 = {}", user );

        if ( User.isEmpty( user ) ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "회원 가입에 실패했습니다." );
        }

        return ResponseEntity.ok( user );
    }



    /**
     * 로그인
     * 성공시 success: true, 유저 정보를 반환.( 세션 저장 )
     * @param userInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping( "/login" )
    public ResponseEntity<?> login( @RequestBody User userInfo, HttpServletRequest request, HttpServletResponse response ) {

        Map< String, Object > result = userService.login( userInfo );

        // 로그인 실패시
        if ( result.get( "success" ) == null || ! ( Boolean ) result.get( "success" ) ) {
            log.error( "로그인 실패 = {}", result.get( "message" ) );
            return ResponseEntity.ok( result );
        }

        // 로그인 성공시
        /* session */
        // 로그인 성공시 세션에 사용자 정보 저장
        HttpSession session = request.getSession( true );
        session.setAttribute( "user", result.get( "user" ) );
        session.setMaxInactiveInterval( 60 * 60 ); // 1시간

        // sessionList에 세션 저장
        sessionList.put( session.getId(), session );

        log.info( "로그인 성공 = {}", result.get( "user" ) );

        return ResponseEntity.ok( result );
    }


    /**
     * 로그아웃
     * @param request
     * @param response
     * @return
     */
    @PostMapping( "/logout" )
    public String logout( HttpServletRequest request, HttpServletResponse response ) {

        HttpSession session = request.getSession( false );

        if ( session != null ) {

            User userInfo = ( User ) session.getAttribute( "user" );
            log.debug( "로그아웃 = {}", userInfo == null ? "userInfo가 null입니다" : userInfo.getId() );

            sessionList.remove( session.getId() );
            session.invalidate();
        }

        return "redirect:/";
    }


    /**
     * 세션 리스트 조회
     * @return
     */
    @GetMapping( "/session-list" )
    @ResponseBody
    public Map< String, String > sessionList() {

        Enumeration elements = sessionList.elements();
        Map< String, String > lists = new HashMap<>();

        while ( elements.hasMoreElements() ) {
            HttpSession session = ( HttpSession ) elements.nextElement();
            User user = ( User ) session.getAttribute( "user" );
            lists.put( session.getId(), user.getUserOid() );
        }

        log.info( "sessionList = {}", lists );
        return lists;
    }


    /**
     * 회원정보 수정
     * @param userInfo
     * @return
     */
    @PostMapping( "/update" )
    public ResponseEntity<?> update( @RequestBody User userInfo ) {

        User user = userService.updateUser( userInfo );

        log.info( "회원정보 수정 = {}", user );

        return ResponseEntity.ok( user );
    }

    /**
     * 회원 상태 수정 ( 탈퇴 )
     * todo. 추후 휴면 상태가 생긴다면 휴면 상태 수정도 포함됨
     * @param userInfo
     * @return
     */
    @PostMapping( "/update/status" )
    public ResponseEntity<?> updateUserStatus( @RequestBody User userInfo ) {

        User user = userService.updateUserStatus( userInfo );

        log.info( "회원정보 수정 = {}", user );

        return ResponseEntity.ok( user );
    }

    /**
     * 회원가입 > 아이디 중복검사
     * @param userOid
     * @return
     */
    @GetMapping( "/duplicate-id/{id}" )
    public ResponseEntity< ? >  hasDuplicatedId( @PathVariable("id" ) String id ) {
        log.info( "아이디 중복검사 = {}", id );

        return ResponseEntity.ok( userService.hasDuplicatedId( id ) );
    }

    /**
     * 전체 회원 조회
     */
    @GetMapping( "/list" )
    public ResponseEntity< ? > listAllUser( @RequestBody UserCnd userCnd ) {

        log.info( "전체 회원 조회 userCnd = {}", userCnd );

        return ok( userService.listAllUser( userCnd ) );
    }

}
