package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ItemController;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemControllerImpl implements ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemControllerImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    @GetMapping("/items/{id}")
    public ItemPl getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @Override
    @GetMapping("/items/search")
    public List<ItemPl> searchByParams(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "size", required = false) Double size) {
        return itemService.searchByParams(ItemFilterPl.builder()
                .id(id)
                .name(name)
                .size(size)
                .category(category)
                .type(type)
                .build());
    }

}
