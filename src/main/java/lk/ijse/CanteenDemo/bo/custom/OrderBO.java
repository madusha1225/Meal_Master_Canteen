package lk.ijse.CanteenDemo.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.dto.MealDTO;
import lk.ijse.CanteenDemo.dto.PlaceOrder;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    int getCount () throws SQLException, ClassNotFoundException;
    ObservableList<String> getItemId() throws SQLException, ClassNotFoundException;
    MealDTO getMeal(String id) throws SQLException, ClassNotFoundException;
    FactoryEmployeeDTO getCustomer(int tel) throws SQLException, ClassNotFoundException;
    boolean placeOrder(PlaceOrder placeOrder) throws SQLException;
}
