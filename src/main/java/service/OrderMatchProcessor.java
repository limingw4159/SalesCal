package service;

import entities.Bundle;
import entities.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMatchProcessor {

    public boolean checkInputFormat(List<OrderItem> orderItemList, Map<String, List<Bundle>> bundles) {
        List<String> inputOrderFormat = orderItemList.stream().map(OrderItem::getFormatCode).collect(Collectors.toList());
        List<String> existFormat = bundles.keySet().stream().collect(Collectors.toList());
        return inputOrderFormat.stream().allMatch(existFormat::contains);
    }

    public boolean checkNumbers(List<OrderItem> orderItemList) {
        return orderItemList.stream().anyMatch(orderItem -> orderItem.getNum() < 0);
    }
}
