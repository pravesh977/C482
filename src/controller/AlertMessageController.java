package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMessageController {

    public static void errorPart() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error Adding Part");
        errorAlert.setContentText("Please make sure fields are not empty and formatted correctly");
        errorAlert.showAndWait();
    }

    public static void errorProduct() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error Adding Product");
        errorAlert.setContentText("Please make sure fields are not empty and formatted correctly");
        errorAlert.showAndWait();
    }

    public static void errorNonSelection() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Nothing selected");
        errorAlert.setContentText("Please select some item");
        errorAlert.showAndWait();
    }

    public static void minMaxError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Logical Min Max Error");
        errorAlert.setContentText("Minimum stock cannot be greater than Maximum stock");
        errorAlert.showAndWait();
    }

    public static void inventoryInBetween() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Inventory In between Error");
        errorAlert.setContentText("Total stock must be between min and max");
        errorAlert.showAndWait();
    }

//    public static void confirmModify() {
//        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmAlert.setTitle("Modification Alert");
//        confirmAlert.setContentText("Are you sure you want to make the changes?");
//        Optional<ButtonType> result = confirmAlert.showAndWait();
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//
//        }
//        confirmAlert.showAndWait();
//    }
}
