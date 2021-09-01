package output;

import entities.OrderResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class OutputResource {


    public void printResult(List<OrderResult> results) {
        results.stream().forEach(
                result -> {
                    log.info(result.getTotalNum() + " " + result.getType() + " " + "$" + result.getTotalPrice());
                    result.getOrderResultItemsList().stream().forEach(
                            resultItems -> {
                                log.info(" " + resultItems.getSum() + " x " + resultItems.getBundle() + "  $" + resultItems.getPrice());
                            }
                    );
                }
        );
    }
}
