package service;

import entities.Bundle;
import entities.OrderResultItems;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Calculator {

    public List<OrderResultItems> calEachItems(int orderNum, List<Bundle> bundles1) {
        List<OrderResultItems> orderResultItemsList = new ArrayList<>();
        OrderResultItems orderResultItems = new OrderResultItems();
        int length = bundles1.size() - 1;
        while (length >= 0) {
            if (orderNum > bundles1.get(length).getMinNum()) {
                int sum = orderNum / bundles1.get(length).getNum();
                int newSum = sum != 0 ? sum : 1;
                int newOrderNum = orderNum % bundles1.get(length).getNum();
                orderResultItems.setSum(orderResultItems.getSum() + newSum);
                orderResultItems.setBundle(bundles1.get(length).getNum());
                orderResultItems.setPrice(orderResultItems.getPrice() +
                        newSum * bundles1.get(length).getAmount());
                orderNum = newOrderNum == orderNum ? 0 : newOrderNum;
                continue;
            }
            if (orderResultItems.getSum() != 0) {
                orderResultItemsList.add(orderResultItems);
            }
            orderNum = orderNum % bundles1.get(length).getNum();
            length--;
            orderResultItems = new OrderResultItems();
        }
        return orderResultItemsList;
    }


}
