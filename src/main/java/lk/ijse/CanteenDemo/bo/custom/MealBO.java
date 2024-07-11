package lk.ijse.CanteenDemo.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.MealDTO;

import java.sql.SQLException;

public interface MealBO extends SuperBO {
    ObservableList<MealDTO> getData() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean save(MealDTO mealDTO) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    MealDTO search(String id) throws SQLException, ClassNotFoundException;
}
