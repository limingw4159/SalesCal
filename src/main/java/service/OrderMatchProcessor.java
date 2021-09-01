package service;

import entities.Bundle;
import entities.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMatchProcessor {
    public boolean doEquals(List<OrderItem> orderItemList, Map<String, List<Bundle>> bundles) {
        List<String> collect = orderItemList.stream().map(OrderItem::getFormatCode).collect(Collectors.toList());
        List<String> collect1 = bundles.keySet().stream().collect(Collectors.toList());

        return collect1.stream().allMatch(collect::contains);
    }
    public boolean checkNumbers(List<OrderItem> orderItemList){
      return orderItemList.stream().anyMatch(orderItem -> orderItem.getNum() < 0);
    }
}
