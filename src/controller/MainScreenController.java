package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {

    Stage stage;
    Parent scene;

    @FXML
    public void addNewPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/add_part.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void modifyPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/modify_part.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void addNewProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/add_product.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void modifyProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/modify_product.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void exitButton() {
        System.exit(0);
    }

//    public void initialize() {
//        System.out.println("who boy");
//    }
}
