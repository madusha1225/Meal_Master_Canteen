package lk.ijse.CanteenDemo.bo.custom;

import lk.ijse.CanteenDemo.bo.SuperBO;

import java.sql.SQLException;

public interface SettingBO extends SuperBO {
    boolean update() throws SQLException, ClassNotFoundException;
    int getId(int tel) throws SQLException, ClassNotFoundException;
}
