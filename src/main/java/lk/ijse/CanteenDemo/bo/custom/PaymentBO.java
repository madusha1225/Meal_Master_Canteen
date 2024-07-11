package lk.ijse.CanteenDemo.bo.custom;

import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.PaymentDTO;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
}
