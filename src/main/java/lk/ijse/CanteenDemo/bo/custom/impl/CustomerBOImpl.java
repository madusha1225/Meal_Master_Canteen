package lk.ijse.CanteenDemo.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.custom.CustomerBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.CustomerDAO;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;
import lk.ijse.CanteenDemo.view.tdm.CustomerTm;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ObservableList<CustomerTm> getData() throws SQLException, ClassNotFoundException{
        ObservableList<CustomerTm> list = FXCollections.observableArrayList();
        ObservableList<FactoryEmployee> data = customerDAO.getData();
        for (FactoryEmployee factoryEmployee : data) {
            list.add(new CustomerTm(factoryEmployee.getId(),factoryEmployee.getName(),factoryEmployee.getFactoryEmployeeNumber()));
        }
        return list;
    }
    @Override
    public boolean delete(int tel) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(tel);
    }
    @Override
    public boolean save(FactoryEmployee employee) throws SQLException, ClassNotFoundException {
        return customerDAO.save(employee);
    }
    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return customerDAO.update();
    }
    @Override
    public FactoryEmployeeDTO search(int tel) throws SQLException, ClassNotFoundException {
        FactoryEmployee factoryEmployee = customerDAO.search(tel);
        FactoryEmployeeDTO factoryEmployeeDTO = new FactoryEmployeeDTO(factoryEmployee.getId(),factoryEmployee.getName(),factoryEmployee.getFactoryEmployeeNumber());
        return factoryEmployeeDTO;
    }
}
