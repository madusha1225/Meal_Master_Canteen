package lk.ijse.CanteenDemo.dao.custom.impl;

import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.OrderDAO;
import lk.ijse.CanteenDemo.dto.PlaceOrder;
import lk.ijse.CanteenDemo.entity.Order;


import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO{

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean save(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?,?,?)",placeOrder.getOrder().getOrderId(), placeOrder.getOrder().getUserId(), placeOrder.getOrder().getEmpId(), placeOrder.getOrder().getTotalPrice(), placeOrder.getOrder().getDate());
    }

    @Override
    public int getCount () throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM orders ORDER BY orderId");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public Order getLastOrder() throws SQLException, ClassNotFoundException {
        Order order = null;
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()){
            order = new Order(resultSet.getString(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDouble(4),resultSet.getDate(5));
            return order;
        }
        return null;
    }
}
