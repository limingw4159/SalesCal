package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResult {
    private int totalNum;
    private String type;
    private double totalPrice;
    private List<OrderResultItems> orderResultItemsList;

    public int calTotalNum(List<OrderResultItems> orderResultItemsList) {
        orderResultItemsList.stream().forEach(
                orderResultItems -> {
                    totalNum = totalNum + orderResultItems.getBundle() * orderResultItems.getSum();
                }
        );
        return totalNum;

    }

    public double calTotalPrice(List<OrderResultItems> orderResultItemsList) {
        orderResultItemsList.stream().forEach(
                orderResultItems -> {
                    totalPrice = totalPrice + orderResultItems.getPrice();
                }
        );
        return totalPrice;
    }
}
