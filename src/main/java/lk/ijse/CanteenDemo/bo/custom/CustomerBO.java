package lk.ijse.CanteenDemo.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;
import lk.ijse.CanteenDemo.view.tdm.CustomerTm;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    ObservableList<CustomerTm> getData() throws SQLException, ClassNotFoundException;
    boolean delete(int tel) throws SQLException, ClassNotFoundException;
    boolean save(FactoryEmployee employee) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    FactoryEmployeeDTO search(int tel) throws SQLException, ClassNotFoundException;
}
