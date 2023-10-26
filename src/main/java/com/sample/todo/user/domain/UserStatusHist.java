package com.sample.todo.user.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserStatusHist {

    private String userStatusHistOid;           // 회원 상태이력 OID
    private String userOid;                     // 사용자 OID
    private String status;                      // 상태
    private LocalDateTime inputDateTime;        // 입력일시

}
