package lk.ijse.CanteenDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    private int paymentId;
    private String orderId;
    private String paymentMethod;
    private Date date;

    public Payment(String orderId, String paymentMethod, Date date) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }
}
