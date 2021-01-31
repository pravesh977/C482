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
        for(Part element : allParts)
        {
            if(element.getId() == partId) {
                return element;
            }
        }
        return null;
    }

    public static Product lookupProduct (int productId) {
        for(Product element : allProducts) {
            if(element.getId() == productId) {
                return element;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart (String partName) {
        ObservableList<Part> matchedParts = FXCollections.observableArrayList();
        for(Part element : allParts) {
            if (element.getName().toLowerCase().contains(partName.toLowerCase())) {
                matchedParts.add(element);
            }
        }
        return matchedParts;
    }

    public static ObservableList<Product> lookupProduct (String productName) {
        ObservableList<Product> matchedProducts = FXCollections.observableArrayList();
        for(Product element : allProducts) {
            if (element.getName().toLowerCase().contains(productName)){
                matchedProducts.add(element);
            }
        }
        return matchedProducts;
    }

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
