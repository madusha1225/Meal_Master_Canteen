package lk.ijse.CanteenDemo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.MainBO;

import java.io.IOException;

public class MainFormController {

    @FXML
    private Button btnDashboard;

    @FXML
    private ImageView dashboardImageView;

    @FXML
    private Button btnAttendece;

    @FXML
    private ImageView attendenceImageView;

    @FXML
    private Button btnMeal;

    @FXML
    private ImageView mealImageView;

    @FXML
    private Button btnOrder;

    @FXML
    private ImageView orderImageView;

    @FXML
    private Button btnCustomer;

    @FXML
    private ImageView customerImageView;

    @FXML
    private Button btnWorker;

    @FXML
    private ImageView workerImageView;

    @FXML
    private Button btnSupplier;

    @FXML
    private ImageView supplierImageView;

    @FXML
    private Button btnSetting;

    @FXML
    private ImageView settimgImageView;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane changePane;

    @FXML
    private Label labelOrderCount;

    @FXML
    private Label labelMealCount;


    @FXML
    private Label labelWorkersCount;


    @FXML
    private Label labelSuppliersCount;


    MainBO mainBO = (MainBO) BOFactory.getBO(BOFactory.BOType.MAIN);

    public void initialize(){
        defaultStyle();
        changeStyle(btnDashboard,dashboardImageView);
        setCount();
    }

    private void setCount() {
        try {
            labelOrderCount.setText(String.valueOf(mainBO.getOrderCount()));
            labelMealCount.setText(String.valueOf(mainBO.getMealCount()));
            labelWorkersCount.setText(String.valueOf(mainBO.getWorkerCount()));
            labelSuppliersCount.setText(String.valueOf(mainBO.getSupplierCount()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void orderAction(ActionEvent event) {
        defaultStyle();
        changeStyle(btnOrder,orderImageView);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/order_form.fxml"));
            Node node = loader.load();
            OrderFormController controller = loader.getController();
            controller.setMainFormController(this);
            changePane.getChildren().setAll(node);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void bnLogOutAction(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml")));
            Stage stage = (Stage) mainForm.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAttendeceAcion(ActionEvent event) {
        defaultStyle();
        changeStyle(btnAttendece,attendenceImageView);
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/attendence_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerAction(ActionEvent event) {
        defaultStyle();
        changeStyle(btnCustomer,customerImageView);
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboardAction(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml")));
            Stage stage = (Stage) mainForm.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnMealAction(ActionEvent event) {
        defaultStyle();
        changeStyle(btnMeal,mealImageView);
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/meal_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSettingAcion(ActionEvent event) {
        defaultStyle();
        changeStyle(btnSetting,settimgImageView);
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/setting_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierAction(ActionEvent event) {
        defaultStyle();
        changeStyle(btnSupplier,supplierImageView);
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnWorkerAction(ActionEvent event) {
        defaultStyle();
        changeStyle(btnWorker,workerImageView);
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/workers_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeStyle(Button button, ImageView view){
        button.setStyle("-fx-text-fill: #18E233;-fx-background-color: #C0C0C0;");
        Image image = null;

        if (view.equals(customerImageView)) {
            image = new Image("/icon/icons8-customer-80.png");
        } else if (view.equals(orderImageView)) {
            image = new Image("/icon/icons8-order-64 (1).png");
        } else if (view.equals(settimgImageView)) {
            image = new Image("/icon/icons8-setting-50 (1).png");
        } else if (view.equals(supplierImageView)) {
            image = new Image("/icon/icons8-supplier-32.png");
        } else if (view.equals(workerImageView)) {
            image = new Image("/icon/icons8-worker-80 (1).png");
        } else if (view.equals(dashboardImageView)) {
            image = new Image("/icon/icons8-dashboard-50 (1).png");
        } else if (view.equals(attendenceImageView)) {
            image = new Image("/icon/icons8-immigration-64 (1).png");
        } else if (view.equals(mealImageView)) {
            image = new Image("/icon/icons8-meal-50 (1).png");
        }
        view.setImage(image);
    }

    private void defaultStyle() {
        String style = "-fx-text-fill: #838996;-fx-background-color: white;";
        btnDashboard.setStyle(style);
        btnAttendece.setStyle(style);
        btnCustomer.setStyle(style);
        btnOrder.setStyle(style);
        btnMeal.setStyle(style);
        btnSupplier.setStyle(style);
        btnSetting.setStyle(style);
        btnWorker.setStyle(style);

        Image image ;

        image = new Image("/icon/icons8-customer-80 (1).png");
        customerImageView.setImage(image);
        image = new Image("/icon/icons8-order-64.png");
        orderImageView.setImage(image);
        image = new Image("/icon/icons8-meal-50.png");
        mealImageView.setImage(image);
        image = new Image("/icon/icons8-setting-50.png");
        settimgImageView.setImage(image);
        image = new Image("/icon/icons8-supplier-32 (1).png");
        supplierImageView.setImage(image);
        image = new Image("/icon/icons8-worker-80.png");
        workerImageView.setImage(image);
        image = new Image("/icon/icons8-dashboard-50.png");
        dashboardImageView.setImage(image);
        image = new Image("/icon/icons8-immigration-64.png");
        attendenceImageView.setImage(image);
    }

}
