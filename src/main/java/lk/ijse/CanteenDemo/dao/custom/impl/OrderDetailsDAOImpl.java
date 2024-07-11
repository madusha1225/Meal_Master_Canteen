package lk.ijse.CanteenDemo.dao.custom.impl;

import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.OrderDetailsDAO;
import lk.ijse.CanteenDemo.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(List<OrderDetails> placeOrder) throws SQLException, ClassNotFoundException {
        for (OrderDetails i : placeOrder){
            if (!addItem(i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addItem(OrderDetails details) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails VALUES (?,?,?)",details.getOrderID(),details.getMealId(),details.getQty());
    }
}
