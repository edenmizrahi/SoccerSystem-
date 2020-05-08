package Presentation;

import Domain.MainSystem;
import Service.SystemOperationsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        primaryStage.setTitle("home page");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {


        SystemOperationsController systemOperationsController =new SystemOperationsController();
        SystemOperationsController.initSystemObjectsAvitalForUI();
        MainSystem ma= MainSystem.getInstance();

        launch(args);
    }
}
