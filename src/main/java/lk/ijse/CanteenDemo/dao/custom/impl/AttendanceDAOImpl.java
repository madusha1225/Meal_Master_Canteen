package lk.ijse.CanteenDemo.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.AttendanceDAO;
import lk.ijse.CanteenDemo.view.tdm.AttendanceTm;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public boolean isInOrOut(int id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM attendance WHERE factoryEmployeeId = ? AND date = ? ORDER BY attendance_id DESC LIMIT 1",id,Date.valueOf(LocalDate.now()));

        if (rs.next()) {
            if (rs.getTime("outTime") == null) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean updateOutTime(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE attendance SET outTime = ? WHERE factoryEmployeeId = ? AND date = ? ORDER BY attendance_id DESC LIMIT 1",Time.valueOf(LocalTime.now()),id,Date.valueOf(LocalDate.now()));
    }
    @Override
    public boolean saveInTime(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO attendance (factoryEmployeeId, inTime, date) VALUES (?,?,?)",id, Time.valueOf(LocalTime.now()),Date.valueOf(LocalDate.now()));
    }
    @Override
    public ObservableList<AttendanceTm> getData() throws SQLException, ClassNotFoundException {
        ObservableList<AttendanceTm> list = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM attendance a JOIN Factory_Employee f on a.factoryEmployeeId = f.factoryEmployeeId");

        while (rs.next()) {
            list.add(new AttendanceTm(rs.getString("name"),rs.getDate("date"),rs.getTime("inTime"), rs.getTime("outTime")));
        }
        return list;
    }
}
