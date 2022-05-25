package com.shepard1992.gmail.twoya_formochka.view.model;

import com.shepard1992.gmail.twoya_formochka.repository.entity.Item;
import com.shepard1992.gmail.twoya_formochka.view.model.enums.Month;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetMonthStatisticPl {

    private Integer year;

    private Month month;

    private Integer countOrders;

    private Integer countItems;

    private Double totalSum;

    private Double middleSumOfOrder;

    private Integer middleCountOfItems;

    private Double middleSumOfItem;

    public void addCountOrders() {
        this.countOrders++;
    }

    public void addCountItems(List<Item> list) {
        countItems += list.size();
    }

    public void addTotalSum(Double totalSum) {
        this.totalSum += totalSum;
    }

    public void addMiddleSumOfOrder() {
        this.middleSumOfOrder = this.totalSum / this.countOrders;
    }

    public void addMiddleCountOfItems() {
        this.middleCountOfItems = this.countItems / this.countOrders;
    }

    public void addMiddleSumOfItem() {
        this.middleSumOfItem = this.totalSum / this.countItems;
    }

}
