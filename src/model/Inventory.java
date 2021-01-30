package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.ArrayList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //remove this constructor?
//    public Inventory() {
//        allParts = new ObservableList<Part>();
//    }

    public static void addPart (Part newPart) {
        allParts.add(newPart); //FIX ME probable need to handle input exceptions
    }

    public static void addProduct (Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart (int partId) {
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getId() == partId) {
                System.out.println("Match found which is " + partId + " for " + allParts.get(i).getName());
                return allParts.get(i);
            }
        }
        return allParts.get(0); //Fix me what happens if no part found?
    }
//
//    public static Product lookupProduct (int productId) {
//        return someproduct
//    }
//
    public static ObservableList<Part> lookupPart (String partName) {
        return allParts;
    }
//
//    public static ObservableList<Product> lookupProduct (String productName) {
//        return list of products
//    }
//
//    public static void updatePart (int index, Part selectedPart) {
//
//    }
//
//    public static void updateProduct (int index, Product newProduct) {
//
//    }
//
//    public static boolean deletePart (Part selectedPart) {
//        return ture or false;
//    }
//
//    public static boolean deleteProduct (Product selectedProduct) {
//        return true or false;
//    }
//
    public static ObservableList<Part> getAllParts () {
        return allParts;
    }
//
    public static ObservableList<Product> getAllProducts () {
        return allProducts;
    }
}
