package lk.ijse.CanteenDemo.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.dao.CrudDAO;
import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;

import java.sql.SQLException;

public interface CustomerDAO extends SuperDAO, CrudDAO<FactoryEmployee> {
    FactoryEmployee search(int tel) throws SQLException, ClassNotFoundException;
    boolean delete(int tel) throws SQLException, ClassNotFoundException;
    ObservableList<String> getIds() throws SQLException, ClassNotFoundException;
    FactoryEmployee getCustomer(int tel) throws SQLException, ClassNotFoundException;
    FactoryEmployee getCustomerDetail(int id) throws SQLException, ClassNotFoundException;
    int getId(int tel) throws SQLException, ClassNotFoundException;
}
