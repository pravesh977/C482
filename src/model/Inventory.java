package model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.ArrayList;

public class Inventory {

    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    //remove this constructor?
    public Inventory() {
        //allParts = new ObservableList<Part>();
    }

    public static void addPart (Part newPart) {
        allParts.add(newPart); //FIX ME probable need to handle input exceptions
    }

    public static void addProduct (Product newProduct) {

    }

//    public static Part lookupPart (int partId) {
//        return somepart
//    }
//
//    public static Product lookupProduct (int productId) {
//        return someproduct
//    }
//
//    public static ObservableList<Part> lookupPart (String partName) {
//        return list of parts
//    }
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
//    public static ObservableList<Part> getAllParts () {
//        return some list
//    }
//
//    public static ObservableList<Product> getAllProducts () {
//        return some list
//    }
}
