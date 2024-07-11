package lk.ijse.CanteenDemo.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.dao.CrudDAO;
import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.entity.Meal;

import java.sql.SQLException;

public interface MealDAO extends SuperDAO, CrudDAO<Meal> {
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    ObservableList<String> getItemId() throws SQLException, ClassNotFoundException;
    Meal getMeal(String id) throws SQLException, ClassNotFoundException;
    Meal search(String id) throws SQLException, ClassNotFoundException;
    ObservableList<String> getName() throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}
