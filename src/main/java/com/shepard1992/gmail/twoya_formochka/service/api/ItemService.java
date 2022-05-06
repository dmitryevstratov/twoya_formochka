package com.shepard1992.gmail.twoya_formochka.service.api;

import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;

import java.util.List;

public interface ItemService {

    ItemPl getItemById(Integer id);

    List<ItemPl> searchByParams(ItemFilterPl filterPl);

}
