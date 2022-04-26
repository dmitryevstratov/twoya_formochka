package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;

import java.util.List;

public interface ItemController {

    ItemPl getItemById(Long id);

    List<ItemPl> searchByParams(Long id,
                                String name,
                                String type,
                                String category,
                                Double size);

}
