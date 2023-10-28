package com.sample.todo.item.service;

import com.sample.todo.item.domain.Item;
import com.sample.todo.item.repository.ItemMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    /**
     * 할 일 아이템 정보 단건 등록
     *
     * @param item 등록할 아이템
     * @return
     */
    @Override
    public Item createItem( Item item ) {

        // validation : user정보 없으면 null 객체 반환
        if ( item.getUserOid() == null || item.getUserOid().isBlank() ) {
            log.error( "userOid is null or blank" );
            return Item.empty();
        }

        // oid 생성
        if ( item.getItemOid() == null || item.getItemOid().isBlank() ) {
            item.setItemOid( java.util.UUID.randomUUID().toString() );
        }

        // 등록일시
        item.setInputDateTime( LocalDateTime.now() );

        return itemMapper.insertItem( item ) > 0 ? item : Item.empty();
    }

    /**
     * 회원의 모든 할 일 아이템 전체 목록 조회
     *
     * @param userOid 회원 oid
     * @return
     */
    @Override
    public List< Item > listAllItem( String userOid ) {
        return null;
    }

    /**
     * 회원의 가장 최근 할 일 아이템 정보 조회
     *
     * @param userOid
     * @return
     */
    @Override
    public Item getLastItem( String userOid ) {
        return null;
    }

    /**
     * 할 일 아이템 상태 수정
     *
     * @param item
     * @return
     */
    @Override
    public Item updateItemStatus( Item item ) {
        return null;
    }

    /**
     * 할 일 아이템 정보 수정
     *
     * @param item
     * @return
     */
    @Override
    public Item updateItem( Item item ) {
        return null;
    }

    /**
     * 할 일 아이템 정보 삭제
     *
     * @param item
     * @return
     */
    @Override
    public Item deleteItem( Item item ) {
        return null;
    }
}
