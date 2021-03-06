package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 * Javadoc location is C482_Project/src/Javadoc. This is an entry point for the app
 */
public class Main extends Application {


    /**
     * Creating test data for the application. Adding some inHouse parts, outsourced parts, adding them to products and associating a few parts to the products.
     */
    @Override
    public void init() {
//        Inventory inventory = new Inventory();
//        addTestData(inventory);

        //adding InHouse parts for test data
        Part tire = new InHouse(1, "bike tires", 12.99, 10, 5, 20, 1);
        Part seat = new InHouse(3, "Comfy Seat", 8.50, 12, 1, 50, 3);

        //adding OutSourced parts for test data
        Part chain = new Outsourced(4, "Sturdy Chain", 30.25, 12, 3, 20, "BMX Supplies");
        Part spoke = new Outsourced(5, "metal spoke", 5.10, 10, 4, 15, "Super Cycles Supplies");
        Part rim = new Outsourced(6, "Silver Rim", 15.50, 8, 1, 30, "Fly Bikes Co.");

        //adding test data to Inventory ObservableList
        Inventory.addPart(tire);
        Inventory.addPart(new InHouse(2, "Smooth handles", 15.00, 20, 10, 35, 2));
        Inventory.addPart(seat);
        Inventory.addPart(chain);
        Inventory.addPart(spoke);
        Inventory.addPart(rim);

        //creating Product object and adding some associated parts
        Product falconBicycle = new Product(1, "Falcon Bicycle", 500.00, 5, 1, 20);
        Product schwinnBike = new Product(2, "Schwinn Hybrid", 400.00, 7, 5, 15);
        Product roadMaster = new Product(3, "Granite Mountain Bike", 200.00, 20, 1, 50);

        falconBicycle.addAssociatedPart(tire);
        falconBicycle.addAssociatedPart(new InHouse(2, "Smooth handles", 15.00, 20, 10, 35, 2));
        falconBicycle.addAssociatedPart(spoke);
        falconBicycle.addAssociatedPart(rim);
        //System.out.println(falconBicycle.getName());
        //System.out.println(falconBicycle.getAllAssociatedParts().get(1).getName());
        Inventory.addProduct(falconBicycle);
        //System.out.println(Inventory.getAllProducts().get(0).getName());
        Inventory.addProduct(schwinnBike);
        Inventory.addProduct(roadMaster);

        schwinnBike.addAssociatedPart(spoke);
        schwinnBike.addAssociatedPart(rim);
        roadMaster.addAssociatedPart(tire);
        roadMaster.addAssociatedPart(seat);
        roadMaster.addAssociatedPart(chain);
    }

    /**
     * Setting scene for the application and opening the main_screen.fxml as the main screen when the app opens
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //initializing inventory object and passing it to addTestData
        //this is done at init method above
//        Inventory inventory = new Inventory();
//        addTestData(inventory);
        Parent root = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        primaryStage.setTitle("C482 Inventory Management System WGU");
        primaryStage.setScene(new Scene(root, 1020, 400));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
     * Launches the application
     */
    public static void main(String[] args) {

//        System.out.println("hello boy");
//        Product bike = new Product(12, "BMX", 120, 15, 5, 20);
//        System.out.println(bike.getName());
        launch(args);
    }

    /**
     * Anything in here gets executed after the application closes. Can be used to make cleanups.
     */
    @Override
    public void stop() {
        //System.out.println("STOP THIS");
    }

//    public void addTestData(Inventory inv) {

//        //adding InHouse parts for test data
//        Part tire = new InHouse(1, "bike tires", 12.99, 10,5,20,1);
//        inv.addPart(tire);
//        //can also be done in one line like below for product id 2
//        inv.addPart(new InHouse(2, "Smooth handles", 15.00, 20,10,35,2));
//        Part seat = new InHouse(3, "Comfy Seat", 8.50, 12,1,50,3);
//        inv.addPart(seat);
//
//        //adding OutSourced parts for test data
//        Part chain = new Outsourced(4,"Sturdy Chain", 30.25, 12,3,20,"BMX Supplies");
//        Part spoke = new Outsourced(5,"metal spoke", 5.10, 10,4,15,"Super Cycles Supplies");
//        Part rim = new Outsourced(6,"Silver Rim", 15.50, 8,1,30,"Fly Bikes Co.");
//        inv.addPart(chain);
//        inv.addPart(spoke);
//        inv.addPart(rim);
//        System.out.println(tire.getName());
    //creating Product object and adding some associated parts
//        Product falconBicycle = new Product(1,"Falcon Bicycle", 500.00, 5,1,10);
//        falconBicycle.addAssociatedPart(tire);
//        falconBicycle.addAssociatedPart(new InHouse(2, "Smooth handles", 15.00, 20,10,35,2));
//        falconBicycle.addAssociatedPart(spoke);
//        falconBicycle.addAssociatedPart(rim);
//    }
}
