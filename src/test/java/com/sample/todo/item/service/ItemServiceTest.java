package com.sample.todo.item.service;

import com.sample.todo.item.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@ExtendWith( SpringExtension.class)
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private String userOid = "5802e2bc-4212-4c07-8a16-91473ea83492"; // grey

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
        item.setContents( "첫 등록" );
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
}