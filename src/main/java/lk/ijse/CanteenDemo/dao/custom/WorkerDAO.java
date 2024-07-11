package lk.ijse.CanteenDemo.dao.custom;

import lk.ijse.CanteenDemo.dao.CrudDAO;
import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.dto.WorkerDTO;
import lk.ijse.CanteenDemo.entity.Worker;

import java.sql.SQLException;

public interface WorkerDAO extends SuperDAO, CrudDAO<Worker> {
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    Worker search(String id) throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}

