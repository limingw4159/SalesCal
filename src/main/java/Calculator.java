import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Getter
public class Calculator {

    private ResultItems resultItems;
    private Result result;
    private List<Result> resultsLists;
    private List<ResultItems> resultItemsList;
    private  Map<String, List<Bundle>> bundles;

    public Map<String, List<Bundle>> initialBundles() {
        bundles = new HashMap<>();
        Bundle bundle = new Bundle(5, 450);
        Bundle bundle1 = new Bundle(10, 800);
        Bundle bundle2 = new Bundle(3, 427.50);
        Bundle bundle3 = new Bundle(6, 810);
        Bundle bundle4 = new Bundle(9, 1147.50);
        Bundle bundle5 = new Bundle(3, 570);
        Bundle bundle6 = new Bundle(5, 900);
        Bundle bundle7 = new Bundle(9, 1530);
        List<Bundle> lBundle = new ArrayList<>() {{
            add(bundle);
            add(bundle1);
        }};
        List<Bundle> lBundle1 = new ArrayList<>() {{
            add(bundle2);
            add(bundle3);
            add(bundle4);
        }};
        List<Bundle> lBundle2 = new ArrayList<>() {{
            add(bundle5);
            add(bundle6);
            add(bundle7);
        }};
        bundles.put("IMG", lBundle);
        bundles.put("FLAC", lBundle1);
        bundles.put("VID", lBundle2);
        return bundles;
    }

    public boolean doEquals(List<OrderItem> orderItemList, Map<String, List<Bundle>> bundles) {
        List<String> collect = orderItemList.stream().map(OrderItem::getFormatCode).collect(Collectors.toList());
        List<String> collect1 = bundles.keySet().stream().collect(Collectors.toList());
        return collect1.stream().allMatch(collect::contains);
    }

    public List<Result> doCalculate(List<OrderItem> orderItemSet, Map<String, List<Bundle>> bundles) {
        resultsLists = new ArrayList<>();
        orderItemSet.stream().forEach(
                orderItem -> {
                    String formatCode = orderItem.getFormatCode();
                    int orderNum = orderItem.getNum();

                    List<Bundle> bundles1 = bundles.get(formatCode);
                    int[] ints = bundles1.stream().mapToInt(bun -> bun.getNum()).toArray();
                    Arrays.sort(ints);
                    int maxInt = bundles1.stream().mapToInt(bun -> bun.getNum()).max().getAsInt();
                    int minInt = bundles1.stream().mapToInt(bun -> bun.getNum()).min().getAsInt();
                    int length = ints.length;
                    result = new Result();
                    resultItemsList = new ArrayList<>();
                    resultItems = new ResultItems();
                    result.setType(formatCode);
                    result.setResultItemsList(resultItemsList);
                    result.setTotalNum(0);
                    resultItems.setSum(0);
                    doCal(orderNum, ints, length, maxInt, minInt, bundles1);
                    resultsLists.add(result);
                }
        );

        return resultsLists;
    }

    public void doCal(int remain, int[] ints, int length, int maxInt, int minInt, List<Bundle> bundles) {

        if (remain >= maxInt) {
            int newRemain = remain % maxInt;
            int sum = remain / maxInt;
            resultItems = new ResultItems();
            resultItems.setSum(+sum);
            resultItems.setBundle(maxInt);
            resultItems.setPrice(sum * bundles.get(length - 1).getAmount());
            result.setTotalNum(result.calTotalNum(result.getTotalNum(), sum * maxInt));
            result.setTotalPrice(result.calTotalPrice(result.getTotalPrice(), sum * bundles.get(length - 1).getAmount()));
            result.addResultItems(resultItems);
            doCal(newRemain, ints, length, maxInt, minInt, bundles);
        }
        if (remain > minInt && remain < maxInt) {

            if (remain > ints[length - 2]) {
                length = length - 1;
                calResult(resultItems, ints, length, bundles);
                if (result.getResultItemsList().size() == 0) {
                    result.addResultItems(resultItems);
                }
                return;
            }
            resultItems = new ResultItems();
            length = length - 2;
            calResult(resultItems, ints, length, bundles);
            result.addResultItems(resultItems);
        }
        if (remain <= minInt && remain != 0) {
            resultItems = new ResultItems();
            remain = minInt;
            resultItems.setBundle(remain);
            resultItems.setSum(1);
            resultItems.setPrice(bundles.get(0).getAmount());
            result.setTotalNum(result.calTotalNum(result.getTotalNum(), remain));
            result.setTotalPrice(result.calTotalPrice(result.getTotalPrice(), bundles.get(0).getAmount()));
            result.addResultItems(resultItems);
            return;
        }
        if (remain == 0) {
            return;
        }

    }

    public void calResult(ResultItems resultItems, int[] ints, int length, List<Bundle> bundles) {
        resultItems.setSum(resultItems.getSum() + 1);
        resultItems.setBundle(ints[length]);
        resultItems.setPrice(bundles.get(length).getAmount() * resultItems.getSum());
        result.setTotalNum(result.calTotalNum(result.getTotalNum(), ints[length]));
        result.setTotalPrice(result.calTotalPrice(result.getTotalPrice(), bundles.get(length).getAmount()));
    }

}
