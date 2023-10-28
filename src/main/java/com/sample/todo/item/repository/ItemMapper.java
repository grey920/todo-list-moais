package com.sample.todo.item.repository;

import com.sample.todo.item.domain.Item;

public interface ItemMapper {

    /**
     * 아이템 정보 등록
     * @param item
     * @return
     */
    public int insertItem( Item item );
}
