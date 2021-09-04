package service;

import entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProcessor {
    List<OrderResult> orderResultList;
    OrderResultItemProcessor calculator;
    List<OrderItem> orderItems;
    OrderResult orderResult;

    public List<OrderResult> doCalculate(Order order, Map<String, List<Bundle>> bundles) {

        orderResultList = new ArrayList<>();
        calculator = new OrderResultItemProcessor();
        orderItems = order.getOrderItems();

        orderItems.stream().forEach(
                orderItem -> {
                    orderResult = new OrderResult();
                    String formatCode = orderItem.getFormatCode();
                    List<Bundle> inputFormat = bundles.get(formatCode);
                    List<OrderResultItems> orderResultItemsList =
                            calculator.calculate(orderItem, inputFormat);

                    orderResultList.add(orderResult.builder()
                            .type(formatCode)
                            .orderResultItemsList(orderResultItemsList)
                            .totalNum(orderResult.calTotalNumForOrderResult(orderResultItemsList))
                            .totalPrice(orderResult.calTotalPriceForOrderResult(orderResultItemsList))
                            .build());

                }
        );
        return orderResultList;
    }
}
