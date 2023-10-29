package com.sample.todo.service.item.service;

import com.sample.todo.service.item.domain.Item;
import com.sample.todo.service.item.domain.ItemCnd;
import com.sample.todo.service.item.repository.ItemMapper;
import com.sample.todo.web.util.SessionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
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

        if ( item.getUserOid() == null || item.getUserOid().isBlank() ) {
            item.setUserOid( SessionUtil.getUser().getUserOid() );
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
    public List< Item > listAllItem( ItemCnd cnd ) {

        if ( cnd.getUserOid() == null || cnd.getUserOid().isBlank() ) {
            cnd.setUserOid( SessionUtil.getUser().getUserOid() );
        }

        return itemMapper.listAllItem( cnd );
    }

    /**
     * 회원의 가장 최근 할 일 아이템 정보 조회
     * todo. 상태 상관없이 가장 최근인지, 아님 할 일(T) 상태의 아이템 중 최근인지 확인 필요
     * @return
     */
    @Override
    public Item getLastItem() {

        ItemCnd cnd = new ItemCnd();
        cnd.setUserOid( SessionUtil.getUser().getUserOid() );
        cnd.setStatus( Item.ITEM_STATUS_TODO ); // 할 일 상태인 아이템에서만 조회

        return itemMapper.getLastItemByUserOid( cnd ) == null ? Item.empty() : itemMapper.getLastItemByUserOid( cnd );
    }

    /**
     * 할 일 아이템 상태 수정
     * @param item
     * @return
     */
    @Override
    public Item updateItemStatus( Item item ) {

        // 상태값 validation
        if ( item.getStatus() == null || item.getStatus().isBlank() ) {
            log.error( "status is null or blank" );
            return Item.empty();
        }

        return updateItem( item );

    }

    /**
     * 할 일 아이템 정보 수정
     *
     * @param item
     * @return
     */
    @Override
    public Item updateItem( Item item ) {
        // validation : userOid, itemOid null 객체 반환
        if ( item.getItemOid() == null || item.getItemOid().isBlank() ) {
            log.error( "itemOid is null or blank" );
            return Item.empty();
        }

        if ( item.getUserOid() == null || item.getUserOid().isBlank() ) {
            item.setUserOid( SessionUtil.getUser().getUserOid() );
        }

        // 수정일시 추가
        if ( item.getModDateTime() == null ) {
            item.setModDateTime( LocalDateTime.now() );
        }

        return itemMapper.updateItem( item ) > 0 ? item : Item.empty();
    }

    /**
     * 할 일 아이템 정보 삭제
     * fixme. session에서 userOid 가져오는 방식으로 변경 필요
     * @param item
     * @return
     */
    @Override
    public Item deleteItem( String itemOid ) {
        Item item = new Item();
        item.setItemOid( itemOid );
        item.setUserOid( SessionUtil.getUser().getUserOid() );

        // delDateTime 추가
        item.setDeleteYn( "Y" );
        item.setDelDateTime( LocalDateTime.now() );

        return updateItem( item );
    }
}
