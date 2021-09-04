import config.BundleConfig;
import entities.Bundle;
import entities.Order;
import entities.OrderItem;
import entities.OrderResultItems;
import lombok.extern.slf4j.Slf4j;
import mappers.OrderParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.OrderResultItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
class CalculatorTest {

    private static ArrayList<String> userInput;
    private static Order order;
    private static OrderResultItemProcessor orderResultItemProcessor;
    private static OrderItem orderItem;
    private static Map<String, List<Bundle>> bundles;
    private static BundleConfig bundleConfig;
    private static OrderParser orderParser;


    @BeforeAll
    static void initialInput() {
        userInput = new ArrayList<>();
        userInput.add("18 FLAC");
        userInput.add("21 img");
        userInput.add("35 vid");
        orderParser = new OrderParser();
        orderResultItemProcessor = new OrderResultItemProcessor();
        order = new Order();
        bundleConfig = new BundleConfig();
        orderItem = new OrderItem(18, "img");

    }

    @Test
    void shouldGetRightUserInput() {
        Assertions.assertEquals("18 FLAC", userInput.get(0));
    }

    @Test
    void shouldInitialOrder() {
        order = orderParser.initialOrder(userInput);
        Assertions.assertTrue(order.getOrderItems().size() > 2);
    }

    @Test
    void shouldInitialBundles() {
        bundles = bundleConfig.initialBundles();
        Assertions.assertEquals(450, bundles.get("IMG").get(0).getAmount());
    }

    @Test
    void shouldNotGetResults() {
        order = orderParser.initialOrder(userInput);
        bundles = bundleConfig.initialBundles();
        List<Bundle> img = bundles.get("IMG");
        List<OrderResultItems> orderResultItemsList = orderResultItemProcessor.calculate(orderItem, img);
        Assertions.assertFalse(orderResultItemsList.size() > 2);
    }
}
