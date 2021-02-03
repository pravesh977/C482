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



public class ModifyPartController {
    Stage stage;
    Parent scene;

    @FXML
    private Label nameLabel;
    @FXML
    private Label machineCompanyLabel;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private TextField modifyIdTextField;

    @FXML
    private TextField modifyNameTextField;

    @FXML
    private TextField modifyInvTextField;

    @FXML
    private TextField modifyPriceTextField;

    @FXML
    private TextField modifyMaxTextField;

    @FXML
    private TextField modifyMinTextField;

    @FXML
    private TextField modifyMachineOrCompanyTextField;

    @FXML
    public void passPartsToModify(Part modifyPart) {
        modifyIdTextField.setText(String.valueOf(modifyPart.getId()));
        modifyNameTextField.setText(modifyPart.getName());
        modifyInvTextField.setText(String.valueOf(modifyPart.getStock()));
        modifyPriceTextField.setText(String.valueOf(modifyPart.getPrice()));
        modifyMaxTextField.setText(String.valueOf(modifyPart.getMax()));
        modifyMinTextField.setText(String.valueOf(modifyPart.getMin()));
        //checking to see what type of object it is using instance of operator(whether inhouse or outsourced)
        if(modifyPart instanceof InHouse) {
            System.out.println("its inhouse");
            //this sets the radio button to inhouse
            inHouseRadio.fire();
            //machineCompanyLabel.setText("Machine Id"); replaced with existing method
            changeLabelToInHouse();
            InHouse inHousePart = (InHouse)(modifyPart);
            modifyMachineOrCompanyTextField.setText(String.valueOf(inHousePart.getMachineId()));

        } else if (modifyPart instanceof Outsourced) {
            System.out.println("its outsourced");
            //this sets the radio button to outsourced
            outsourcedRadio.fire();
            //machineCompanyLabel.setText("Company Name");
            changeLabelToOutsourced();
            Outsourced outSourcedPart = (Outsourced)(modifyPart);
            modifyMachineOrCompanyTextField.setText(outSourcedPart.getCompanyName());
        }
    }

    /**Caused by: java.lang.IndexOutOfBoundsException: Index 2 out of bounds for length 2
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
            } else {
                //insert condition for radio buttons
                if (inHouseRadio.isSelected()) {
                    machineCompanyLabel.setText("Machine Id");
                    int machineId = Integer.parseInt(modifyMachineOrCompanyTextField.getText());
                    Part modifiedPart = new InHouse(id, name, price, totalInventory, min, max, machineId);
                    //int searchedIndex = Inventory.lookupPart(id).getId();
                    Inventory.updatePart(id, modifiedPart);
                    System.out.println(id + " is the id");
                    //System.out.println(searchedIndex + " is the searched index");
                } else if (outsourcedRadio.isSelected()) {
                    machineCompanyLabel.setText("Company Name");
                    String companyName = modifyMachineOrCompanyTextField.getText();
                    Part modifiedPart = new Outsourced(id, name, price, totalInventory, min, max, companyName);
                    //int searchedIndex = Inventory.lookupPart(id).getId();
                    Inventory.updatePart(id, modifiedPart);
                    System.out.println(id + " is the id");
                    //System.out.println(searchedIndex + " is the searched index");
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch(NumberFormatException exp) { // | IndexOutOfBoundsException exp) {
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

    public void changeLabelToInHouse() {
        machineCompanyLabel.setText("Machine Id");
    }

    public void changeLabelToOutsourced() {
        machineCompanyLabel.setText("Company Name");
    }

}
