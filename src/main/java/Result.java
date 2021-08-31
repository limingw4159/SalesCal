import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {
    private int totalNum;
    private String type;
    private double totalPrice;
    private List<ResultItems> resultItemsList;


    public void addResultItems(ResultItems resultItems) {
        this.resultItemsList.add(resultItems);
    }

    public int calTotalNum(int pNum, int newNum) {
        return pNum + newNum;
    }

    public double calTotalPrice(double pPrice, double nPrice) {
        return pPrice + nPrice;
    }
}
