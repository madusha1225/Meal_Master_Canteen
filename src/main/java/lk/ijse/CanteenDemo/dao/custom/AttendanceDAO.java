package lk.ijse.CanteenDemo.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.dao.SuperDAO;
import lk.ijse.CanteenDemo.view.tdm.AttendanceTm;

import java.sql.SQLException;

public interface AttendanceDAO extends SuperDAO {
    boolean isInOrOut(int id) throws SQLException, ClassNotFoundException;
    boolean updateOutTime(int id) throws SQLException, ClassNotFoundException;
    boolean saveInTime(int id) throws SQLException, ClassNotFoundException;
    ObservableList<AttendanceTm> getData() throws SQLException, ClassNotFoundException;


}
