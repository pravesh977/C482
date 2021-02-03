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

public class AddPartController {

    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup addPartToggleRadio;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField newPartIdTextField;

    @FXML
    private TextField newPartNameTextField;

    @FXML
    private TextField newPartInvTextField;

    @FXML
    private TextField newPartPriceTextField;

    @FXML
    private TextField newPartMaxTextField;

    @FXML
    private TextField newPartMachineIdOrCompanyNameTextField;

    @FXML
    private TextField newPartMinTextField;

    @FXML
    private Label machineIdOrCompanyLabel;


    /**RUNTIME ERROR: NumberFormatException.

     Fixed by using try catch block to catch format errors in the user input field*/
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
            if(min >=  max) {
                AlertMessageController.minMaxError();
            }
            else if((totalInventory < min) || (totalInventory > max)) {
                AlertMessageController.inventoryInBetween();
            }
            else {
                //insert condition for radio buttons
                if (inHouseRadioButton.isSelected()) {
                    machineIdOrCompanyLabel.setText("Machine Id");
                    int machineId = Integer.parseInt(newPartMachineIdOrCompanyNameTextField.getText());
                    Part newPart = new InHouse(id, name, price, totalInventory, min, max, machineId);
                    Inventory.addPart(newPart);
                    System.out.println("it was created in machine");
                } else if (outsourcedRadioButton.isSelected()) {
                    machineIdOrCompanyLabel.setText("Company Name");
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

        } catch(NumberFormatException exp) {
            //System.out.println(exp);
            AlertMessageController.errorPart();
        }
    }

    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void handleAddPartsRadioButton() {
        //System.out.println(addPartToggleRadio.selectedToggleProperty().addListener());
    }

    public void handleInhouseButton() {
        machineIdOrCompanyLabel.setText("Machine Id");
    }

    public void handleOutsourcedButton() {
        machineIdOrCompanyLabel.setText("Company Name");

    }
}
