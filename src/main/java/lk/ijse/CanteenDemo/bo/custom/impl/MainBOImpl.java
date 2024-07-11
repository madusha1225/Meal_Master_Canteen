package lk.ijse.CanteenDemo.bo.custom.impl;

import lk.ijse.CanteenDemo.bo.custom.MainBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.MealDAO;
import lk.ijse.CanteenDemo.dao.custom.OrderDAO;
import lk.ijse.CanteenDemo.dao.custom.SupplierDAO;
import lk.ijse.CanteenDemo.dao.custom.WorkerDAO;
import lk.ijse.CanteenDemo.dao.custom.impl.OrderDAOImpl;

import java.sql.SQLException;

public class MainBOImpl implements MainBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.DAOType.ORDER);
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAO(DAOFactory.DAOType.MEAL);
    WorkerDAO workerDAO = (WorkerDAO) DAOFactory.getDAO(DAOFactory.DAOType.WORKER);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public int getOrderCount () throws SQLException, ClassNotFoundException {
        return orderDAO.getCount();
    }
    @Override
    public int getMealCount() throws SQLException, ClassNotFoundException {
        return mealDAO.getCount();
    }
    @Override
    public int getWorkerCount() throws SQLException, ClassNotFoundException {
        return workerDAO.getCount();
    }
    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        return supplierDAO.getCount();
    }
}
