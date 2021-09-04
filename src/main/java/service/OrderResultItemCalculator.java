package service;

import entities.Bundle;
import entities.OrderResultItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class OrderResultItemCalculator {

    private int totalSum;
    private int leftOrderNum;

    public OrderResultItems generateOrderResultItems(int existBundleLength, int orderNum, List<Bundle> existBundle) {

        if (orderNum > existBundle.get(existBundleLength).getMinNum()) {

            int sum = orderNum / existBundle.get(existBundleLength).getNum();
            int newSum = sum == 0 ? 1 : 0;
            int newOrderNum = orderNum % existBundle.get(existBundleLength).getNum();
            totalSum = totalSum + sum + newSum;
            orderNum = newOrderNum == orderNum ? 0 : newOrderNum;
            leftOrderNum = orderNum;
            generateOrderResultItems(existBundleLength, orderNum, existBundle);

        }
        double totalPrice = totalSum * existBundle.get(existBundleLength).getAmount();
        return OrderResultItems.builder()
                .sum(totalSum)
                .leftOrderNum(leftOrderNum)
                .bundle(existBundle.get(existBundleLength).getNum())
                .price(totalPrice)
                .build();
    }
}
