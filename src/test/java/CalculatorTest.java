import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
class CalculatorTest {

  private static ArrayList<String> userInput;
  private static Order order;
  private static Calculator calculator;
  private static List<OrderItem> orderItems;
  private static Map<String, List<Bundle>> bundles;

  @BeforeAll
  static void initialInput() {
    userInput = new ArrayList<>();
    userInput.add("18 FLAC");
    userInput.add("21 img");
    userInput.add("35 vid");
    calculator = new Calculator();
    order = new Order();

  }

  @Test
  void shouldGetRightUserInput() {
    Assertions.assertEquals("18 FLAC", userInput.get(0));
  }

  @Test
  void shouldInitialOrder() {

    order.initialOrder(userInput);
    Assertions.assertTrue(order.getOrderItems().size() > 2);
  }

  @Test
  void shouldInitialBundles() {
    bundles = calculator.initialBundles();
    Assertions.assertEquals(450, bundles.get("IMG").get(0).getAmount());
  }

  @Test
  void shouldGetResults() {
     orderItems = order.initialOrder(userInput);
    bundles = calculator.initialBundles();
    List<Result> results = calculator.doCalculate(orderItems,bundles);
    Assertions.assertTrue(results.size()>2);
  }
}
