package com.sample.todo.service.item.service;

import com.sample.todo.service.item.domain.Item;
import com.sample.todo.service.item.domain.ItemCnd;

import java.util.List;

/**
 * 할 일 아이템 관련 서비스
 */
public interface ItemService {

    /**
     * 할 일 아이템 정보 단건 등록
     * @param item 등록할 아이템
     * @return
     */
    public Item createItem( Item item );

    /**
     * 회원의 모든 할 일 아이템 전체 목록 조회
     * @param cnd 검색 조건.
     * @return
     */
    public List<Item> listAllItem( ItemCnd cnd );

    /**
     * 회원의 가장 최근 할 일 아이템 정보 조회
     * @return
     */
    public Item getLastItem();

    /**
     * 할 일 아이템 상태 수정
     * @param item
     * @return
     */
    public Item updateItemStatus( Item item );

    /**
     * 할 일 아이템 정보 수정
     * @param item
     * @return
     */
    public Item updateItem( Item item );

    /**
     * 할 일 아이템 정보 삭제 ( 논리 삭제, deleteYn = 'Y' )
     * @return
     */
    public Item deleteItem( String itemOid );


}
