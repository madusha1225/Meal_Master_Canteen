package lk.ijse.CanteenDemo.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text) {
        String filed = "";

        switch (textField) {
            case NAME:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case ADDRESS:
                filed = "^([A-z0-9]|[-\\,.@+]|\\\\s){4,}$";
                break;
            case CONTACT:
                filed = "^[0]([1-9]{2})([0-9]){7}$";
                break;
            case QTY:
                filed = "^[0-9]{1,5}$";
                break;
            case PRICE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case PASSWORD:
                filed = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%*?&]{8,}$";


                break;
            case ID:
                filed = "^([A-Z][0-9]{3})$";
                break;
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-focus-color: #00FF00;");
            return true;
        }else {
            textField.setStyle("-fx-border-color: red;-fx-border-radius: 5;-fx-border-width: 3;");
            return false;
        }
    }
}
