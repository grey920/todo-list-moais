package com.sample.todo.item.domain;

import com.sample.todo.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Item {

    public static final String ITEM_STATUS_TODO = "T";
    public static final String ITEM_STATUS_ING = "I";
    public static final String ITEM_STATUS_DONE = "D";

    private String itemOid;                     // 아이템 OID
    private String userOid;                     // 사용자 OID
    private String contents;                     // 내용
    private String status = ITEM_STATUS_TODO;   // 상태
    private LocalDateTime inputDateTime;        // 입력일시
    private LocalDateTime modDateTime;          // 수정일시
    private LocalDateTime delDateTime;          // 삭제일시

    /**
     * 자신의 객체가 비워져 있는지 체크.
     *
     * @param info
     * @return
     */
    public static boolean isEmpty( Item info ) {

        return info == null || info.getItemOid() == null || info.getItemOid().isBlank();
    }


    /**
     * 자신의 객체가 비워져 있는지 체크.
     *
     * @param info
     * @return
     */
    public static boolean isNotEmpty( Item info ) {

        return !isEmpty(info);
    }



    private static Item EMPTY = new Item.NullItem();

    /**
     * null객체 반환.
     *
     * @return
     */
    public static Item empty() {
        return EMPTY;
    }


    /**
     * 유저 정보가 없을 때 사용하는 Null 객체.
     */
    public static class NullItem extends Item {

        @Override
        public String toString() {
            return "Null 아이템입니다.";
        }
    }

}
