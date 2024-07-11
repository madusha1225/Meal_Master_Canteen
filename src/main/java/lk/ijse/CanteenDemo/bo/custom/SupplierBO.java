package lk.ijse.CanteenDemo.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.SupplierDTO;
import lk.ijse.CanteenDemo.entity.Supplier;

import java.sql.SQLException;

public interface SupplierBO extends SuperBO {
    ObservableList<SupplierDTO> getData() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    SupplierDTO search(String id) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
}
