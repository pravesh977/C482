package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * FUTURE ENHANCEMENT: Highlight TextFields. Instead of only showing dialog box with messages, the app will highlight the TextField which caused the error.
 */
public class AlertMessageController {
    /**
     * This opens a dialog box if the user leaves text fields empty or if the formats entered are not correct
     */
    public static void errorPart() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error Adding Part");
        errorAlert.setContentText("Please make sure fields are not empty and formatted correctly");
        errorAlert.showAndWait();
    }

    /**
     * This opens a dialog box if the user leaves text fields empty or if the formats entered are not correct
     */
    public static void errorProduct() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error Adding Product");
        errorAlert.setContentText("Please make sure fields are not empty and formatted correctly");
        errorAlert.showAndWait();
    }

    /**
     * This displays an error dialog box when the user tries to click on delete or modify without selecting an item first
     */
    public static void errorNonSelection() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Nothing selected");
        errorAlert.setContentText("Please select some item");
        errorAlert.showAndWait();
    }

    /**
     * This displays a dialog box error if minimum is greater or equal to the maximum value entered in stock
     */
    public static void minMaxError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Logical Min Max Error");
        errorAlert.setContentText("Minimum stock cannot be greater than Maximum stock");
        errorAlert.showAndWait();
    }

    /**
     * This displays a dialog box error if inventory amount is not in between minimum and maximum value
     */
    public static void inventoryInBetween() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Inventory In between Error");
        errorAlert.setContentText("Total stock must be between min and max");
        errorAlert.showAndWait();
    }

    /**
     * This displays a dialog box error if the search returns null or empty array
     */
    public static void searchNotFound() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Search Empty");
        errorAlert.setContentText("No match found");
        errorAlert.showAndWait();
    }

    /**
     * This displays a dialog box alerting the user that the selected product has associated parts and need to be removed if they want to delete the product
     */
    public static void productAssociatedPartNotEmptyError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Associated Parts not Empty");
        errorAlert.setContentText("Product cannot be deleted. Please remove associated parts first.");
        errorAlert.showAndWait();
    }

    /**Displays a dialog box if the user tries to submit a form with no value in the string text field*/
    public static void nullName() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Empty Null Field");
        errorAlert.setContentText("The name field cannot be empty");
        errorAlert.showAndWait();
    }
}
