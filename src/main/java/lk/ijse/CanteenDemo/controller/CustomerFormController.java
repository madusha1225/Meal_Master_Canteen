package lk.ijse.CanteenDemo.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.CanteenDemo.Util.Regex;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.CustomerBO;
import lk.ijse.CanteenDemo.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.entity.FactoryEmployee;
import lk.ijse.CanteenDemo.view.tdm.CustomerTm;

import java.sql.SQLException;

public class CustomerFormController {

    @FXML
    private TextField txtName;


    @FXML
    private TextField txtContactNumber;


    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableView<CustomerTm> cusTable;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBO(BOFactory.BOType.CUSTOMER);

    public void initialize(){
        setCellValueFactory();
        loadData();

    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("factoryEmployeeNumber"));

    }

    private void loadData() {
        cusTable.getItems().clear();
        try {
            ObservableList<CustomerTm> list = customerBO.getData();
            cusTable.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        clear();
    }

    private void clear() {
        txtName.setText("");
        txtContactNumber.setText("");
        //cusTable.setItems(null);
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            if (customerBO.delete(Integer.parseInt(txtContactNumber.getText()))){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer has been deleted").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadData();
    }

    @FXML
    void btnSaveAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();
        int contactNumber = Integer.parseInt(txtContactNumber.getText());

        if (isValied()) {
            boolean isSaved = customerBO.save(new FactoryEmployee(0,name,contactNumber));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }
        loadData();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            FactoryEmployeeDTO employee = customerBO.search(Integer.parseInt(txtContactNumber.getText()));
            updatevalue();
            if (customerBO.update()){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer has been updated").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadData();
    }

    private void updatevalue() {
        if (!txtName.getText().equals("") && !txtName.getText().equals(CustomerDAOImpl.employee.getName())) {
            CustomerDAOImpl.employee.setName(txtName.getText());
        }
        if (Integer.parseInt(txtContactNumber.getText()) != 0 && Integer.parseInt(txtContactNumber.getText()) != (CustomerDAOImpl.employee.getFactoryEmployeeNumber())) {
            CustomerDAOImpl.employee.setFactoryEmployeeNumber(Integer.parseInt(txtContactNumber.getText()));
        }
    }

    @FXML
    void txtIdAction(ActionEvent event) {
    }

    @FXML
    void txtSearchAction(ActionEvent event) {
        try {
            FactoryEmployeeDTO employee = customerBO.search(Integer.parseInt(txtSearch.getText()));
            if (employee != null){
                txtName.setText(employee.getName());
                txtContactNumber.setText(String.valueOf(employee.getFactoryEmployeeNumber()));
            } else {
                clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtContactNumber)) return false;
        return true;
    }

    @FXML
    void txtCustomerNameOnKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName);
    }
    @FXML
    void txtCustomerNumberOnKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtContactNumber);
    }

    public void customerTableAction(javafx.scene.input.MouseEvent mouseEvent) {
        CustomerTm selectedItem = cusTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtContactNumber.setText(String.valueOf(selectedItem.getFactoryEmployeeNumber()));
            txtName.setText(selectedItem.getName());
        }

    }
}
