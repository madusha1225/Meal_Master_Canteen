package lk.ijse.CanteenDemo.bo.custom;

import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.UserDTO;

public interface LoginBO extends SuperBO {
     boolean checkUser(UserDTO userDTO);
}
