package com.sample.todo.item.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Item {

    private String itemOid;                     // 아이템 OID
    private String userOid;                     // 사용자 OID
    private String content;                     // 내용
    private String status;                      // 상태
    private LocalDateTime inputDateTime;        // 입력일시
    private LocalDateTime modDateTime;          // 수정일시
    private LocalDateTime delDateTime;          // 삭제일시
}
