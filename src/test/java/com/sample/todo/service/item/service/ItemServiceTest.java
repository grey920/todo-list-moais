package com.sample.todo.service.item.service;

import com.sample.todo.service.item.domain.Item;
import com.sample.todo.service.item.domain.ItemCnd;
import com.sample.todo.service.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
@SpringBootTest
@ExtendWith( SpringExtension.class)
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private final String userOid = "5802e2bc-4212-4c07-8a16-91473ea83492"; // grey
    private final String grey_item_Oid1 = "05689f07-584c-42b6-918c-dd2b739e3d18"; // item1
    private final String grey_item_Oid2 = "44f68109-71cc-4914-9044-02e8657d51c5"; // item2

    @Test
    public void init() {
        log.info("init");
    }

    @Test
    @DisplayName( "아이템 등록" )
    public void createItem() {
        /* Given */
        Item item = new Item();
        item.setUserOid( userOid );
        item.setContents( "등록테스트22222" );
        item.setInputDateTime( LocalDateTime.now() );

        /* When */
        Item createdItem = itemService.createItem( item );
        log.info( "createdItem : {}", createdItem );

        /* Then */
        assertThat( createdItem.getItemOid() ).isNotNull();
    }

    @Test
    @DisplayName( "아이템 등록 > 내용 안넣은 경우" )
    public void createItem2() {
        /* Given */
        Item item = new Item();
        item.setUserOid( userOid );

        /* When */
        Item createdItem = itemService.createItem( item );
        log.info( "createdItem : {}", createdItem );

        /* Then */
        assertThat( createdItem.getItemOid() ).isNotNull();
        assertThat( createdItem.getContents() ).isNull();

    }

    @Test
    @DisplayName( "아이템 전체 조회" )
    public void listAllItem() {

        /* Given */
        ItemCnd cnd = new ItemCnd();
        cnd.setUserOid( userOid ); // 회원 oid


        /* When */
        List< Item > itemList = itemService.listAllItem( cnd );
        itemList.stream().forEach( item -> log.info( "item : {}", item ) );

        /* Then */
        assertThat( itemList ).isNotNull();
        assertThat( itemList.get( 0 ).getUserOid() ).isEqualTo( userOid );

    }

    @Test
    @DisplayName( "가장 최근의 TODO 아이템  조회" )
    public void getLastItem() {

        /* When */
        Item lastItem = itemService.getLastItem();
        log.info( "lastItem : {}", lastItem );

        /* Then */
        assertThat( lastItem.getUserOid() ).isEqualTo( userOid );
        log.info( "last inputDateTime ::: {} ", lastItem.getInputDateTime() );

    }

    @Test
    @DisplayName( "아이템 상태 수정 > T(예정) -> I(진행중) 으로 변경" )
    public void updateItem1() {

        /* Given */
        Item item = new Item();
        item.setUserOid( userOid ); // 필수
        item.setItemOid( grey_item_Oid1 ); // 필수
        item.setStatus( Item.ITEM_STATUS_ING ); // 상태

        /* When */
        Item updatedStatusItem = itemService.updateItemStatus( item );
        log.info( "updatedStatusItem : {}", updatedStatusItem );

        /* Then */
        assertThat( updatedStatusItem.getStatus() ).isEqualTo( Item.ITEM_STATUS_ING );
        log.info( "updatedStatusItem.getStatus ::: {} ", updatedStatusItem.getStatus() );

        // 원복
        item.setStatus( Item.ITEM_STATUS_TODO );
        itemService.updateItemStatus( item );
    }

    @Test
    @DisplayName( "아이템 수정 > 내용 수정" )
    public void updateItem2() {

        /* Given */
        Item item = new Item();
        item.setUserOid( userOid ); // 필수
        item.setItemOid( grey_item_Oid2 ); // 필수
        item.setContents( "내용 수정하기222222" );

        /* When */
        Item updatedStatusItem = itemService.updateItem( item );
        log.info( "updatedStatusItem : {}", updatedStatusItem );

        /* Then */
        assertThat( updatedStatusItem.getContents() ).isEqualTo( item.getContents() );

    }

    @Test
    @DisplayName( "아이템 삭제" )
    public void updateItem3() {

        /* When */
        Item deleteItem = itemService.deleteItem( userOid, grey_item_Oid2 );
        log.info( "deleteItem : {}", deleteItem );

        /* Then */
        assertThat( deleteItem.getDeleteYn() ).isEqualTo( "Y" );
        assertThat( deleteItem.getDelDateTime() ).isNotNull();

    }


}