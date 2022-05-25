package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;

import java.util.List;

public interface ItemController {

    ItemPl getItemById(Integer id);

    List<ItemPl> searchByParams(Integer id,
                                String name,
                                String type,
                                String category,
                                Double size);

}
