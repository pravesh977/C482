package model;

import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart (Part newPart) {
        allParts.add(newPart); //FIX ME probable need to handle input exceptions
    }

    public void addProduct (Product newProduct) {

    }

//    public Part lookupPart (int partId) {
//        return somepart
//    }
//
//    public Product lookupProduct (int productId) {
//        return someproduct
//    }
//
//    public ObservableList<Part> lookupPart (String partName) {
//        return list of parts
//    }
//
//    public ObservableList<Product> lookupProduct (String productName) {
//        return list of products
//    }
//
//    public void updatePart (int index, Part selectedPart) {
//
//    }
//
//    public void updateProduct (int index, Product newProduct) {
//
//    }
//
//    public boolean deletePart (Part selectedPart) {
//        return ture or false;
//    }
//
//    public boolean deleteProduct (Product selectedProduct) {
//        return true or false;
//    }
//
//    public ObservableList<Part> getAllParts () {
//        return some list
//    }
//
//    public ObservableList<Product> getAllProducts () {
//        return some list
//    }
}
