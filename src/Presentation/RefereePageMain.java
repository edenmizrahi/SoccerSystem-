package Presentation;

import Domain.MainSystem;
import Service.SystemOperationsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RefereePageMain extends Application {
    @Override

    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("RefereePage.fxml"));
        primaryStage.setTitle("Referee home page");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {

        SystemOperationsController systemOperationsController =new SystemOperationsController();
        systemOperationsController.initSystemObjectsYardenRefereeForUI();
        MainSystem ma= MainSystem.getInstance();

        launch(args);
    }
}
