package lk.ijse.CanteenDemo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.PaymentBO;
import lk.ijse.CanteenDemo.db.DbConnection;
import lk.ijse.CanteenDemo.dto.PaymentDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaymentFormController {

    @FXML
    private Button BillId;

    @FXML
    private CheckBox cardBox;

    @FXML
    private CheckBox cashBox;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtCash;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtTotal;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private Pane showPane;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBO(BOFactory.BOType.PAYMENT);

    public static PaymentDTO paymentDTO = new PaymentDTO();

    public void initialize(){
        showPane.setVisible(false);
        txtDate.setText(String.valueOf(LocalDate.now()));
        getLastOrder();
        setPayment();
    }

    private void setPayment() {
        paymentDTO.setOrderId(txtOrderId.getText());
        paymentDTO.setDate(Date.valueOf(txtDate.getText()));
    }

    private void getLastOrder() {
        txtOrderId.setText(OrderFormController.orderDTO.getOrderId());
        txtTotal.setText(String.valueOf(OrderFormController.orderDTO.getTotalPrice()));
    }

    @FXML
    void cardBoxAction(ActionEvent event) {
        cardBox.setSelected(true);
        cashBox.setSelected(false);
        showPane.setVisible(false);
        paymentDTO.setPaymentMethod("Card");
    }

    @FXML
    void cashBoxAction(ActionEvent event) {
        cashBox.setSelected(true);
        cardBox.setSelected(false);
        showPane.setVisible(true);
        paymentDTO.setPaymentMethod("Cash");
    }


    void printBillAction(){
        try {
            if (paymentBO.save(paymentDTO)){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed Successfully !!").show();


            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        close();

    }

    @FXML
    void txtCashAction(ActionEvent event) {
        double balance = Double.parseDouble(txtCash.getText()) - Double.parseDouble(txtTotal.getText());
        txtBalance.setText(String.valueOf(balance));
    }

    @FXML
    void closeAction(ActionEvent  event) {
        close();
    }

    private void close() {
        Stage stage = (Stage) paymentPane.getScene().getWindow();
        stage.close();
    }


    @FXML
    void BillOnAction(ActionEvent event) throws JRException, SQLException {
        printBillAction();
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\ASUS\\Downloads\\Meal_Master_Canteen\\Meal_Master_Canteen\\Meal_Master_Canteen\\src\\main\\resources\\Repoers\\meal_master_canteen.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("orderId",txtOrderId.getText());
        // data.put("Total",getTotal());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

}
