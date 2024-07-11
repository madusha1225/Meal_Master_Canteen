package lk.ijse.CanteenDemo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lk.ijse.CanteenDemo.Util.Regex;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.SettingBO;
import lk.ijse.CanteenDemo.dao.custom.impl.UserDAOImpl;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class SettingFormController {

    @FXML
    private TextField txtOldName;

    @FXML
    private TextField txtPassword;

    @FXML
    private Pane visiblePane;

    @FXML
    private TextField txtNewName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtTel;

    @FXML
    private Pane QRPane;

    @FXML
    private TextField txtQRNAme;

    SettingBO settingBO = (SettingBO) BOFactory.getBO(BOFactory.BOType.SETTING);

    private int id1;

    public void initialize(){
        visiblePane.setVisible(false);
        QRPane.setVisible(false);
    }

    @FXML
    void cnfirmAction(ActionEvent event) {
        UserDAOImpl.user1.setUserName(txtNewName.getText());
        UserDAOImpl.user1.setPassword(txtNewPassword.getText());
        try {
            if (settingBO.update()){
                new Alert(Alert.AlertType.CONFIRMATION,"User Updated").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtPasswordAction(ActionEvent event) {
        if (UserDAOImpl.user1.getUserName().equals(txtOldName.getText())){
            if (UserDAOImpl.user1.getPassword().equals(txtPassword.getText())){
                visiblePane.setVisible(true);
            } else {
                new Alert(Alert.AlertType.ERROR,"Worng Password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Worng UserName").show();
        }
    }

    @FXML
    void txtTelAction(ActionEvent event) {
        QRPane.setVisible(false);
        try {
            int id = settingBO.getId(Integer.parseInt(txtTel.getText()));
            if (id != 0){
                QRPane.setVisible(true);
                id1 = id;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void QRSAVEAction(ActionEvent event) {
        saveQrImage();
    }

    @FXML
    void txtQRNameAction(ActionEvent event) {
        saveQrImage();
    }


    private void saveQrImage(){
        if (!txtQRNAme.getText().equals("")){
            String QR_CODE_IMAGE_PATH = "C:\\Users\\ASUS\\Downloads\\" + txtQRNAme.getText() + ".png";

            try {
                String text = String.valueOf(id1); // Content for the QR code
                int width = 300;
                int height = 300;
                String format = "png";

                BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
                Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);
                MatrixToImageWriter.writeToPath(bitMatrix, format, path);
                new Alert(Alert.AlertType.CONFIRMATION,"QR Code saved successfully.").show();
            } catch (WriterException | IOException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            clear();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Please enter QR Code Name").show();
        }
    }

    private void clear(){
        QRPane.setVisible(false);
        txtTel.setText("");
    }

    @FXML
    void txtSettingUse2rKey(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtNewName);
    }

    @FXML
    void txtSettingUserKey(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.NAME,txtOldName);
    }

    @FXML
    void txtSettingUserPass2Key(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.PASSWORD,txtNewPassword);
    }

    @FXML
    void txtSettingUserPassKey(javafx.scene.input.KeyEvent event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.PASSWORD,txtPassword);
    }
}
