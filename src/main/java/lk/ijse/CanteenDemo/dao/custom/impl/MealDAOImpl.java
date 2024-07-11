package lk.ijse.CanteenDemo.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.CanteenDemo.dao.SQLUtil;
import lk.ijse.CanteenDemo.dao.custom.MealDAO;
import lk.ijse.CanteenDemo.entity.Meal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDAOImpl implements MealDAO {
    public static Meal meal;

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from meal where mealId = ?",id);
    }

    @Override
    public boolean save(Meal meal) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Insert into meal values(?,?,?,?)",meal.getMealId(),meal.getDescription(),meal.getPrice(),meal.getStatus());
    }

    @Override
    public ObservableList<String> getItemId() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute("SELECT mealId,status FROM meal");
        while (resultSet.next()){
            if(resultSet.getString("status").equals("Available")){
                list.add(resultSet.getString(1));
            }
        }
        return list;
    }

    @Override
    public Meal getMeal(String id) throws SQLException, ClassNotFoundException {
        meal = null;

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM meal WHERE mealId = ?",id);

        if (resultSet.next()){
            meal = new Meal(resultSet.getString("mealId"),resultSet.getString("description"),resultSet.getDouble("price"),resultSet.getString("status"));
        }
        return meal;
    }

    @Override
    public ObservableList<Meal> getData() throws SQLException, ClassNotFoundException {
        ObservableList<Meal> list = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM meal");

        while (resultSet.next()){
            list.add(new Meal(resultSet.getString("mealId"), resultSet.getString("description"), resultSet.getDouble("price"), resultSet.getString("status")));
        }
        return list;
    }

    @Override
    public Meal search(String id) throws SQLException, ClassNotFoundException {
        meal = null;
        ResultSet resultSet = SQLUtil.execute("select * from meal where mealId = ?",id);

        if (resultSet.next()) {
            meal = new Meal(resultSet.getString("mealId"),resultSet.getString("description"),resultSet.getDouble("price"),resultSet.getString("status"));
        } else {
            new Alert(Alert.AlertType.ERROR,"can't find meal").show();
        }
        return meal;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update meal set description = ? ,price = ? ,status = ? where mealId = ?", meal.getDescription(),meal.getPrice(),meal.getStatus(),meal.getMealId());
    }

    @Override
    public ObservableList<String> getName() throws SQLException, ClassNotFoundException {

        ObservableList<String> list = FXCollections.observableArrayList();
        PreparedStatement pstm = SQLUtil.execute("Select description from meal");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(resultSet.getString("description"));
        }
        return list;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM meal ORDER BY mealId");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
