package lk.ijse.CanteenDemo.dao.custom;

import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.entity.User;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    boolean checkUser(User user);
    boolean update() throws SQLException, ClassNotFoundException;
}
