package lk.ijse.CanteenDemo.bo.custom.impl;

import lk.ijse.CanteenDemo.bo.custom.SettingBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.CustomerDAO;
import lk.ijse.CanteenDemo.dao.custom.UserDAO;
import lk.ijse.CanteenDemo.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;

public class SettingBOImpl implements SettingBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOType.USER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return userDAO.update();
    }
    @Override
    public int getId(int tel) throws SQLException, ClassNotFoundException {
        return customerDAO.getId(tel);
    }
}
