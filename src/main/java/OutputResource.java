import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class OutputResource {


    public void printResult(List<Result> results) {
        results.stream().forEach(
                result -> {
                    log.info(result.getTotalNum() + " " + result.getType() + " " + "$" + result.getTotalPrice());
                    result.getResultItemsList().stream().forEach(
                            resultItems -> {
                                log.info(" " + resultItems.getSum() + " x " + resultItems.getBundle() + "  $" + resultItems.getPrice());
                            }
                    );
                }
        );
    }
}
