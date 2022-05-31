package com.shepard1992.gmail.twoya_formochka.view.controller.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ItemCategoryPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemTypePl;

import java.util.List;

public interface ItemController {

    ItemPl getItemById(Integer id);

    List<ItemPl> searchByParams(Integer id,
                                String name,
                                String type,
                                String category,
                                Double size);

    List<ItemPl> getItems();

    ItemPl addItem(ItemPl itemPl);

    ItemPl editItem(ItemPl itemPl);

    List<ItemTypePl> searchItemType(String type);

    List<ItemCategoryPl> searchItemCategory(String category);

    void deleteItemById(Integer id);

}
