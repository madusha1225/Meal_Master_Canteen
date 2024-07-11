package lk.ijse.CanteenDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private int phone;

}
