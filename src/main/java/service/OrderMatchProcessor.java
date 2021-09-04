package service;

import entities.OrderItem;

import java.util.List;

public class OrderMatchProcessor {

    public boolean checkNumbers(List<OrderItem> orderItemList) {
        return orderItemList.stream().anyMatch(orderItem -> orderItem.getNum() < 0);
    }
}
