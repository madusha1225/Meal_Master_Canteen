package lk.ijse.CanteenDemo.dao.custom;

import lk.ijse.CanteenDemo.dao.CrudDAO;
import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.entity.Supplier;

import java.sql.SQLException;


public interface SupplierDAO extends SuperDAO, CrudDAO<Supplier> {
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    Supplier search(String id) throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}
