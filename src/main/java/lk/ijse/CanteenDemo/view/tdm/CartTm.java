package lk.ijse.CanteenDemo.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String id;
    private String description;
    private double unitPrice;
    private int quantity;
    private double total;
}
