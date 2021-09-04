package entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResultItems {
    private int sum;
    private int bundle;
    private double price;
    private int leftOrderNum;

}
