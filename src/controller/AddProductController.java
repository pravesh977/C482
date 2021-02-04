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
public class AddProductController {
    Stage stage;
    Parent scene;

    /**
     * Imports searchPartsTextField from add_product.fxml file.
     */
    @FXML
    private TextField searchPartsTextField;

    /**
     * Imports idTextField from add_product.fxml file.
     */
    @FXML
    private TextField idTextField;

    /**
     * Imports nameTextField from add_product.fxml file.
     */
    @FXML
    private TextField nameTextField;

    /**
     * Imports invTextField from add_product.fxml file.
     */
    @FXML
    private TextField invTextField;

    /**
     * Imports priceTextField from add_product.fxml file.
     */
    @FXML
    private TextField priceTextField;

    /**
     * Imports maxTextField from add_product.fxml file.
     */
    @FXML
    private TextField maxTextField;

    /**
     * Imports minTextField from add_product.fxml file.
     */
    @FXML
    private TextField minTextField;

    /**
     * Imports addPartsTableView table from add_product.fxml file.
     */
    @FXML
    private TableView<Part> addPartsTableView;

    /**
     * Imports associatedPartsTableView table from add_product.fxml file.
     */
    @FXML
    private TableView<Part> associatedPartsTableView;

    /**
     * Imports partIdCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * Imports partNameCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Imports partInvCountCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, Integer> partInvCountCol;

    /**
     * Imports partPriceCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * Imports associatedPartIdCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;

    /**
     * Imports associatedPartNameCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    /**
     * Imports associatedPartInvCountCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInvCountCol;

    /**
     * Imports associatedPartPriceCol column from add_product.fxml file.
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;


    /**
     * This observable list holds array for associated parts of the product
     */
    private ObservableList<Part> initialAssociatedParts = FXCollections.observableArrayList();

    /**
     * This method initializes the tables with values. Parts table is initialized with all parts and associated table is populated with associated parts of the product.
     */
    public void initialize() {
        //adding data to total parts table
        addPartsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //adding parts to associated parts table
        associatedPartsTableView.setItems(initialAssociatedParts);
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvCountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * NumberFormatException.For input string: Caused when entered value is string in the search bar. Fixed by using catch to see if its string and then treating the entered value as a string for search.
     */
    @FXML
    public void handleSearchPartsClicked() {
        try {
            int searchedPartInteger = Integer.parseInt(searchPartsTextField.getText());
            Part searchedPart = Inventory.lookupPart(searchedPartInteger);
            if (searchedPart == null) {
                AlertMessageController.searchNotFound();
            } else {
                addPartsTableView.getSelectionModel().select(searchedPart);
                addPartsTableView.scrollTo(searchedPart);
            }
        } catch (NumberFormatException exp) {
            String searchedPartsString = searchPartsTextField.getText();
            ObservableList<Part> matchedPartsList = Inventory.lookupPart(searchedPartsString);
            if (matchedPartsList.size() == 0) {
                AlertMessageController.searchNotFound();
            } else {
                addPartsTableView.setItems(matchedPartsList);
            }
        }
        searchPartsTextField.clear();
    }

    /**
     * This method sends the selected part and adds it to the initialAssociatedTable by adding it to initialAssociatedParts ObservableList.
     */
    @FXML
    public void addPartsToAssociatedTable(MouseEvent event) {
        //check to see if nothing is selected
        if(addPartsTableView.getSelectionModel().getSelectedItem() != null) {
            //select the highlighted part object
            Part selectedPart = addPartsTableView.getSelectionModel().getSelectedItem();
            //pass the selected part object to the new initialized part array
            initialAssociatedParts.add(selectedPart);
        }
        else {
            AlertMessageController.errorNonSelection();
        }

    }

    /**
     * This method sends the selected part and removes it from the initialAssociatedParts observable list and then the associatedPartsTableView.
     */
    @FXML
    public void removeAssociatedPartFromTable() {
        //check to see if nothing is selected
        if(associatedPartsTableView.getSelectionModel().getSelectedItem() != null) {
            //select the highlighted part object
            Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Remove Part Alert");
            confirmAlert.setContentText("Are you sure you want to remove the association?");
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                //remove the selected part object after confirmation
                initialAssociatedParts.remove(selectedPart);
            }
        }
        else {
            AlertMessageController.errorNonSelection();
        }
    }

    /**
     * LOGICAL ERROR: The associated parts from the table would not get added to the selected product's associatedParts observable list. Corrected by creating a loop and then adding each part from the table/initialAssociatedParts to the associatedParts observable list.
     */
    @FXML
    public void saveNewProduct(MouseEvent event) throws IOException {
        try {
            int id = Inventory.getUniqueIdProduct();
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int totalInventory = Integer.parseInt(invTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            if (min >= max) {
                AlertMessageController.minMaxError();
            } else if ((totalInventory < min) || (totalInventory > max)) {
                AlertMessageController.inventoryInBetween();
            }
            else if (name.trim().isEmpty()) {
                AlertMessageController.nullName();
            } else {
                Product newProduct = new Product(id, name, price, totalInventory, min, max);
                Inventory.addProduct(newProduct);
                Inventory.incrementUniqueIdProduct();

                //start a loop and get every item from initialAssociatedParts observable list and add to the
                //associated part array of the product
                //Needs to be handled on click Add Button
                for (Part element : initialAssociatedParts) {
                    newProduct.addAssociatedPart(element);
                }

                //return to main screen after adding product
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
     * This returns the users to the main screen when clicked.
     */
    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
