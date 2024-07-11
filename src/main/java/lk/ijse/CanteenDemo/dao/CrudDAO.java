package lk.ijse.CanteenDemo.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CrudDAO<T> {
    boolean save(T obj) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    ObservableList<T> getData() throws SQLException, ClassNotFoundException;
}
