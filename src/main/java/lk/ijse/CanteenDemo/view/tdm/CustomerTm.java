package lk.ijse.CanteenDemo.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTm {
    private int customerId;
    private String name;
    private int factoryEmployeeNumber;
}
