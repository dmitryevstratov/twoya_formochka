package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ItemController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ViewController;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemCategoryPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemTypePl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ItemControllerImpl implements ItemController, ViewController {

    private final ItemService service;

    @Autowired
    public ItemControllerImpl(ItemService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/items.html")
    public ModelAndView getView() {
        return new ModelAndView("items");
    }

    @Override
    @GetMapping("/items/{id}")
    public ItemPl getItemById(@PathVariable Integer id) {
        return service.getItemById(id);
    }

    @Override
    @GetMapping("/items/search")
    public List<ItemPl> searchByParams(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "size", required = false) Double size) {
        return service.searchByParams(ItemFilterPl.builder()
                .id(id)
                .name(name)
                .size(size)
                .category(category)
                .type(type)
                .build());
    }

    @Override
    @GetMapping("/items")
    public List<ItemPl> getItems() {
        return service.getItems();
    }

    @Override
    @PostMapping("/items/create")
    public ItemPl addItem(@RequestBody ItemPl itemPl) {
        return service.addItem(itemPl);
    }

    @Override
    @PutMapping("/items/edit")
    public ItemPl editItem(@RequestBody ItemPl itemPl) {
        return service.editItem(itemPl);
    }

    @Override
    @GetMapping("/items/search-type")
    public List<ItemTypePl> searchItemType(@RequestParam(value = "type", required = false) String type) {
        return service.searchItemType(type);
    }

    @Override
    @GetMapping("/items/search-category")
    public List<ItemCategoryPl> searchItemCategory(@RequestParam(value = "category", required = false) String category) {
        return service.searchItemCategory(category);
    }

    @Override
    @DeleteMapping("/items/{id}")
    public void deleteItemById(@PathVariable Integer id) {
        service.deleteItemById(id);
    }
}
