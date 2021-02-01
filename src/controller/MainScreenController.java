package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCountCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvCount;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField searchPartsField;

    @FXML
    private TextField searchProductsField;

    /**
     * Exception in Application start method
     * Corrected by matching the main_scree.fxml file's TableView id with field declaration in MainScreenController class
     */
    public void initialize() {

        //initializing main screen with test parts
        partsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //initializing main screen with test products
        productsTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    public void addNewPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/add_part.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @FXML
    public void modifyPart(MouseEvent event) throws IOException {

        //trying to extantiate controller
        //System.out.println(partsTableView.getSelectionModel().getSelectedItem().getName());
//        String modifyPartName = partsTableView.getSelectionModel().getSelectedItem().getName();
//        ModifyPartController controller = new ModifyPartController();
//        controller.handleModifyPartsSave();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/modify_part.fxml"));
        loader.load();

        ModifyPartController modPartCont = loader.getController();

        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        modPartCont.passPartsToModify(selectedPart);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        //scene = FXMLLoader.load(getClass().getResource("../view/modify_part.fxml"));
        Parent scene = loader.getRoot();
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

    //NumberFormatException.For input string: fixed by using catch to see if its string
    @FXML
    public void searchPartsAction() {
        //Part select searched working
        try {
            int searchedPartInteger = Integer.parseInt(searchPartsField.getText());
            Part searchedPart = Inventory.lookupPart(searchedPartInteger);
            partsTableView.getSelectionModel().select(searchedPart);
            partsTableView.scrollTo(searchedPart);
            searchPartsField.clear();
        }
        catch(NumberFormatException e) {
            String searchedPartsString = searchPartsField.getText();
            ObservableList<Part> matchedPartsList = Inventory.lookupPart(searchedPartsString);
            partsTableView.setItems(matchedPartsList);
            searchPartsField.clear();
        }
    }

    public void searchProductsAction() {
        try {
            int searchedProductInteger = Integer.parseInt(searchProductsField.getText());
            Product searchedProduct = Inventory.lookupProduct(searchedProductInteger);
            productsTableView.getSelectionModel().select(searchedProduct);
            productsTableView.scrollTo(searchedProduct);
            searchProductsField.clear();
        } catch(NumberFormatException e) {
            String searchedProductString = searchProductsField.getText();
            ObservableList<Product> matchedProductsList = Inventory.lookupProduct(searchedProductString);
            productsTableView.setItems(matchedProductsList);
            searchProductsField.clear();
        }
    }

    @FXML
    public void exitButton() {
        System.exit(0);
    }

}
