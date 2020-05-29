package Presentation;

import Domain.Controllers.SystemOperationsController;
import Domain.MainSystem;
import Service.SystemOperationsApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RefereePageMain extends Application {
    @Override

    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("RefereePage.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Referee home page");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {

        SystemOperationsController systemOperationsController = new SystemOperationsController();
        systemOperationsController.initSystemFromDB();
        systemOperationsController.initSystemObjectsYardenRefereeForUI();
        MainSystem ma= MainSystem.getInstance();

        launch(args);
    }
}
