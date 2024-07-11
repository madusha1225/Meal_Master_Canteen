package lk.ijse.CanteenDemo.dao;

import lk.ijse.CanteenDemo.dao.custom.AttendanceDAO;
import lk.ijse.CanteenDemo.dao.custom.impl.*;

public class DAOFactory {

    public enum DAOType {
        ATTENDANCE,CUSTOMER,MEAL,ORDER,ORDERDETAILS,PAYMENT,SUPPLIER,USER,WORKER
    }

    public static SuperDAO getDAO(DAOType type) {
        switch (type) {
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case MEAL:
                return new MealDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            case WORKER:
                return new WorkerDAOImpl();
            default:
                return null;
        }
    }
}
