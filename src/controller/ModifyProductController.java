package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.Optional;

/**
 * FUTURE ENHANCEMENT: The add associated parts table would only take a certain number of parts for a product. This is so that a product, lets say bike would not need 100 tires which the current app allows.
 */
public class ModifyProductController {
    Stage stage;
    Parent scene;

    /**
     * imports allPartsTableView Table from modify_product.fxml file
     */
    @FXML
    private TableView<Part> allPartsTableView;

    /**
     * imports associatedPartsTableView Table from modify_product.fxml file
     */
    @FXML
    private TableView<Part> associatedPartsTableView;

    /**
     * imports partIdCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * imports partNameCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * imports partInvCountCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, Integer> partInvCountCol;

    /**
     * imports partPriceCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * imports idProductTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField idProductTextField;

    /**
     * imports nameProductTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField nameProductTextField;

    /**
     * imports inventoryProductTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField inventoryProductTextField;

    /**
     * imports priceProductTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField priceProductTextField;

    /**
     * imports minProductTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField minProductTextField;

    /**
     * imports maxProductTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField maxProductTextField;

    /**
     * imports searchPArtsTextField TextField from modify_product.fxml file
     */
    @FXML
    private TextField searchPartsTextField;

    /**
     * imports associatedPartIdCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;

    /**
     * imports associatedPartNameCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    /**
     * imports associatedPartInvCountCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInvCountCol;

    /**
     * imports associatedPartPriceCol column from modify_product.fxml file
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;


    /**
     * this observable list holds array for associated parts of the selected product
     */
    private ObservableList<Part> associatedPartsObservableList = FXCollections.observableArrayList();

    /**
     * Initiating the all parts table and associated parts table
     */
    public void initialize() {
        //adding data to total parts table on initialization of the view
        allPartsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //adding parts to associated parts table
        associatedPartsTableView.setItems(associatedPartsObservableList);
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvCountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Handles the search from the text field and outputs the result
     */
    @FXML
    public void searchPartsClicked() {
        try {
            int searchedPartInteger = Integer.parseInt(searchPartsTextField.getText());
            Part searchedPart = Inventory.lookupPart(searchedPartInteger);
            if (searchedPart == null) {
                AlertMessageController.searchNotFound();
            } else {
                allPartsTableView.getSelectionModel().select(searchedPart);
                allPartsTableView.scrollTo(searchedPart);
            }
        } catch (NumberFormatException exp) {
            String searchedPartsString = searchPartsTextField.getText();
            ObservableList<Part> matchedPartsList = Inventory.lookupPart(searchedPartsString);
            if (matchedPartsList.size() == 0) {
                AlertMessageController.searchNotFound();
            } else {
                allPartsTableView.setItems(matchedPartsList);
            }
        }
        searchPartsTextField.clear();
    }


    //this populates the text fields from the main screen controller

    /**
     * LOGICAL ERROR: Could not get associated parts of the selected product to show up on the associated parts table. Fixed by getting
     * the selectedPart from the main screen controller and for each associated part of the product, adding it to associatedPartsObservableList
     * in a loop
     */
    public void passProductToModify(Product selectedProduct) {
        idProductTextField.setText(String.valueOf(selectedProduct.getId()));
        nameProductTextField.setText(selectedProduct.getName());
        inventoryProductTextField.setText(String.valueOf(selectedProduct.getStock()));
        priceProductTextField.setText(String.valueOf(selectedProduct.getPrice()));
        minProductTextField.setText(String.valueOf(selectedProduct.getMin()));
        maxProductTextField.setText(String.valueOf(selectedProduct.getMax()));

        //getting the selected product, getting its all associated parts and adding it to the Observable list
        for (Part element : selectedProduct.getAllAssociatedParts()) {
            associatedPartsObservableList.add(element);
        }
    }

    /**
     * Adding the selected part from the all parts table to the associated parts table
     */
    @FXML
    public void addToAssociate() {
        if(allPartsTableView.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();
            associatedPartsObservableList.add(selectedPart);
        }
        else {
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * Removing the selected part from the associated parts table
     */
    @FXML
    public void removeAssociatedParts() {
        if(associatedPartsTableView.getSelectionModel().getSelectedItem() != null) {
            //select the highlighted part object
            Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Remove Part Alert");
            confirmAlert.setContentText("Are you sure you want to remove the association?");
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                //pass the selected part object after confirmation
                associatedPartsObservableList.remove(selectedPart);
            }
        }
        else {
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * LOGICAL ERROR: Wrong product gets updated and gets an OutOfBounds exception. Caused by not getting the correct index of the product to be updated.
     * Fixed by adding a position with -1 on the updateProduct method in the Inventory class and incrementing it with 1 on each loop
     */
    @FXML
    public void modifyProductSave(MouseEvent event) throws IOException {
        try {
            int id = Integer.parseInt(idProductTextField.getText());
            String name = nameProductTextField.getText();
            double price = Double.parseDouble(priceProductTextField.getText());
            int totalInventory = Integer.parseInt(inventoryProductTextField.getText());
            int min = Integer.parseInt(minProductTextField.getText());
            int max = Integer.parseInt(maxProductTextField.getText());
            if (min >= max) {
                AlertMessageController.minMaxError();
            } else if ((totalInventory < min) || (totalInventory > max)) {
                AlertMessageController.inventoryInBetween();
            }
            else if (name.trim().isEmpty()) {
                AlertMessageController.nullName();
            } else {
                Product modifiedProduct = new Product(id, name, price, totalInventory, min, max);

                //for each item in observable list, add everything from that table/list to the
                // selected product's associated parts observable list
                for (int i = 0; i < associatedPartsObservableList.size(); i++) {
                    modifiedProduct.addAssociatedPart(associatedPartsObservableList.get(i));
                }
                //check for confirmation first
                //Inventory.updateProduct(id, modifiedProduct);
                modifyProductConfirmation(id, modifiedProduct);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException exp) {
            AlertMessageController.errorProduct();
        }
    }

    /**
     * Accepts the id and modifiedProduct from the modifyProductSave method and then sends it to the updateProduct method after user confirmation
     */
    public void modifyProductConfirmation(int id, Product modifiedProduct) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Modify Alert");
        confirmAlert.setContentText("Are you sure you want to modify it?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.updateProduct(id, modifiedProduct);
        }
    }

    /**
     * Returns the user to the main screen when cancel is pressed
     */
    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
