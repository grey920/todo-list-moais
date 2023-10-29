package com.sample.todo.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemCnd {

    private String searchText;                  // 검색어 ( 현재는 contents 기준 검색 가능 )
    private LocalDate searchFromDate;           // 검색일 ( 현재는 등록일 기준 검색 가능 )
    private LocalDate searchToDate;             // 검색일 ( 현재는 등록일 기준 검색 가능 )

    private String itemOid;                     // 아이템 OID
    private String userOid;                     // 사용자 OID
    private String contents;                    // 내용
    private String status;                      // 상태
    private String deleteYn = "N";              // 삭제여부 (default N)

    private LocalDateTime inputDateTime;        // 입력일시
    private LocalDateTime modDateTime;          // 수정일시
    private LocalDateTime delDateTime;          // 삭제일시

    /**
     * 자신의 객체가 비워져 있는지 체크.
     *
     * @param info
     * @return
     */
    public static boolean isEmpty( ItemCnd info ) {

        return info == null || info.getItemOid() == null || info.getItemOid().isBlank();
    }


    /**
     * 자신의 객체가 비워져 있는지 체크.
     *
     * @param info
     * @return
     */
    public static boolean isNotEmpty( ItemCnd info ) {

        return !isEmpty(info);
    }



    private static ItemCnd EMPTY = new ItemCnd.NullItem();

    /**
     * null객체 반환.
     *
     * @return
     */
    public static ItemCnd empty() {
        return EMPTY;
    }


    /**
     * 유저 정보가 없을 때 사용하는 Null 객체.
     */
    public static class NullItem extends ItemCnd {

        @Override
        public String toString() {
            return "Null 아이템입니다.";
        }
    }

}
