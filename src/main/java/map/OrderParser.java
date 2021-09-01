package map;

import entities.Order;
import entities.OrderItem;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderParser {

    public Order initialOrder(List<String> inputOrder) {
        Order order= new Order();
        List<OrderItem> orderItemList=new ArrayList<>();
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
            OrderItem orderItem = new OrderItem(num, format);
            orderItemList.add(orderItem);
        });
        order.setOrderItems(orderItemList);
        return order;
    }
}
