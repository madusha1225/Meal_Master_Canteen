package lk.ijse.CanteenDemo.bo.custom.impl;

import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.PaymentBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.PaymentDAO;
import lk.ijse.CanteenDemo.dto.PaymentDTO;
import lk.ijse.CanteenDemo.entity.Payment;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAO(DAOFactory.DAOType.PAYMENT);
    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        Payment payment = new Payment(paymentDTO.getOrderId(),paymentDTO.getPaymentMethod(),paymentDTO.getDate());
        return paymentDAO.save(payment);
    }
}
