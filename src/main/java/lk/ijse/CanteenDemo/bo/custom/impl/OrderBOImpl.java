package lk.ijse.CanteenDemo.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.custom.OrderBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.CustomerDAO;
import lk.ijse.CanteenDemo.dao.custom.MealDAO;
import lk.ijse.CanteenDemo.dao.custom.OrderDAO;
import lk.ijse.CanteenDemo.dao.custom.OrderDetailsDAO;
import lk.ijse.CanteenDemo.db.DbConnection;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.dto.MealDTO;
import lk.ijse.CanteenDemo.dto.OrderDetailsDTO;
import lk.ijse.CanteenDemo.dto.PlaceOrder;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;
import lk.ijse.CanteenDemo.entity.Meal;
import lk.ijse.CanteenDemo.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.DAOType.ORDER);
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAO(DAOFactory.DAOType.MEAL);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDAO(DAOFactory.DAOType.ORDERDETAILS);

    @Override
    public int getCount () throws SQLException, ClassNotFoundException {
        return orderDAO.getCount();
    }
    @Override
    public ObservableList<String> getItemId() throws SQLException, ClassNotFoundException {
        return mealDAO.getItemId();
    }
    @Override
    public MealDTO getMeal(String id) throws SQLException, ClassNotFoundException {
        Meal meal = mealDAO.getMeal(id);
        MealDTO mealDTO = new MealDTO(meal.getMealId(),meal.getDescription(),meal.getPrice(),meal.getStatus());
        return mealDTO;
    }
    @Override
    public FactoryEmployeeDTO getCustomer(int tel) throws SQLException, ClassNotFoundException {
        FactoryEmployee factoryEmployee = customerDAO.getCustomer(tel);
        FactoryEmployeeDTO employeeDTO = new FactoryEmployeeDTO(factoryEmployee.getId(),factoryEmployee.getName(),factoryEmployee.getFactoryEmployeeNumber());
        return employeeDTO;
    }
    @Override
    public boolean placeOrder(PlaceOrder placeOrder) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        List<OrderDetails> list = new ArrayList<>();
        for (OrderDetailsDTO orderDetailsDTO : placeOrder.getDetails()){
            list.add(new OrderDetails(orderDetailsDTO.getOrderID(),orderDetailsDTO.getMealId(),orderDetailsDTO.getQty()));
        }

        try {
            if (orderDAO.save(placeOrder)) {
                if (orderDetailsDAO.save(list)){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

}
