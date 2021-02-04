package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.ArrayList;

/**
 * The Inventory Class which holds a list of parts and products
 */
public class Inventory {
    private static int uniqueIdPart = 7;
    private static int uniqueIdProduct = 4;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Returns a unique id for a new part
     */
    public static int getUniqueIdPart() {
        return uniqueIdPart;
    }

    /**
     * Increases the value of uniqueIdPart by 1
     */
    public static void incrementUniqueIdPart() {
        ++uniqueIdPart;
    }

    /**
     * Returns unique id for a product
     */
    public static int getUniqueIdProduct() {
        return uniqueIdProduct;
    }

    /**
     * Increases the value of uniqueIdProduct by 1
     */
    public static void incrementUniqueIdProduct() {
        ++uniqueIdProduct;
    }

    /**
     * Adds the newly created part to the allParts observable list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds the newly created product to the allProducts observable list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Receives the partId from the form and then starts a loop to see if the partId matches any of the list's id and returns the part
     */
    public static Part lookupPart(int partId) {
        //using for loop
//        for(int i = 0; i < allParts.size(); i++) {
//            if(allParts.get(i).getId() == partId) {
//                System.out.println("Match found which is " + partId + " for " + allParts.get(i).getName());
//                return allParts.get(i);
//            }
//
//        }
//        return null;

        //using Enhanced loop
        for (Part element : allParts) {
            if (element.getId() == partId) {
                return element;
            }
        }
        return null;
    }

    /**
     * Receives the productId from the form and then starts a loop to see if the productId matches any of the list's id and returns the product
     */
    public static Product lookupProduct(int productId) {
        for (Product element : allProducts) {
            if (element.getId() == productId) {
                return element;
            }
        }
        return null;
    }

    /**
     * Receives the partName from the form and then starts a loop to see if the productId matches any of the list's names and returns the matched part array
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchedParts = FXCollections.observableArrayList();
        for (Part element : allParts) {
            if (element.getName().toLowerCase().contains(partName.toLowerCase())) {
                matchedParts.add(element);
            }
        }
        return matchedParts;
    }

    /**
     * Receives the productName from the form and then starts a loop to see if the productName matches any of the list's names and returns the matched product array
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchedProducts = FXCollections.observableArrayList();
        for (Product element : allProducts) {
            if (element.getName().toLowerCase().contains(productName)) {
                matchedProducts.add(element);
            }
        }
        return matchedProducts;
    }

    /**
     * Receives the index and selectedPart object from the form and then starts a loop to see if the index matches any of the list's id and updates the part
     */
    public static void updatePart(int index, Part selectedPart) {
        int position = -1;
        for (Part element : allParts) {
            position++;
            if (element.getId() == index) {
                allParts.set(position, selectedPart);
            }
        }
    }

    /**
     * Receives the index and newProduct object from the form and then starts a loop to see if the index matches any of the list's id and updates the product
     */
    public static void updateProduct(int index, Product newProduct) {
        int position = -1;
        for (Product element : allProducts) {
            position++;
            if (element.getId() == index) {
                allProducts.set(position, newProduct);
            }
        }
    }

    /**
     * Receives the selectedPart object from the form and finds and deletes the part
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Receives the selectedProduct object from the form and finds and deletes the product
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Returns all the parts from the allParts observable list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns all the products from the allProducts observable list
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
