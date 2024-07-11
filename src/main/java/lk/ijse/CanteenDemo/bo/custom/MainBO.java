package lk.ijse.CanteenDemo.bo.custom;

import lk.ijse.CanteenDemo.bo.SuperBO;

import java.sql.SQLException;

public interface MainBO extends SuperBO {
    int getOrderCount () throws SQLException, ClassNotFoundException;
    int getMealCount() throws SQLException, ClassNotFoundException;
    int getWorkerCount() throws SQLException, ClassNotFoundException;
    int getSupplierCount() throws SQLException, ClassNotFoundException;
}
