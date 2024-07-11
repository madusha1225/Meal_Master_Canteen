package lk.ijse.CanteenDemo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.CanteenDemo.Util.Regex;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.LoginBO;
import lk.ijse.CanteenDemo.dto.UserDTO;


import java.io.IOException;

public class LoginFormController {

    public AnchorPane loginForm;
    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPass;

    LoginBO loginBO = (LoginBO) BOFactory.getBO(BOFactory.BOType.LOGIN);

    @FXML
    void btnLoginAction(javafx.event.ActionEvent actionEvent) throws IOException {
        String name = txtName.getText();
        String psw = txtPass.getText();

        UserDTO userDTO = new UserDTO(name, psw);

        boolean isUser = loginBO.checkUser(userDTO);

        if (isUser) {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) loginForm.getScene().getWindow();
            stage1.close();
        }
    }
    @FXML
    void txtPasswordAction(javafx.event.ActionEvent event) {
        try {
            btnLoginAction(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtUserOnKey(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtName);
    }

    @FXML
    void txtUserPassOnKey(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.PASSWORD,txtPass);
    }

    @FXML
    void txtUserNameAction(javafx.event.ActionEvent event) {
        txtPass.requestFocus();
    }
}