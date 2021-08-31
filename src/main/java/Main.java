import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("Please input the order you want:");
        InputResources Ir = new InputResources();
        Order order = new Order();
        Calculator cal = new Calculator();
        List<OrderItem> orderItems = order.initialOrder(Ir.inputString());
        Map<String, List<Bundle>> bundles = cal.initialBundles();
        boolean b = cal.doEquals(orderItems, bundles);
        if (b==false){
            throw new FormatCodeException("the format code is wrong please input again");
        }
        List<Result> results = cal.doCalculate(orderItems, bundles);
        OutputResource outputResource = new OutputResource();
        outputResource.printResult(results);
    }
}
