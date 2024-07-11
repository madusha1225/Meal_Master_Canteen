package lk.ijse.CanteenDemo.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.SuperBO;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.view.tdm.AttendanceTm;

import java.sql.SQLException;

public interface AttendanceBO extends SuperBO {
    ObservableList<AttendanceTm> getData() throws SQLException, ClassNotFoundException;
    FactoryEmployeeDTO getCustomerDetail(int id) throws SQLException, ClassNotFoundException;
    boolean isInOrOut(int id) throws SQLException, ClassNotFoundException;
    boolean updateOutTime(int id) throws SQLException, ClassNotFoundException;
    boolean saveInTime(int id) throws SQLException, ClassNotFoundException;
}
