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


    public void initialize() {
        //modifyNameTextField.setText("tits");
        //nameLabel.setText("bush");
    }
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
            machineCompanyLabel.setText("Machine Id");
            InHouse inHousePart = (InHouse)(modifyPart);
            modifyMachineOrCompanyTextField.setText(String.valueOf(inHousePart.getMachineId()));

        } else if (modifyPart instanceof Outsourced) {
            System.out.println("its outsourced");
            machineCompanyLabel.setText("Company Name");
            Outsourced outSourcedPart = (Outsourced)(modifyPart);
            modifyMachineOrCompanyTextField.setText(outSourcedPart.getCompanyName());
        }
    }

    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void changeLabelToInhouse() {
        machineCompanyLabel.setText("Machine Id");
    }

    public void changeLabelToOutsourced() {
        machineCompanyLabel.setText("Company Name");
    }

//    public void changeRadioAutomatic() {
//        if(inHouseRadio.isSelected()) {
//            machineCompanyLabel.setText("Machine Id");
//        }
//        else if(outsourcedRadio.isSelected()) {
//            machineCompanyLabel.setText("Company Name");
//        }
//    }
}
