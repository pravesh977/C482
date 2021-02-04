package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;

/**
 * FUTURE ENHANCEMENT: Adding form validation to see if the part already exists. If the name and machine id or company names match, throw an error.
 */
public class AddPartController {

    Stage stage;
    Parent scene;

    /**
     * Imports ToggleGroup from the add_part.fxml file
     */
    @FXML
    private ToggleGroup addPartToggleRadio;

    /**
     * Imports inHouseRadioButton from the add_part.fxml file
     */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * Imports outsourcedRadioButton from the add_part.fxml file
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Imports newPartIdTextField from the add_part.fxml file
     */
    @FXML
    private TextField newPartIdTextField;

    /**
     * Imports newPartNameTextField from the add_part.fxml file
     */
    @FXML
    private TextField newPartNameTextField;

    /**
     * Imports newPartInvTextField from the add_part.fxml file
     */
    @FXML
    private TextField newPartInvTextField;

    /**
     * Imports newPartPriceTextField from the add_part.fxml file
     */
    @FXML
    private TextField newPartPriceTextField;

    /**
     * Imports newPartMaxField from the add_part.fxml file
     */
    @FXML
    private TextField newPartMaxTextField;

    /**
     * Imports newPartMachineIdOrCompanyNameTextField from the add_part.fxml file
     */
    @FXML
    private TextField newPartMachineIdOrCompanyNameTextField;

    /**
     * Imports newPartMinTextField from the add_part.fxml file
     */
    @FXML
    private TextField newPartMinTextField;

    /**
     * Imports machineIdOrCompanyLabel from the add_part.fxml file
     */
    @FXML
    private Label machineIdOrCompanyLabel;


    /**
     * RUNTIME ERROR: NumberFormatException. Fixed by using try catch block to catch format errors in the user input field or when the fields are empty.
     */
    @FXML
    public void saveNewPart(MouseEvent event) throws IOException {
        //   Min should be less than Max; and Inv should be between those two values.
        try {
            int id = Inventory.getUniqueIdPart();
            String name = newPartNameTextField.getText();
            double price = Double.parseDouble(newPartPriceTextField.getText());
            //we can also use valueOf like so
            //int invNum = Integer.valueOf(newPartInvTextField.getText());
            int totalInventory = Integer.parseInt(newPartInvTextField.getText());
            int min = Integer.parseInt(newPartMinTextField.getText());
            int max = Integer.parseInt(newPartMaxTextField.getText());
            if (min >= max) {
                AlertMessageController.minMaxError();
            } else if ((totalInventory <= min) || (totalInventory >= max)) {
                AlertMessageController.inventoryInBetween();
            }
            else if (name.trim().isEmpty()) {
                AlertMessageController.nullName();
            } else {
                //insert condition for radio buttons
                if (inHouseRadioButton.isSelected()) {
                    //machineIdOrCompanyLabel.setText("Machine Id");
                    handleInhouseButton();
                    int machineId = Integer.parseInt(newPartMachineIdOrCompanyNameTextField.getText());
                    Part newPart = new InHouse(id, name, price, totalInventory, min, max, machineId);
                    Inventory.addPart(newPart);
                    System.out.println("it was created in machine");
                } else if (outsourcedRadioButton.isSelected()) {
                    //machineIdOrCompanyLabel.setText("Company Name");
                    handleOutsourcedButton();
                    String companyName = newPartMachineIdOrCompanyNameTextField.getText();
                    Part newPart = new Outsourced(id, name, price, totalInventory, min, max, companyName);
                    Inventory.addPart(newPart);
                    System.out.println("it was created in company name");
                }
                Inventory.incrementUniqueIdPart();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }

        } catch (NumberFormatException exp) {
            //System.out.println(exp);
            AlertMessageController.errorPart();
        }
    }

    /**
     * This returns the user to the main screen when Cancel button is clicked.
     */
    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This switches the text label to Machine Id if inhouse radio button is pressed.
     */
    public void handleInhouseButton() {
        machineIdOrCompanyLabel.setText("Machine Id");
    }

    /**
     * This switches the text label to Machine Id if outsourced radio button is pressed.
     */
    public void handleOutsourcedButton() {
        machineIdOrCompanyLabel.setText("Company Name");

    }
}
