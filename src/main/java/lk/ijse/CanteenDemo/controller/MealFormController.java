package lk.ijse.CanteenDemo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.CanteenDemo.Util.Regex;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.MealBO;
import lk.ijse.CanteenDemo.dao.custom.impl.MealDAOImpl;
import lk.ijse.CanteenDemo.dto.MealDTO;
import lk.ijse.CanteenDemo.view.tdm.MealTm;

public class MealFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtDis;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TextField melSearch;

    @FXML
    private TableView<MealTm> melTable;

    @FXML
    private TableColumn<?, ?> melId;

    @FXML
    private TableColumn<?, ?> melDiscription;

    @FXML
    private TableColumn<?, ?> melPrice;

    @FXML
    private TableColumn<?, ?> melStatus;

    MealBO mealBO = (MealBO) BOFactory.getBO(BOFactory.BOType.MEAL);

    public void initialize(){
        setCellValueFactory();
        setComboBoxValue();
        loadData();

    }

    private void setComboBoxValue() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Available");
        list.add("Unavailable");
        statusComboBox.setItems(list);
    }

    private void setCellValueFactory() {
        melId.setCellValueFactory(new PropertyValueFactory<>("mealId"));
        melDiscription.setCellValueFactory(new PropertyValueFactory<>("description"));
        melPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        melStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void loadData() {
        melTable.getItems().clear();
        try {
            ObservableList<MealDTO> list = mealBO.getData();
            for (MealDTO mealDTO : list) {
                melTable.getItems().add(new MealTm(mealDTO.getMealId(),mealDTO.getDescription(),mealDTO.getPrice(),mealDTO.getStatus()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        clear();
    }

    private void clear() {
        txtId.setText("");
        txtDis.setText("");
        txtPrice.setText("");
        statusComboBox.setValue("Available");
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            if (mealBO.delete(txtId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Meal has been deleted").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadData();

    }

    @FXML
    void btnSaveAction(ActionEvent event)  {
        String id = txtId.getText();
        String name = txtDis.getText();
        double price =  Double.parseDouble(txtPrice.getText());
        String status = statusComboBox.getValue();

        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved = mealBO.save(new MealDTO(id, name,price,status));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Meal has been Saved!").show();
                clear();
            }
        }
        loadData();

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            MealDTO mealDTO = mealBO.search(txtId.getText());
            updatevalue();
            if (mealBO.update()){
                new Alert(Alert.AlertType.CONFIRMATION,"Meal has been updated").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadData();
    }

    private void updatevalue() {
        if (!txtDis.getText().equals("") && !txtDis.getText().equals(MealDAOImpl.meal.getDescription())){
            MealDAOImpl.meal.setDescription(txtDis.getText());
        }
        if (!statusComboBox.getValue().equals("") && !statusComboBox.getValue().equals(MealDAOImpl.meal.getStatus())){
            MealDAOImpl.meal.setStatus(statusComboBox.getValue());
        }
        if (!txtPrice.getText().equals("") && !txtPrice.getText().equals(MealDAOImpl.meal.getPrice())){
            MealDAOImpl.meal.setPrice(Double.parseDouble(txtPrice.getText()));
        }
    }

    @FXML
    void txtIdAction(ActionEvent event) {

    }

    @FXML
    void searchAction(ActionEvent event) {
        try {
            MealDTO mealDTO = mealBO.search(melSearch.getText());
            if (mealDTO != null){
                txtId.setText(mealDTO.getMealId());
                txtDis.setText(mealDTO.getDescription());
                txtPrice.setText(String.valueOf(mealDTO.getPrice()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ID,txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtDis)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ADDRESS,txtPrice)) return false;
        return true;
    }
    @FXML
    void txtMealDisKeyRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtDis);
    }

    @FXML
    void txtMealIdKeyRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ID,txtId);
    }

    @FXML
    void txtMealPriceKeyRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.PRICE,txtPrice);
    }

    public void MealTableMouse(MouseEvent mouseEvent) {
        MealTm selectedItem = melTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null)
            txtId.setText(selectedItem.getMealId());
            txtDis.setText(selectedItem.getDescription());
            txtPrice.setText(String.valueOf(selectedItem.getPrice()));
            statusComboBox.setValue(selectedItem.getStatus());
    }
}
