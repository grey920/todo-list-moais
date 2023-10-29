package com.sample.todo.item.repository;

import com.sample.todo.item.domain.Item;
import com.sample.todo.item.domain.ItemCnd;

import java.util.List;

public interface ItemMapper {

    /**
     * 아이템 정보 등록
     * @param item
     * @return
     */
    public int insertItem( Item item );

    /**
     * 회원의 모든 할 일 아이템 전체 목록 조회
     * @param cnd 검색 조건. userOid 필수
     * @return
     */
    List<Item> listAllItem( ItemCnd cnd );

    /**
     * 회원의 가장 최근 할 일 아이템 정보 조회
     * @param cnd
     * @return
     */
    public Item getLastItemByUserOid( ItemCnd cnd );

    /**
     * 아이템 정보 수정. 필수값 item_oid, user_oid
     * ( contents, status, mod_date_time, del_date_time )
     *
     * @param item
     * @return
     */
    public int updateItem( Item item );
}
