package lk.ijse.CanteenDemo.dao.custom;

import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.entity.OrderDetails;


import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends SuperDAO {
    boolean save(List<OrderDetails> placeOrder) throws SQLException, ClassNotFoundException;
    boolean addItem(OrderDetails details) throws SQLException, ClassNotFoundException;
}
