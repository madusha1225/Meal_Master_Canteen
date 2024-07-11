package lk.ijse.CanteenDemo.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.custom.AttendanceBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.AttendanceDAO;
import lk.ijse.CanteenDemo.dao.custom.CustomerDAO;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;
import lk.ijse.CanteenDemo.view.tdm.AttendanceTm;

import java.sql.SQLException;

public class AttendanceBOImpl implements AttendanceBO {
    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDAO(DAOFactory.DAOType.ATTENDANCE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public ObservableList<AttendanceTm> getData() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getData();
    }
    @Override
    public FactoryEmployeeDTO getCustomerDetail(int id) throws SQLException, ClassNotFoundException {
        FactoryEmployee employee = customerDAO.getCustomerDetail(id);
        return new FactoryEmployeeDTO(employee.getId(),employee.getName(),employee.getFactoryEmployeeNumber());
    }
    @Override
    public boolean isInOrOut(int id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.isInOrOut(id);
    }
    @Override
    public boolean updateOutTime(int id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.updateOutTime(id);
    }
    @Override
    public boolean saveInTime(int id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.saveInTime(id);
    }
}
