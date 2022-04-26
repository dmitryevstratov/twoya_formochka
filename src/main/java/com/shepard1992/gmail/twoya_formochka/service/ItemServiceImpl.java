package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ItemSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemFilterMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemFilterMapper filterMapper;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository repository, ItemFilterMapper filterMapper, ItemMapper itemMapper) {
        this.repository = repository;
        this.filterMapper = filterMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemPl getItemById(Long id) {
        return itemMapper.mapperToItemPl(Objects.requireNonNull(repository.findById(id).orElse(null)));
    }

    @Override
    public List<ItemPl> searchByParams(ItemFilterPl filterPl) {
        return repository.findAll(new ItemSpecification(filterMapper.mapperToFilter(filterPl)))
                .stream()
                .map(itemMapper::mapperToItemPl)
                .sorted((i, i1) -> Integer.parseInt((i.getId() - i1.getId()) + ""))
                .collect(Collectors.toList());
    }

}
