package lk.ijse.CanteenDemo.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.custom.WorkerBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.WorkerDAO;
import lk.ijse.CanteenDemo.dao.custom.impl.WorkerDAOImpl;
import lk.ijse.CanteenDemo.dto.WorkerDTO;
import lk.ijse.CanteenDemo.entity.Worker;

import java.sql.SQLException;

public class WorkerBOImpl implements WorkerBO {

    WorkerDAO workerDAO = (WorkerDAO) DAOFactory.getDAO(DAOFactory.DAOType.WORKER);
    @Override
    public ObservableList<WorkerDTO> getData() throws SQLException, ClassNotFoundException {
        ObservableList<Worker> workers = workerDAO.getData();
        ObservableList<WorkerDTO> workerDTOS = FXCollections.observableArrayList();
        for (Worker worker : workers) {
            workerDTOS.add(new WorkerDTO(worker.getId(),worker.getUserId(),worker.getName(),worker.getAddress(),worker.getEmail(),worker.getPhone()));
        }
        return workerDTOS;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return workerDAO.delete(id);
    }
    @Override
    public boolean save(WorkerDTO workerDTO) throws SQLException, ClassNotFoundException {
        Worker worker = new Worker(workerDTO.getId(),workerDTO.getUserId(),workerDTO.getName(),workerDTO.getAddress(),workerDTO.getEmail(),workerDTO.getPhone());
        return workerDAO.save(worker);
    }
    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return workerDAO.update();
    }
    @Override
    public WorkerDTO search(String id) throws SQLException, ClassNotFoundException {
        Worker worker = workerDAO.search(id);
        return new WorkerDTO(worker.getId(),worker.getUserId(),worker.getName(),worker.getAddress(),worker.getEmail(),worker.getPhone());
    }
}
