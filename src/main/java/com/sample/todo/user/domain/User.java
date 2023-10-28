package com.sample.todo.user.domain;

import lombok.*;
import org.springframework.util.StringUtils;

/**
 * 회원 정보 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    public static final String STATUS_JOIN = "J";           // 회원 상태 > 가입
    public static final String STATUS_WITHDRAW = "W";       // 회원 상태 > 탈퇴

    private String userOid;                                 // 사용자 OID
    private String id;                                      // 사용자 ID
    private String password;                                // 비밀번호
    private String email;                                   // 이메일
    private String nickName;                                // 닉네임
    private String status = STATUS_JOIN;                    // 회원 상태. 기본값 J ( 가입 )

    /**
     * 자신의 객체가 비워져 있는지 체크.
     *
     * @param info
     * @return
     */
    public static boolean isEmpty( User info ) {

        return info == null || info.getUserOid().isBlank();
    }


    /**
     * 자신의 객체가 비워져 있는지 체크.
     *
     * @param info
     * @return
     */
    public static boolean isNotEmpty( User info ) {

        return !isEmpty(info);
    }



    private static User EMPTY = new NullUser();

    /**
     * null객체 반환.
     *
     * @return
     */
    public static User empty() {
        return EMPTY;
    }


    /**
     * 유저 정보가 없을 때 사용하는 Null 객체.
     */
    public static class NullUser extends User {

        @Override
        public String toString() {
            return "Null 사용자입니다.";
        }
    }

}
