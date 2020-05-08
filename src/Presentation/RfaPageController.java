package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class RfaPageController extends HomePageController {


    @FXML
    Button viewSettings;
    @FXML
    Button registrationForGamesAlerts;
    @FXML
    Button myAlerts;

    @FXML
    public javafx.scene.control.Button defineCalcPolicyBtn;
    public javafx.scene.control.Button defineSchedPolicyBtn;



//    private String userName="Ilan12";
    private String userName;


//    @FXML
//    public void fanDetailsEventClick(ActionEvent event) throws IOException {
//        FXMLLoader loader=new FXMLLoader();
//        loader.setLocation(getClass().getResource("FanDetails.fxml"));
//        Parent root=loader.load();
//        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
//        Scene scene = new Scene(root, 900, 600);
//        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
//        FanDetailsController fanDetailsController=loader.getController();
//        fanDetailsController.initUser(userName);
//
//        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stageTheEventSourceNodeBelongs.setScene(scene);
//        stageTheEventSourceNodeBelongs.show();
//    }


//    @FXML
//    public void registrationForGamesAlertsMouseClickHandling(MouseEvent mouseEvent) throws IOException {
//
//        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("RegistrationGamesAlerts.fxml"));
//        Scene scene = new Scene(root, 900, 600);
//
//        stageTheEventSourceNodeBelongs.setScene(scene);
//
//    }

    @FXML
    public void defineSchedulingPolicy(MouseEvent mouseEvent) throws IOException {

    }

    @FXML
    public void defineCalculationPolicy(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = (Parent) loader.load(getClass().getResource("CalculationPolicy.fxml").openStream());
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void MyAlertsFunction(MouseEvent mouseEvent) throws IOException {

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RfaNotification.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);

    }

}
