package lk.ijse.CanteenDemo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ObservableList<String> itemNames = FXCollections.observableArrayList(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Another Item");

    @Override
    public void start(Stage primaryStage) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        ListView<String> suggestionsListView = new ListView<>();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            suggestionsListView.setItems(getFilteredSuggestions(newValue));
        });

        // Handle item selection from the suggestion list
        suggestionsListView.setOnMouseClicked(event -> {
            String selectedItem = suggestionsListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                searchField.setText(selectedItem);
                suggestionsListView.getItems().clear(); // Clear suggestions after selection
            }
        });

        VBox root = new VBox(searchField, suggestionsListView);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<String> getFilteredSuggestions(String input) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        for (String itemName : itemNames) {
            if (itemName.toLowerCase().contains(input.toLowerCase())) {
                filteredList.add(itemName);
            }
        }
        return filteredList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
