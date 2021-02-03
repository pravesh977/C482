package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;

public class AddProductController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField searchPartsTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField invTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TableView<Part> addPartsTableView;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCountCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;

    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartInvCountCol;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;

    @FXML
    private Button saveProductButton;

    ObservableList<Part> initialAssociatedParts = FXCollections.observableArrayList();


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

    /**NumberFormatException when entering string on searchbar
     *
     */
    @FXML
    public void handleSearchPartsClicked() {
        try {
            int searchedPartInteger = Integer.parseInt(searchPartsTextField.getText());
            Part searchedPart = Inventory.lookupPart(searchedPartInteger);
            addPartsTableView.getSelectionModel().select(searchedPart);
            addPartsTableView.scrollTo(searchedPart);
            searchPartsTextField.clear();
        } catch (NumberFormatException exp) {
            String searchedPartsString = searchPartsTextField.getText();
            ObservableList<Part> matchedPartsList = Inventory.lookupPart(searchedPartsString);
            addPartsTableView.setItems(matchedPartsList);
            searchPartsTextField.clear();
        }
    }

    @FXML
    public void addPartsToAssociatedTable(MouseEvent event) {
        //select the highlighted part object
        Part selectedPart = addPartsTableView.getSelectionModel().getSelectedItem();
        //pass the selected part object to the new initialized part array
        initialAssociatedParts.add(selectedPart);
    }

    @FXML
    public void removeAssociatedPartFromTable() {
        //select the highlighted part object
        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
        //pass the selected part object to the new initialized part array
        initialAssociatedParts.remove(selectedPart);
    }

    /**NumberFormatException
     * Product added after showing form error
     *
     * @param event
     * @throws IOException
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
            } else {
                Product newProduct = new Product(id, name, price, totalInventory, min, max);
                Inventory.addProduct(newProduct);
                Inventory.incrementUniqueIdProduct();

                //start a loop and get every item from initialAssociatedParts and add to the
                //associated part of the array
                for(Part element : initialAssociatedParts) {
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

    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
