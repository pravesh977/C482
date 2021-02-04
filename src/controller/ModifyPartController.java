package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.util.Optional;

/**
 * FUTURE ENHANCEMENT: Application will show warning if all new modified part values match an existing part. This will make each part unique other than the id.
 */
public class ModifyPartController {
    Stage stage;
    Parent scene;

    /**
     * imports nameLabel label from modify_part.fxml file
     */
    @FXML
    private Label nameLabel;

    /**
     * imports machineCompanyLabel label from modify_part.fxml file
     */
    @FXML
    private Label machineCompanyLabel;

    /**
     * imports inHouseRadio button from modify_part.fxml file
     */
    @FXML
    private RadioButton inHouseRadio;

    /**
     * imports outsourcedRaio button from modify_part.fxml file
     */
    @FXML
    private RadioButton outsourcedRadio;

    /**
     * imports modifyIdTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyIdTextField;

    /**
     * imports modifyNameTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyNameTextField;

    /**
     * imports modifyInvTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyInvTextField;

    /**
     * imports modifyPriceTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyPriceTextField;

    /**
     * imports modifyMaxTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyMaxTextField;

    /**
     * imports modifyMinTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyMinTextField;

    /**
     * imports modifyMachineOrCompanyTextField TextField from modify_part.fxml file
     */
    @FXML
    private TextField modifyMachineOrCompanyTextField;

    /**
     * LOGICAL ERROR: Could not set the values of the inHouse or outsourced from the selected part. Fixed by checking if the part sent is
     * inHouse or outsourced by using instanceof operator and then casting it to appropriate type(inHouse or Outsourced)
     */
    @FXML
    public void passPartsToModify(Part modifyPart) {
        modifyIdTextField.setText(String.valueOf(modifyPart.getId()));
        modifyNameTextField.setText(modifyPart.getName());
        modifyInvTextField.setText(String.valueOf(modifyPart.getStock()));
        modifyPriceTextField.setText(String.valueOf(modifyPart.getPrice()));
        modifyMaxTextField.setText(String.valueOf(modifyPart.getMax()));
        modifyMinTextField.setText(String.valueOf(modifyPart.getMin()));
        //checking to see what type of object it is using instance of operator(whether inhouse or outsourced)
        if (modifyPart instanceof InHouse) {
            System.out.println("its inhouse");
            //this sets the radio button to inhouse
            inHouseRadio.fire();
            //machineCompanyLabel.setText("Machine Id"); replaced with existing method
            changeLabelToInHouse();
            InHouse inHousePart = (InHouse) (modifyPart);
            modifyMachineOrCompanyTextField.setText(String.valueOf(inHousePart.getMachineId()));
        } else if (modifyPart instanceof Outsourced) {
            System.out.println("its outsourced");
            //this sets the radio button to outsourced
            outsourcedRadio.fire();
            //machineCompanyLabel.setText("Company Name");
            changeLabelToOutsourced();
            Outsourced outSourcedPart = (Outsourced) (modifyPart);
            modifyMachineOrCompanyTextField.setText(outSourcedPart.getCompanyName());
        }
    }

    /**
     * RUNTIME ERROR: java.lang.IndexOutOfBoundsException: Caused when selected part is attempted to modify. Fixed by adding an index of -1 to the updatePart
     * method in Inventory class and updating it by 1 in the loop to find the correct index to be updated.
     */
    @FXML
    public void modifyPartsSave(MouseEvent event) throws IOException {
        try {
            int id = Integer.parseInt(modifyIdTextField.getText());
            String name = modifyNameTextField.getText();
            double price = Double.parseDouble(modifyPriceTextField.getText());
            int totalInventory = Integer.parseInt(modifyInvTextField.getText());
            int min = Integer.parseInt(modifyMinTextField.getText());
            int max = Integer.parseInt(modifyMaxTextField.getText());
            if (min >= max) {
                AlertMessageController.minMaxError();
            } else if ((totalInventory < min) || (totalInventory > max)) {
                AlertMessageController.inventoryInBetween();
            }
            else if (name.trim().isEmpty()) {
                AlertMessageController.nullName();
            } else {
                //insert condition for radio buttons
                if (inHouseRadio.isSelected()) {
                    machineCompanyLabel.setText("Machine Id");
                    int machineId = Integer.parseInt(modifyMachineOrCompanyTextField.getText());
                    Part modifiedPart = new InHouse(id, name, price, totalInventory, min, max, machineId);
                    //int searchedIndex = Inventory.lookupPart(id).getId();
                    //Inventory.updatePart(id, modifiedPart); using modifyconfirmationpart instead
                    modifyPartConfirmation(id, modifiedPart);
                    System.out.println(id + " is the id");
                    //System.out.println(searchedIndex + " is the searched index");
                } else if (outsourcedRadio.isSelected()) {
                    machineCompanyLabel.setText("Company Name");
                    String companyName = modifyMachineOrCompanyTextField.getText();
                    Part modifiedPart = new Outsourced(id, name, price, totalInventory, min, max, companyName);
                    //int searchedIndex = Inventory.lookupPart(id).getId();
                    //Inventory.updatePart(id, modifiedPart);
                    modifyPartConfirmation(id, modifiedPart);
                    System.out.println(id + " is the id");
                    //System.out.println(searchedIndex + " is the searched index");
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (NumberFormatException exp) { // | IndexOutOfBoundsException exp) {
            AlertMessageController.errorPart();
        }
    }

    /**
     * This returns the users to the main screen when cancel button is pressed.
     */
    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This changes the label to inHouse when pressed
     */
    public void changeLabelToInHouse() {
        machineCompanyLabel.setText("Machine Id");
    }

    /**
     * This changes the label to outsourced when pressed
     */
    public void changeLabelToOutsourced() {
        machineCompanyLabel.setText("Company Name");
    }

    /**
     * This accepts the index and Part modifiedPart from the modifyPartsSave method and sends it to updatePArt method if the user accepts to make changes
     */
    @FXML
    public void modifyPartConfirmation(int id, Part modifiedPart) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Modify Alert");
        confirmAlert.setContentText("Are you sure you want to modify it?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.updatePart(id, modifiedPart);
        }
    }
}
