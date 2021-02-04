package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import javax.security.sasl.SaslException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * FUTURE ENHANCEMENT: Adding dynamic search capability for the parts and products. The parts and products tables would update based on each key pressed instead of having to click on search button or pressing the enter key.
 */
public class MainScreenController {

    Stage stage;
    Parent scene;

    /**
     * Imports partsTableView from the main_screen.fxml file
     */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * Imports partIdCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * Imports partNameCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Imports partInvCountCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Part, Integer> partInvCountCol;

    /**
     * Imports partPriceCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * Imports productsTableView table from the main_screen.fxml file
     */
    @FXML
    private TableView<Product> productsTableView;

    /**
     * Imports productIdCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Product, Integer> productIdCol;

    /**
     * Imports productNameCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * Imports productInvCount column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Product, Integer> productInvCount;

    /**
     * Imports productPriceCol column from the main_screen.fxml file
     */
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * Imports searchPartsField text field from the main_screen.fxml file
     */
    @FXML
    private TextField searchPartsField;

    /**
     * Imports searchProductsField text field from the main_screen.fxml file
     */
    @FXML
    private TextField searchProductsField;

    /**
     * Exception in Application start method. Corrected by matching the main_scree.fxml file's TableView id with field importing in MainScreenController class.
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

    /**
     * This method opens a new window where users can add new parts.
     */
    @FXML
    public void addNewPart(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/add_part.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * RUNTIME ERROR: NullPointerException: Caused by clicking on modify button without selecting an item. Corrected by catching the exception and showing an error dialog to select an item.
     */
    @FXML
    public void modifyPart(MouseEvent event) throws IOException {

        //trying to extantiate controller, cant do it this way
        //System.out.println(partsTableView.getSelectionModel().getSelectedItem().getName());
//        String modifyPartName = partsTableView.getSelectionModel().getSelectedItem().getName();
//        ModifyPartController controller = new ModifyPartController();
//        controller.handleModifyPartsSave();

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/modify_part.fxml"));
            loader.load();

            ModifyPartController modPartCont = loader.getController();
            Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            modPartCont.passPartsToModify(selectedPart);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            //scene = FXMLLoader.load(getClass().getResource("../view/modify_part.fxml"));
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
//            Displaying errors in console
//            System.out.println("something happened");
//            System.out.println("Exception is " + e);
//            System.out.println("Exception message " + e.getMessage());
//            System.out.println("Exception case is " + e.getCause());

            //Displaying errors in an alert window
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Error selection");
//            alert.setContentText(e.getMessage());
//            System.out.println("Exception message " + e);
//            alert.showAndWait();

//            Displaying error using AlertMessageController class
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * This method grabs the selected part, saves it in a Part variable and sends it to deletePart method in Inventory class where it deletes the part.
     */
    @FXML
    public void deleteParts() {
        if (partsTableView.getSelectionModel().getSelectedItem() != null) {
            Part partForDeletion = partsTableView.getSelectionModel().getSelectedItem();
//        int selectedIndex = partsTableView.getSelectionModel().getSelectedItem().getId();
//        Inventory.lookupPart(selectedIndex);
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Deletion Alert");
            confirmAlert.setContentText("Are you sure you want to delete it?");
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(partForDeletion);
            }
        } else {
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * This method grabs the selected product, saves it in a product variable and sends it to deleteProduct method in Inventory class where it deletes the product.
     */
    @FXML
    public void deleteProduct() {
        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            Product productForDeletion = productsTableView.getSelectionModel().getSelectedItem();
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Deletion Alert");
            confirmAlert.setContentText("Are you sure you want to delete it?");
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                //display dialog box saying product cant be deleted if it has associated parts
                if (productForDeletion.getAllAssociatedParts().size() != 0) {
                    AlertMessageController.productAssociatedPartNotEmptyError();
                } else {
                    //this deletes the selected product if ok is selected
                    Inventory.deleteProduct(productForDeletion);
                }
            }
        } else {
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * This opens a window in which users can add new product.
     */
    @FXML
    public void addNewProduct(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/add_product.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * RUNTIME ERROR: NullPointerException: Caused by clicking on modify button without selecting an item. Corrected by catching the exception and showing an error dialog to select an item.
     */
    @FXML
    public void modifyProduct(MouseEvent event) throws IOException {

//        System.out.println(Inventory.getAllProducts().get(3).getAllAssociatedParts().get(0).getName());
//        System.out.println(Inventory.getAllProducts().get(3).getAllAssociatedParts().get(1).getName());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/modify_product.fxml"));
            loader.load();

            ModifyProductController modProdCont = loader.getController();
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            modProdCont.passProductToModify(selectedProduct);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException exp) {
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * NumberFormatException.For input string: Caused when entered value is string in the search bar. Fixed by using catch to see if its string and then treating the entered value as a string for search.
     */
    @FXML
    public void searchPartsAction() {
        //Part select searched working
        try {
            int searchedPartInteger = Integer.parseInt(searchPartsField.getText());
            Part searchedPart = Inventory.lookupPart(searchedPartInteger);
            if (searchedPart == null) {
                AlertMessageController.searchNotFound();
            } else {
                partsTableView.getSelectionModel().select(searchedPart);
                partsTableView.scrollTo(searchedPart);
            }
        } catch (NumberFormatException e) {
            String searchedPartsString = searchPartsField.getText();
            ObservableList<Part> matchedPartsList = Inventory.lookupPart(searchedPartsString);
            if (matchedPartsList.size() == 0) {
                AlertMessageController.searchNotFound();
            } else {
                partsTableView.setItems(matchedPartsList);
            }
        }
        searchPartsField.clear();
    }

    /**
     * NumberFormatException.For input string: Caused when entered value is string in the search bar. Fixed by using catch to see if its string and then treating the entered value as a string for search.
     */
    public void searchProductsAction() {
        try {
            int searchedProductInteger = Integer.parseInt(searchProductsField.getText());
            Product searchedProduct = Inventory.lookupProduct(searchedProductInteger);
            if (searchedProduct == null) {
                AlertMessageController.searchNotFound();
            } else {
                productsTableView.getSelectionModel().select(searchedProduct);
                productsTableView.scrollTo(searchedProduct);
            }
        } catch (NumberFormatException e) {
            String searchedProductString = searchProductsField.getText();
            ObservableList<Product> matchedProductsList = Inventory.lookupProduct(searchedProductString);
            if (matchedProductsList.size() == 0) {
                AlertMessageController.searchNotFound();
            } else {
                productsTableView.setItems(matchedProductsList);
            }
        }
        searchProductsField.clear();
    }

    /**
     * This exits the program when users click on exit.
     */
    @FXML
    public void exitButton() {
        System.exit(0);
    }

}
