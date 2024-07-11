package lk.ijse.CanteenDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private int userId;
    private int empId;
    private double totalPrice;
    private Date date;
}