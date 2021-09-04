package entities;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderResult {
    private int totalNum;
    private String type;
    private double totalPrice;
    private List<OrderResultItems> orderResultItemsList;

    public int calTotalNumForOrderResult(List<OrderResultItems> orderResultItemsList) {

        orderResultItemsList.stream().forEach(
                orderResultItems -> {
                    totalNum = totalNum + orderResultItems.getBundle() * orderResultItems.getSum();
                }
        );
        return totalNum;

    }

    public double calTotalPriceForOrderResult(List<OrderResultItems> orderResultItemsList) {

        orderResultItemsList.stream().forEach(
                orderResultItems -> {
                    totalPrice = totalPrice + orderResultItems.getPrice();
                }
        );

        return totalPrice;
    }
}
