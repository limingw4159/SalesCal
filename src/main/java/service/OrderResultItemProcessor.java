package service;

import entities.Bundle;
import entities.OrderItem;
import entities.OrderResultItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResultItemProcessor {

    private List<OrderResultItems> orderResultItemsList;
    private OrderResultItemCalculator orderResultItemCalculator;
    private OrderResultItems orderResultItems;

    public List<OrderResultItems> calculate(OrderItem orderItem, List<Bundle> existBundle) {

        orderResultItemsList = new ArrayList<>();
        int existBundleLength = existBundle.size() - 1;
        int orderNum = orderItem.getNum();

        while (existBundleLength >= 0 && orderNum > 0) {

            if (orderNum > existBundle.get(existBundleLength).getMinNum()) {

                orderResultItemCalculator = new OrderResultItemCalculator();
                orderResultItems = new OrderResultItems();
                orderResultItems = orderResultItemCalculator.
                        generateOrderResultItems(existBundleLength, orderNum, existBundle);
                orderResultItemsList.add(orderResultItems);
                orderNum = orderResultItems.getLeftOrderNum();
            }

            existBundleLength--;
        }

        return orderResultItemsList;
    }


}
