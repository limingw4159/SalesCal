package service;

import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderProcessor {
    public List<OrderResult> doCalculate(Order order, Map<String, List<Bundle>> bundles) {
        List<OrderResult> orderResultList = new ArrayList<>();
        Calculator calculator = new Calculator();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.stream().forEach(
                orderItem -> {
                    OrderResult orderResult = new OrderResult();
                    String formatCode = orderItem.getFormatCode();
                    int orderNum = orderItem.getNum();
                    List<Bundle> bundles1 = bundles.get(formatCode);
                    List<OrderResultItems> orderResultItemsList = calculator.calEachItems(orderNum, bundles1);
                    int totalNum = orderResult.calTotalNum(orderResultItemsList);
                    double totalPrice = orderResult.calTotalPrice(orderResultItemsList);
                    orderResult.setOrderResultItemsList(orderResultItemsList);
                    orderResult.setTotalNum(totalNum);
                    orderResult.setType(formatCode);
                    orderResult.setTotalPrice(totalPrice);
                    orderResultList.add(orderResult);
                }
        );
        return orderResultList;
    }
}
