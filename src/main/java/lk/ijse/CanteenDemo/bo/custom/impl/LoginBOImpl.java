package lk.ijse.CanteenDemo.bo.custom.impl;

import lk.ijse.CanteenDemo.bo.custom.LoginBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.UserDAO;
import lk.ijse.CanteenDemo.dto.UserDTO;
import lk.ijse.CanteenDemo.entity.User;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOType.USER);
    @Override
    public boolean checkUser(UserDTO userDTO) {
        User user = new User(userDTO.getUserName(), userDTO.getPassword());
        return userDAO.checkUser(user);
    }
}
