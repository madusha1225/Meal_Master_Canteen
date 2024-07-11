package lk.ijse.CanteenDemo.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.WorkerDTO;
import lk.ijse.CanteenDemo.entity.Worker;

import java.sql.SQLException;

public interface WorkerBO extends SuperBO {
    ObservableList<WorkerDTO> getData() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean save(WorkerDTO worker) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    WorkerDTO search(String id) throws SQLException, ClassNotFoundException;
}
