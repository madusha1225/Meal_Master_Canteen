package lk.ijse.CanteenDemo.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.CanteenDemo.bo.BOFactory;
import lk.ijse.CanteenDemo.bo.custom.AttendanceBO;
import lk.ijse.CanteenDemo.dto.FactoryEmployeeDTO;
import lk.ijse.CanteenDemo.view.tdm.AttendanceTm;

import java.awt.image.BufferedImage;
import java.util.*;

public class AttendanceFormController {

    @FXML
    private TableView<AttendanceTm> tblAttendance;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colIn;

    @FXML
    private TableColumn<?, ?> colOut;

    @FXML
    private Button btnScan;

    @FXML
    private Label labelId;

    @FXML
    private Label labelName;

    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBO(BOFactory.BOType.ATTENDANCE);

    public static FactoryEmployeeDTO employee;

    public void initialize() {
        setCellValueFactory();
        loadData();
    }

    private void loadData() {
        try {
            ObservableList<AttendanceTm> list = attendanceBO.getData();
            tblAttendance.setItems(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIn.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        colOut.setCellValueFactory(new PropertyValueFactory<>("outTime"));
    }

    @FXML
    void btnScanAction(ActionEvent event) {
        startScanning();
    }

    private void startScanning() {
        Webcam webcam = Webcam.getDefault();
        if (webcam != null) {
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.open();
            if (webcam.isOpen()){
                btnScan.setDisable(true);
            }

            new Thread(() -> {
                while (true) {
                    BufferedImage image = webcam.getImage();
                    if (image != null) {
                        try {
                            String qrCodeData = readQRCode(image);
                            if (qrCodeData != null) {
                                Platform.runLater(() -> {
                                    labelId.setText(qrCodeData);
                                    System.out.println(qrCodeData);
                                    try {
                                        employee = attendanceBO.getCustomerDetail(Integer.parseInt(qrCodeData));
                                        if (employee != null){
                                            labelName.setText(employee.getName());
                                            getData(employee);
                                        } else {
                                            labelId.setText("");
                                        }
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                                webcam.close();
                                if (!webcam.isOpen()){
                                    btnScan.setDisable(false);
                                }
                                return; // Stop scanning once QR code is found
                            }
                        } catch (NotFoundException e) {
                            // QR code not found in the current frame, continue scanning
                        }
                    }
                }
            }).start();
        } else {
            System.err.println("No webcam detected!");
        }
    }

    private void getData(FactoryEmployeeDTO employee) {
        try {
            if (attendanceBO.isInOrOut(employee.getId())){
                if (attendanceBO.updateOutTime(employee.getId())){
                    System.out.println("done");
                }
            } else {
                if (attendanceBO.saveInTime(employee.getId())){
                    System.out.println("done1");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        loadData();
    }

    private String readQRCode(BufferedImage image) throws NotFoundException {
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        MultiFormatReader reader = new MultiFormatReader();
        Result result = reader.decode(binaryBitmap, hints);

        return result.getText();
    }

}
