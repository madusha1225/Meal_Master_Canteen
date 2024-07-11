package lk.ijse.CanteenDemo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.CanteenDemo.Util.Regex;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.OrderBO;
import lk.ijse.CanteenDemo.dao.custom.impl.UserDAOImpl;
import lk.ijse.CanteenDemo.dto.*;
import lk.ijse.CanteenDemo.view.tdm.CartTm;
import lombok.Setter;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class OrderFormController {

    @FXML
    private ComboBox<String> ItemBox;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label labelCustomerName;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelDes;

    @FXML
    private Label labelNetTotal;

    @FXML
    private Label labelOrderID;

    @FXML
    private Label labelUnitPrice;

    @FXML
    private AnchorPane orderForm;

    @FXML
    private TableView<CartTm> orderTable;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtTel;

    @FXML
    private VBox namevBox;


    OrderBO orderBO = (OrderBO) BOFactory.getBO(BOFactory.BOType.ORDER);

    @Setter
    private MainFormController mainFormController;

    private ObservableList<CartTm> list = FXCollections.observableArrayList();
    private ObservableList<OrderDetailsDTO> details = FXCollections.observableArrayList();

    private MealDTO mealDTO = new MealDTO();
    public static OrderDTO orderDTO;

    public void initialize(){
        getOrderIDAndDate();
        setCellValueFactory();
        getItemId();

    }

    private void getOrderIDAndDate() {
        labelDate.setText(" " + LocalDate.now());

        try {
            int count = orderBO.getCount();
            String nextOrderId = generateNextOrderId(count);
            labelOrderID.setText(nextOrderId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private String generateNextOrderId(int count) {
        if(count != 0) {
            return "O" + ++count;
        }
        return "O1";
    }

    private void getItemId(){
        try {
            ObservableList<String> list = orderBO.getItemId();
            ItemBox.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void ItemBoxAction(ActionEvent event) {
        try {
            mealDTO = orderBO.getMeal(ItemBox.getValue());
            labelUnitPrice.setText(String.valueOf(mealDTO.getPrice()));
            labelDes.setText(String.valueOf(mealDTO.getDescription()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnAddTotable(ActionEvent event) {
        int qty = Integer.parseInt(txtQty.getText());

        for (int i = 0; i < orderTable.getItems().size(); i++){
            if (mealDTO.getMealId().equals(colId.getCellData(i))){
                CartTm tm = list.get(i);

                qty += tm.getQuantity();

                tm.setQuantity(qty);
                tm.setTotal(qty * mealDTO.getPrice());

                orderTable.refresh();
                setNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(mealDTO.getMealId(), mealDTO.getDescription(), mealDTO.getPrice(), qty,(mealDTO.getPrice()* qty));
        list.add(tm);
        orderTable.setItems(list);
        setNetTotal();
    }

    private void setNetTotal() {
        double total = 0;

        for (int i = 0;i < orderTable.getItems().size();i++){
            total += (Double) colTotal.getCellData(i);
        }
        labelNetTotal.setText(String.valueOf(total));
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    void btnNewOrder(ActionEvent event) {
        mainFormController.orderAction(event);
    }

    @FXML
    void btnPlaceToOrderAction(ActionEvent event) {
        for (int i = 0; i < orderTable.getItems().size(); i++) {
            OrderDetailsDTO details1 = new OrderDetailsDTO(labelOrderID.getText(),orderTable.getItems().get(i).getId(),orderTable.getItems().get(i).getQuantity());
            details.add(details1);
        }

        FactoryEmployeeDTO employee = null;
        try {
            employee = orderBO.getCustomer(Integer.parseInt(txtTel.getText()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

        orderDTO = new OrderDTO(labelOrderID.getText(), UserDAOImpl.user1.getId(), employee.getId(), Double.parseDouble(labelNetTotal.getText()), Date.valueOf(LocalDate.now()));

        PlaceOrder placeOrder = new PlaceOrder(orderDTO,details);

        try {
            if (orderBO.placeOrder(placeOrder)){
                //new Alert(Alert.AlertType.CONFIRMATION, "Order Placed Successfully !!").show();
                navigatePayment();
            } else {
                new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully !!").show();
            }
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void navigatePayment() {
        try {
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml")));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void txtOrderPhone(javafx.scene.input.KeyEvent  event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.CONTACT,txtTel);
    }

    @FXML
    void txtOrdrQuty(javafx.scene.input.KeyEvent  event) {
        Regex.setTextColor(lk.ijse.CanteenDemo.Util.TextField.QTY,txtQty);
    }

    @FXML
    void txtTelAction(ActionEvent event) {
        try {
            FactoryEmployeeDTO employee = orderBO.getCustomer(Integer.parseInt(txtTel.getText()));
            if (employee != null){
                labelCustomerName.setText(employee.getName());
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Not Found !!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();


        }

    }
    @FXML
    void searchNameAction(ActionEvent event) {

    }


}
