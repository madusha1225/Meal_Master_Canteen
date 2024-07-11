package lk.ijse.CanteenDemo.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.CanteenDemo.bo.custom.MealBO;
import lk.ijse.CanteenDemo.dao.DAOFactory;
import lk.ijse.CanteenDemo.dao.custom.MealDAO;
import lk.ijse.CanteenDemo.dto.MealDTO;
import lk.ijse.CanteenDemo.entity.Meal;

import java.sql.SQLException;

public class MealBOImpl implements MealBO {
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAO(DAOFactory.DAOType.MEAL);
    @Override
    public ObservableList<MealDTO> getData() throws SQLException, ClassNotFoundException {
        ObservableList<Meal> meals = mealDAO.getData();
        ObservableList<MealDTO> mealDTOS = FXCollections.observableArrayList();
        for (Meal meal : meals) {
            mealDTOS.add(new MealDTO(meal.getMealId(),meal.getDescription(),meal.getPrice(),meal.getStatus()));
        }
        return mealDTOS;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return mealDAO.delete(id);
    }
    @Override
    public boolean save(MealDTO mealDTO) throws SQLException, ClassNotFoundException {
        Meal meal = new Meal(mealDTO.getMealId(),mealDTO.getDescription(),mealDTO.getPrice(),mealDTO.getStatus());
        return mealDAO.save(meal);
    }
    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return mealDAO.update();
    }
    @Override
    public MealDTO search(String id) throws SQLException, ClassNotFoundException {
        Meal meal = mealDAO.search(id);
        MealDTO mealDTO = new MealDTO(meal.getMealId(),meal.getDescription(),meal.getPrice(),meal.getStatus());
        return mealDTO;
    }
}
