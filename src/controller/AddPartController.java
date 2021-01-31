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
    private int idCount;

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

    public void initialize() {
        //Fix me disable button and set id here
        //int sizeOfParts = Inventory.getAllParts().size();
        //newPartIdTextField.setText(String.valueOf(sizeOfParts + 1));
        //newPartIdTextField.setText(String.valueOf(idCount));
    }

    @FXML
    public void saveNewPart(MouseEvent event) throws IOException {
        int id = Integer.parseInt(newPartIdTextField.getText());
        String name = newPartNameTextField.getText();
        double price = Double.parseDouble(newPartPriceTextField.getText());
        //we can also use valueOf like so
        //int invNum = Integer.valueOf(newPartInvTextField.getText());
        int totalInventory = Integer.parseInt(newPartInvTextField.getText());
        int min = Integer.parseInt(newPartMinTextField.getText());
        int max = Integer.parseInt(newPartMaxTextField.getText());

        //insert condition for radio buttons
        if(inHouseRadioButton.isSelected()) {
            int machineId = Integer.parseInt(newPartMachineIdOrCompanyNameTextField.getText());
            Part newPart = new InHouse(id, name, price, totalInventory, min, max, machineId);
            Inventory.addPart(newPart);
            System.out.println("it was created in machine");
        } else if(outsourcedRadioButton.isSelected()) {
            String companyName = newPartMachineIdOrCompanyNameTextField.getText();
            Part newPart = new Outsourced(id, name,price, totalInventory, min, max, companyName);
            Inventory.addPart(newPart);
            System.out.println("it was created in company name");
        }
        idCount++;
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
