package lk.ijse.CanteenDemo.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.WorkerDAO;
import lk.ijse.CanteenDemo.entity.Worker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerDAOImpl implements WorkerDAO {
    public static Worker worker;

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from workers where workerId = ?",id);
    }

    @Override
    public boolean save(Worker worker) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Insert into workers values(?,?,?,?,?,?)",worker.getId(),worker.getUserId(),worker.getName(), worker.getAddress(),worker.getEmail(),worker.getPhone());
    }

    @Override
    public Worker search(String id) throws SQLException, ClassNotFoundException {
        worker = null;
        ResultSet set = SQLUtil.execute("select * from workers where workerId = ?",id);

        if (set.next()) {
            worker = new Worker(set.getString("workerId"), UserDAOImpl.user1.getId(), set.getString("name"), set.getString("address"), set.getString("email"), set.getInt("telephone_Number"));
        } else {
            new Alert(Alert.AlertType.ERROR,"can't find employee").show();
        }
        return worker;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update workers set name = ? ,address = ? ,email = ?,telephone_Number = ? where workerId = ?",worker.getName(),worker.getAddress(),worker.getEmail(),worker.getPhone(),worker.getId());
    }

    @Override
    public ObservableList<Worker> getData() throws SQLException, ClassNotFoundException {
        ObservableList<Worker> list = FXCollections.observableArrayList();

        ResultSet set = SQLUtil.execute("select * from workers");

        while (set.next()) {
            Worker tm = new Worker(set.getString("workerId"),set.getInt("userId"), set.getString("name"), set.getString("address"), set.getString("email"), set.getInt("telephone_Number"));
            list.add(tm);
        }
        return list;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM workers ORDER BY workerId");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
