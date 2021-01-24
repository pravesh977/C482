package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Part;
import model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        primaryStage.setTitle("C482 Inventory Management System WGU");
        primaryStage.setScene(new Scene(root, 1020, 400));
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
//        System.out.println("hello boy");
//        Product bike = new Product(12, "BMX", 120, 15, 5, 20);
//        System.out.println(bike.getName());
        launch(args);

    }
}
