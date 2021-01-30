package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;

public class ModifyProductController {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Part> allPartsView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCountCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

//    @FXML
//    private TableView<?> AssociatedPartsView;
//
//    @FXML
//    private TableColumn<?, ?> associatedPartIdCol;
//
//    @FXML
//    private TableColumn<?, ?> associatedPartNameCol;
//
//    @FXML
//    private TableColumn<?, ?> associatedPartInvCountCol;
//
//    @FXML
//    private TableColumn<?, ?> associatedPartPriceCol;

    public void initialize() {
        //adding data to total parts table
        allPartsView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //adding parts to associated parts table
        //associatedPartsView.setItems();
    }

    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
