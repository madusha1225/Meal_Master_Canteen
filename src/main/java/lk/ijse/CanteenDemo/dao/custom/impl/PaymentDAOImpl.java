package lk.ijse.CanteenDemo.dao.custom.impl;

import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.PaymentDAO;
import lk.ijse.CanteenDemo.entity.Payment;


import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payments (orderId, paymentType, date) VALUES (?,?,?)",payment.getOrderId(), payment.getPaymentMethod(), payment.getDate());
    }
}
