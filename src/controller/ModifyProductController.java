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

public class ModifyProductController {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Part> allPartsTableView;

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
    private TextField idProductTextField;

    @FXML
    private TextField nameProductTextField;

    @FXML
    private TextField inventoryProductTextField;

    @FXML
    private TextField priceProductTextField;

    @FXML
    private TextField minProductTextField;

    @FXML
    private TextField maxProductTextField;

    @FXML
    private TextField searchPartsTextField;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;

    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartInvCountCol;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;


    //this observable list holds array for associated parts
    private ObservableList<Part> associatedPartsObservableList = FXCollections.observableArrayList();

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

    @FXML
    public void searchPartsClicked() {
        try {
            int searchedPartInteger = Integer.parseInt(searchPartsTextField.getText());
            Part searchedPart = Inventory.lookupPart(searchedPartInteger);
            allPartsTableView.getSelectionModel().select(searchedPart);
            allPartsTableView.scrollTo(searchedPart);
            searchPartsTextField.clear();
        } catch (NumberFormatException exp) {
            String searchedPartsString = searchPartsTextField.getText();
            ObservableList<Part> matchedPartsList = Inventory.lookupPart(searchedPartsString);
            allPartsTableView.setItems(matchedPartsList);
            searchPartsTextField.clear();
        }
    }


    //this populates the text fields from the main screen controller
    public void passProductToModify(Product selectedProduct) {
        idProductTextField.setText(String.valueOf(selectedProduct.getId()));
        nameProductTextField.setText(selectedProduct.getName());
        inventoryProductTextField.setText(String.valueOf(selectedProduct.getStock()));
        priceProductTextField.setText(String.valueOf(selectedProduct.getPrice()));
        minProductTextField.setText(String.valueOf(selectedProduct.getMin()));
        maxProductTextField.setText(String.valueOf(selectedProduct.getMax()));

        //getting the selected product, gettings its all associated parts and adding it to the Observable list
        for (Part element : selectedProduct.getAllAssociatedParts()) {
            associatedPartsObservableList.add(element);
        }
    }


    @FXML
    public void addToAssociate() {
        Part selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();
        associatedPartsObservableList.add(selectedPart);
    }

    @FXML
    public void removeAssociatedParts() {
        //select the highlighted part object
        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
        //pass the selected part object to the new initialized part array
        associatedPartsObservableList.remove(selectedPart);
    }

    /**
     * N
     *
     * @param event
     * @throws IOException
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

    public void modifyProductConfirmation(int id, Product modifiedProduct) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Modify Alert");
        confirmAlert.setContentText("Are you sure you want to modify it?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.updateProduct(id, modifiedProduct);
        }
    }

    @FXML
    public void cancelPressed(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
