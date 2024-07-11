package lk.ijse.CanteenDemo.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.SupplierDAO;
import lk.ijse.CanteenDemo.entity.Supplier;
import lk.ijse.CanteenDemo.view.tdm.SupplierTm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAOImpl implements SupplierDAO {
    public static Supplier supplier;

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from supplier where supplierId = ?",id);
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Insert into supplier values(?,?,?,?,?)",supplier.getId(), supplier.getName(),supplier.getAddress(),supplier.getEmail(),supplier.getPhone());
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        supplier = null;
        ResultSet set = SQLUtil.execute("select * from supplier where supplierId = ?",id);

        if (set.next()) {
            supplier = new Supplier(set.getString("SupplierId"), set.getString("name"), set.getString("address"), set.getString("email"), set.getInt("telePhone_Number"));
        } else {
            new Alert(Alert.AlertType.ERROR,"can't find employee").show();
        }
        return supplier;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update supplier set name = ? ,address = ? ,email = ? ,telePhone_Number = ? where supplierId = ?", supplier.getName(),supplier.getAddress(),supplier.getEmail(), supplier.getPhone(),supplier.getId());
    }

    @Override
    public ObservableList<Supplier> getData() throws SQLException, ClassNotFoundException {
        ObservableList<Supplier> list = FXCollections.observableArrayList();

        ResultSet set = SQLUtil.execute("select * from supplier");

        while (set.next()) {
            Supplier tm = new Supplier(set.getString("supplierId"), set.getString("name"), set.getString("address"), set.getString("email"), set.getInt("telePhone_Number"));
            list.add(tm);
        }
        return list;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM supplier ORDER BY supplierId");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
