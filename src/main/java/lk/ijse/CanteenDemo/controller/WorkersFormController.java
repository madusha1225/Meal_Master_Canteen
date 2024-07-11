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
import lk.ijse.CanteenDemo.bo.custom.WorkerBO;
import lk.ijse.CanteenDemo.dao.custom.impl.UserDAOImpl;
import lk.ijse.CanteenDemo.dao.custom.impl.WorkerDAOImpl;
import lk.ijse.CanteenDemo.dto.WorkerDTO;
import lk.ijse.CanteenDemo.view.tdm.WorkerTm;

import java.sql.SQLException;

public class WorkersFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSearch;


    @FXML
    private TableColumn<?, ?> worId;

    @FXML
    private TableColumn<?, ?> worName;

    @FXML
    private TableColumn<?, ?> worAddr;

    @FXML
    private TableColumn<?, ?> workEmail;

    @FXML
    private TableColumn<?, ?> workPhone;


    @FXML
    private TableView<WorkerTm> worTable;

    WorkerBO workerBO = (WorkerBO) BOFactory.getBO(BOFactory.BOType.WORKER);

    public void initialize(){
        setCellValueFactory();
        loadData();

    }

    private void setCellValueFactory() {
        worId.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        worName.setCellValueFactory(new PropertyValueFactory<>("name"));
        worAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        workEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        workPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void loadData() {
        worTable.getItems().clear();
        try {
            ObservableList<WorkerDTO> list = workerBO.getData();
            for (WorkerDTO workerDTO : list) {
                worTable.getItems().add(new WorkerTm(workerDTO.getId(),workerDTO.getName(),workerDTO.getAddress(),workerDTO.getEmail(),workerDTO.getPhone()));
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
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            if (workerBO.delete(txtId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Worker has been deleted").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadData();

    }

    @FXML
    void btnSaveAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int phone =  Integer.parseInt(txtPhone.getText());
        String email = txtEmail.getText();

        try {
            if (isValied()) {
                boolean isSaved = workerBO.save(new WorkerDTO(id, UserDAOImpl.user1.getId(), name,address,email,phone));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Worker has been Saved!").show();
                    clear();
                }
            }
            loadData();
        }catch (Exception e){}
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            WorkerDTO workerDTO = workerBO.search(txtId.getText());
            updatevalue();
            if (workerBO.update()){
                loadData();
                new Alert(Alert.AlertType.CONFIRMATION,"Worker has been updated").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void updatevalue() {
        if (!txtName.getText().equals("") && !txtName.getText().equals(WorkerDAOImpl.worker.getName())){
            WorkerDAOImpl.worker.setName(txtName.getText());
        }
        if (!txtAddress.getText().equals("") && !txtAddress.getText().equals(WorkerDAOImpl.worker.getAddress())){
            WorkerDAOImpl.worker.setAddress(txtAddress.getText());
        }
        if (!txtEmail.getText().equals("") && !txtEmail.getText().equals(WorkerDAOImpl.worker.getEmail())){
            WorkerDAOImpl.worker.setEmail(txtEmail.getText());
        }
        if (Integer.parseInt(txtPhone.getText()) != 0 && Integer.parseInt(txtPhone.getText()) != (WorkerDAOImpl.worker.getPhone())){
            WorkerDAOImpl.worker.setPhone(Integer.parseInt(txtPhone.getText()));
        }
    }

    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ID,txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtPhone)) return false;
        return true;
    }

    @FXML
    void txtIdAction(ActionEvent event) {
    }
    @FXML
    void txtSearchAction(ActionEvent event) {
        try {
            WorkerDTO workerDTO = workerBO.search(txtSearch.getText());
            txtId.setText(workerDTO.getId());
            txtName.setText(workerDTO.getName());
            txtPhone.setText(String.valueOf(workerDTO.getPhone()));
            txtAddress.setText(workerDTO.getAddress());
            txtEmail.setText(workerDTO.getEmail());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtWoAddressRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ADDRESS,txtAddress);
    }

    @FXML
    void txtWoEmailRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.EMAIL,txtEmail);
    }

    @FXML
    void txtWoIdRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ID,txtId);
    }

    @FXML
    void txtWoNameRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName);
    }

    @FXML
    void txtWoPhoneNumRe(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtPhone);
    }

    public void workerMouse(javafx.scene.input.MouseEvent mouseEvent) {
        WorkerTm selectedItem = worTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null)
            txtId.setText(selectedItem.getWorkerId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(String.valueOf(selectedItem.getPhone()));
    }
}
