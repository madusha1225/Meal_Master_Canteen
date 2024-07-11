package lk.ijse.CanteenDemo.dao.custom;

import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.dto.PlaceOrder;
import lk.ijse.CanteenDemo.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends SuperDAO {
    String getCurrentId() throws SQLException, ClassNotFoundException;
    boolean save(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException;
    int getCount () throws SQLException, ClassNotFoundException;
    Order getLastOrder() throws SQLException, ClassNotFoundException;
}
