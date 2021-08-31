import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private List<OrderItem> orderItems;

    public List<OrderItem> initialOrder(List<String> inputOrder) {
        orderItems=new ArrayList<>();
         inputOrder.stream().forEach(input -> {
            int num = 0;
            String format = null;
            try {
                String[] s = input.split(" ");
                num = Integer.parseInt(s[0]);
                format = s[1].toUpperCase();
            } catch (Exception e) {
                log.error("the format is wrong, please input again");
                System.exit(0);
            }
            OrderItem orderItem=new OrderItem(num,format);
            orderItems.add(orderItem);
         });
         return orderItems;
    }
}
