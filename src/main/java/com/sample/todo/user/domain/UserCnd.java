package com.sample.todo.user.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 회원 정보 검색용 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserCnd {

    private String userOid;                     // 사용자 OID

    private String id;                          // 사용자 ID

    private String password;                    // 비밀번호

    private String email;                       // 이메일

    private String nickName;                    // 닉네임

    private String status;

}
