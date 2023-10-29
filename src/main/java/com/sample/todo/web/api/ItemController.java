package com.sample.todo.web.api;

import com.sample.todo.service.item.domain.Item;
import com.sample.todo.service.item.domain.ItemCnd;
import com.sample.todo.service.item.service.ItemService;
import com.sample.todo.service.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/item" )
public class ItemController {

    private final ItemService itemService;

    /**
     * 아이템 추가 ( 할 일 추가 )
     * @param item
     * @return
     */
    @PostMapping( "/add" )
    public ResponseEntity<?> addItem( @RequestBody Item item ) {

        return ResponseEntity.ok( itemService.createItem( item ) );
    }

    /**
     * 회원의 모든 할 일 아이템 전체 목록 조회
     * @param cnd
     * @return
     */
    @GetMapping( "/listAll" )
    public ResponseEntity<?> listAllItem( @RequestBody ItemCnd cnd ) {

        return ResponseEntity.ok( itemService.listAllItem( cnd ) );
    }


    /**
     * 회원의 가장 최근 할 일 아이템 정보 조회
     * @return
     */
    @GetMapping( "/last" )
    public ResponseEntity<?> getLastItem() {

        return ResponseEntity.ok( itemService.getLastItem() );
    }

    /**
     * 아이템 상태 수정
     * 필수값. itemOid, status
     * @param info
     * @return
     */
    @PutMapping( "/update/status" )
    public ResponseEntity<?> updateItemStatus( @RequestBody Item info ) {

        Item item = itemService.updateItemStatus( info );

        log.info( "아이템 상태 수정 = {}", item );

        return ResponseEntity.ok( item );
    }

    /**
     * 아이템 수정
     * 필수값. itemOid, status
     * @param info
     * @return
     */
    @PutMapping( "/update" )
    public ResponseEntity<?> updateItem( @RequestBody Item info ) {

        Item item = itemService.updateItem( info );

        log.info( "아이템 수정 = {}", item );

        return ResponseEntity.ok( item );
    }

    @DeleteMapping( "{itemOid}" )
    public ResponseEntity<?> deleteItem( @PathVariable( "itemOid" ) String itemOid ) {

        Item item = itemService.deleteItem( itemOid );

        log.info( "아이템 삭제 = {}", item );

        return ResponseEntity.ok( item );
    }

}
