package lk.ijse.CanteenDemo.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.CanteenDemo.Util.Regex;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.SupplierBO;
import lk.ijse.CanteenDemo.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.CanteenDemo.dto.SupplierDTO;
import lk.ijse.CanteenDemo.view.tdm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TableColumn<?, ?> supId;

    @FXML
    private TableColumn<?, ?> supName;

    @FXML
    private TableColumn<?, ?> supAddress;

    @FXML
    private TableColumn<?, ?> supEmail;

    @FXML
    private TableColumn<?, ?> supPhone;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<SupplierTm> supTable;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBO(BOFactory.BOType.SUPPLIER);

    public void initialize(){
        setCellValueFactory();
        loadData();

    }

    private void setCellValueFactory() {
        supId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supName.setCellValueFactory(new PropertyValueFactory<>("name"));
        supAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        supEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        supPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void loadData() {
        supTable.getItems().clear();
        try {
            ObservableList<SupplierDTO> list =supplierBO.getData();
            for (SupplierDTO supplierDTO : list) {
                supTable.getItems().add(new SupplierTm(supplierDTO.getId(),supplierDTO.getName(),supplierDTO.getAddress(),supplierDTO.getEmail(),supplierDTO.getPhone()));
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
        txtPhoneNumber.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            if (supplierBO.delete(txtId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier has been deleted").show();
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
        int phone =  Integer.parseInt(txtPhoneNumber.getText());
        String email = txtEmail.getText();

        try {
            if (isValied()) {
                boolean isSaved = supplierBO.save(new SupplierDTO(id, name,address,email,phone));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier has been Saved!").show();
                    clear();
                }
            }
            loadData();
        }catch (Exception e){}
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            SupplierDTO SupplierDTO = supplierBO.search(txtId.getText());
            updatevalue();
            if (supplierBO.update()){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier has been updated").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadData();
    }

    private void updatevalue() {
        if (!txtName.getText().equals("") && !txtName.getText().equals(SupplierDAOImpl.supplier.getName())){
            SupplierDAOImpl.supplier.setName(txtName.getText());
        }
        if (!txtAddress.getText().equals("") && !txtAddress.getText().equals(SupplierDAOImpl.supplier.getAddress())){
            SupplierDAOImpl.supplier.setAddress(txtAddress.getText());
        }
        if (!txtEmail.getText().equals("") && !txtEmail.getText().equals(SupplierDAOImpl.supplier.getEmail())){
            SupplierDAOImpl.supplier.setEmail(txtEmail.getText());
        }
        if (Integer.parseInt(txtPhoneNumber.getText()) != 0 && Integer.parseInt(txtPhoneNumber.getText()) != (SupplierDAOImpl.supplier.getPhone())){
            SupplierDAOImpl.supplier.setPhone(Integer.parseInt(txtPhoneNumber.getText()));
        }
    }

    @FXML
      void txtIdAction(ActionEvent event) {

    }

    @FXML
    void txtSearchAction(ActionEvent event) {
        try {
            SupplierDTO supplierDTO = supplierBO.search(txtSearch.getText());
            if (supplierDTO != null){
                txtId.setText(supplierDTO.getId());
                txtName.setText(supplierDTO.getName());
                txtAddress.setText(supplierDTO.getAddress());
                txtEmail.setText(supplierDTO.getEmail());
                txtPhoneNumber.setText(String.valueOf(supplierDTO.getPhone()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ID,txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.EMAIL,txtEmail)) return false;
       if (!Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtPhoneNumber)) return false;
        return true;
    }
    @FXML
    void txtSAddKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ADDRESS,txtAddress);
    }

    @FXML
    void txtSEmaKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.EMAIL,txtEmail);
    }

    @FXML
    void txtSIdOnKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.ID,txtId);
    }

    @FXML
    void txtSNOnKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName);
    }

    @FXML
    void txtSPhoKeyReleased(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtPhoneNumber);
    }

    @FXML
    void btnEmailFrom(ActionEvent event) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/EmailSend_form.fxml")));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void supplierMouse(javafx.scene.input.MouseEvent mouseEvent) {
        SupplierTm selectedItem = supTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null)
            txtId.setText(selectedItem.getSupplierId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtEmail.setText(selectedItem.getEmail());
            txtPhoneNumber.setText(String.valueOf(selectedItem.getPhone()));
    }
}
