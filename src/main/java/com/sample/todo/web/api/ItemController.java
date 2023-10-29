package com.sample.todo.web.api;

import com.sample.todo.service.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/item" )
public class ItemController {

    private final ItemService itemService;
}
