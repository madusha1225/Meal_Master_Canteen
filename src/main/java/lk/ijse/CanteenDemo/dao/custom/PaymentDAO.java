package lk.ijse.CanteenDemo.dao.custom;

import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends SuperDAO {
    boolean save(Payment payment) throws SQLException, ClassNotFoundException;
}
