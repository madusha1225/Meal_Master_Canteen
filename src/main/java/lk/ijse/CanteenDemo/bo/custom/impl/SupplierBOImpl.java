package lk.ijse.CanteenDemo.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.custom.SupplierBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.SupplierDAO;
import lk.ijse.CanteenDemo.dto.SupplierDTO;
import lk.ijse.CanteenDemo.entity.Supplier;

import java.sql.SQLException;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public ObservableList<SupplierDTO> getData() throws SQLException, ClassNotFoundException {
        ObservableList<Supplier> suppliers = supplierDAO.getData();
        ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList();
        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getEmail(),supplier.getPhone()));
        }
        return supplierDTOS;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
    @Override
    public boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(supplierDTO.getId(),supplierDTO.getName(),supplierDTO.getAddress(),supplierDTO.getEmail(),supplierDTO.getPhone());
        return supplierDAO.save(supplier);
    }
    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getEmail(),supplier.getPhone());
    }
    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return supplierDAO.update();
    }
}
