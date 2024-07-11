package lk.ijse.CanteenDemo.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.CustomerDAO;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    public static FactoryEmployee employee;

    @Override
    public boolean delete(int tel) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "delete from Factory_Employee where factoryEmployeeNumber = ?",tel);
    }

    @Override
    public boolean save(FactoryEmployee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Insert into Factory_Employee (name, factoryEmployeeNumber) values(?,?)",employee.getName(),employee.getFactoryEmployeeNumber());
    }

    @Override
    public FactoryEmployee search(int tel) throws SQLException, ClassNotFoundException {
        employee = null;
        ResultSet set = SQLUtil.execute("select * from Factory_Employee where factoryEmployeeNumber = ?",tel);

        if (set.next()) {
            employee = new FactoryEmployee(set.getInt("factoryEmployeeId"), set.getString("name"),set.getInt("factoryEmployeeNumber"));
        } else {
            new Alert(Alert.AlertType.ERROR,"can't find employee").show();
        }
        return employee;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Factory_Employee set name = ?  where factoryEmployeeNumber = ?",employee.getName(),employee.getFactoryEmployeeNumber());
    }

    @Override
    public ObservableList<FactoryEmployee> getData() throws SQLException, ClassNotFoundException {
        ObservableList<FactoryEmployee> list = FXCollections.observableArrayList();

        ResultSet set = SQLUtil.execute( "select * from Factory_Employee");

        while (set.next()) {
            FactoryEmployee tm = new FactoryEmployee(set.getInt("factoryEmployeeId"), set.getString("name"), set.getInt("factoryEmployeeNumber"));
            list.add(tm);
        }
        return list;
    }

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute("SELECT factoryEmployeeId FROM Factory_Employee");
        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public FactoryEmployee getCustomer(int tel) throws SQLException, ClassNotFoundException {
        employee = null;

        ResultSet set = SQLUtil.execute("Select * from Factory_Employee where factoryEmployeeNumber = ?",tel);

        while (set.next()){
            employee = new FactoryEmployee(set.getInt("factoryEmployeeId"), set.getString("name"),set.getInt("factoryEmployeeNumber"));
        }
        return employee;
    }

    @Override
    public FactoryEmployee getCustomerDetail(int id) throws SQLException, ClassNotFoundException {
        employee = null;

        ResultSet set = SQLUtil.execute("Select * from Factory_Employee where factoryEmployeeId = ?",id);

        while (set.next()){
            employee = new FactoryEmployee(set.getInt("factoryEmployeeId"), set.getString("name"),set.getInt("factoryEmployeeNumber"));
        }
        return employee;
    }

    @Override
    public int getId(int tel) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("Select factoryEmployeeId from Factory_Employee where factoryEmployeeNumber = ?",tel);
        if (set.next()){
            return set.getInt("factoryEmployeeId");
        }
        return 0;
    }

}
