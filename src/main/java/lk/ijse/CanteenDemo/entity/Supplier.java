package lk.ijse.CanteenDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private String id;
    private String name;
    private String address;
    private String email;
    private int phone;

}
