package lk.ijse.CanteenDemo.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.UserDAO;
import lk.ijse.CanteenDemo.db.DbConnection;
import lk.ijse.CanteenDemo.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    public static User user1 = new User();

    @Override
    public boolean checkUser(User user) {
        String sql = "select userPassword,userId from User where userName = ?";

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, user.getUserName());
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                if (user.getPassword().equals(rs.getString("userPassword"))){
                    user1.setId(rs.getInt("userId"));
                    user1.setPassword(rs.getString("userPassword"));
                    user1.setUserName(user.getUserName());
                    return true;
                } else {
                    new Alert(Alert.AlertType.ERROR,"Incorrect password" ).show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR,"Can't find user name" ).show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update User set userName = ? , userPassword = ? where userId = ?",user1.getUserName(),user1.getPassword(),user1.getId());
    }
}
