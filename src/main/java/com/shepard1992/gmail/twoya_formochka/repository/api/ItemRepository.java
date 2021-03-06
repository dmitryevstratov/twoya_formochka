package com.shepard1992.gmail.twoya_formochka.repository.api;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
}
