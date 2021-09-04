import config.BundleConfig;
import entities.Bundle;
import entities.Order;
import entities.OrderItem;
import entities.OrderResult;
import exception.FormatCodeException;
import input.InputResources;
import lombok.extern.slf4j.Slf4j;
import mappers.OrderParser;
import output.OutputResource;
import service.OrderMatchProcessor;
import service.OrderProcessor;

import java.util.List;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {

        log.info("Please input the order you want:");
        InputResources iR = new InputResources();
        OrderParser orderParser = new OrderParser();
        BundleConfig bundleConfig = new BundleConfig();
        OutputResource outputResource = new OutputResource();
        OrderProcessor orderProcessor = new OrderProcessor();
        OrderMatchProcessor orderMatchProcessor = new OrderMatchProcessor();

        Map<String, List<Bundle>> existBundles = bundleConfig.initialBundles();
        List<String> inputOrder = iR.inputString();
        Order order = orderParser.initialOrder(inputOrder);
        List<OrderItem> orderItems = order.getOrderItems();

        boolean checkUserInputNumbers = orderMatchProcessor.checkNumbers(orderItems);
        boolean checkUserInputFormat = orderMatchProcessor.doEquals(orderItems, existBundles);
        if (checkUserInputNumbers==false||checkUserInputFormat==false) {
            throw new FormatCodeException("the format code is wrong please input again");
        }

        List<OrderResult> orderResultList = orderProcessor.doCalculate(order, existBundles);
        outputResource.printResult(orderResultList);
    }
}
