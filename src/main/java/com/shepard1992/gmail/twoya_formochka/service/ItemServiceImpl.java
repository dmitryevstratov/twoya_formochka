package com.shepard1992.gmail.twoya_formochka.service;

import com.shepard1992.gmail.twoya_formochka.repository.api.ItemCategoryRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemRepository;
import com.shepard1992.gmail.twoya_formochka.repository.api.ItemTypeRepository;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemCategory;
import com.shepard1992.gmail.twoya_formochka.repository.entity.ItemType;
import com.shepard1992.gmail.twoya_formochka.repository.specification.ItemSpecification;
import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemFilterMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ItemMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemCategoryPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemFilterPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemPl;
import com.shepard1992.gmail.twoya_formochka.view.model.ItemTypePl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemTypeRepository typeRepository;
    private final ItemCategoryRepository categoryRepository;
    private final ItemFilterMapper filterMapper;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository repository, ItemTypeRepository typeRepository, ItemCategoryRepository categoryRepository, ItemFilterMapper filterMapper, ItemMapper itemMapper) {
        this.repository = repository;
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
        this.filterMapper = filterMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemPl getItemById(Integer id) {
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

    @Override
    public List<ItemPl> getItems() {
        return repository.findAll().stream()
                .map(itemMapper::mapperToItemPl)
                .sorted(Comparator.comparing(ItemPl::getId))
                .collect(Collectors.toList());
    }

    @Override
    public ItemPl addItem(ItemPl itemPl) {
        return getItemPlForSave(itemPl);
    }

    private ItemPl getItemPlForSave(ItemPl itemPl) {
        ItemType type = typeRepository.findByName(itemPl.getType().getName());
        ItemCategory category = categoryRepository.findByName(itemPl.getCategory().getName());

        if (type == null) {
            ItemType typeSave = typeRepository.save(ItemType.builder()
                    .name(itemPl.getType().getName())
                    .build());
            itemPl.setType(ItemTypePl.builder()
                    .id(typeSave.getId())
                    .name(typeSave.getName())
                    .build());
        } else {
            itemPl.setType(ItemTypePl.builder()
                    .id(type.getId())
                    .name(type.getName())
                    .build());
        }
        if (category == null) {
            ItemCategory categorySave = categoryRepository.save(ItemCategory.builder()
                    .name(itemPl.getCategory().getName())
                    .build());
            itemPl.setCategory(ItemCategoryPl.builder()
                    .id(categorySave.getId())
                    .name(categorySave.getName())
                    .build());
        } else {
            itemPl.setCategory(ItemCategoryPl.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build());
        }

        return itemMapper.mapperToItemPl(repository.save(itemMapper.mapperToItem(itemPl)));
    }

    @Override
    public ItemPl editItem(ItemPl itemPl) {
        return getItemPlForSave(itemPl);
    }

    @Override
    public List<ItemTypePl> searchItemType(String type) {
        if (StringUtils.isEmpty(type)) {
            return typeRepository.findAll().stream()
                    .map(tp -> ItemTypePl.builder()
                            .id(tp.getId())
                            .name(tp.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return typeRepository.findAllByName(type).stream()
                .map(tp -> ItemTypePl.builder()
                        .id(tp.getId())
                        .name(tp.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemCategoryPl> searchItemCategory(String category) {
        if (StringUtils.isEmpty(category)) {
            return categoryRepository.findAll().stream()
                    .map(tp -> ItemCategoryPl.builder()
                            .id(tp.getId())
                            .name(tp.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return categoryRepository.findAllByName(category).stream()
                .map(tp -> ItemCategoryPl.builder()
                        .id(tp.getId())
                        .name(tp.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItemById(Integer id) {
        Item item = repository.findById(id).get();
        item.getOrders().forEach(order -> order.deleteItem(item));
        repository.deleteById(id);
    }

}
