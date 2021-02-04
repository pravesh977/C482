package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product Class can create product objects and has parts
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for the Product Class that initializes values for a product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns id for a product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for a product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns name for a product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name for a product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns price for a product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price for a product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns stock for a product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock for a product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns min value for a product
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets min for a product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Returns max value for a product
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets max for a product
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds associated part object to a product
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes associated part for a product
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Returns all associated parts for a product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
