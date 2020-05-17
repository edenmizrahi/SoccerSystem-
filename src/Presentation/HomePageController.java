package Presentation;

import Service.FanApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HomePageController {


    @FXML
    private Button viewSettings;
    @FXML
    private Button registrationForGamesAlerts;
    @FXML
    private Button myAlerts;
    @FXML
    private Button buttonMyRoles;
    @FXML
    private  Button tempButtonTeamRole;



    private FanApplication fanApplication = new FanApplication();
    private String userName="Ilan12"; // is teamRole


//    @FXML
//    public void fanDetailsMouseClickHandling(MouseEvent mouseEvent) throws IOException {
//
//        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
//        Scene scene = new Scene(root, 900, 600);
//        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
//        stageTheEventSourceNodeBelongs.setScene(scene);
//
//    }

    /**
     * or- check if notification function works
     */
    @FXML
    public void initialize() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("checking notifications");
                String ans=fanApplication.checkForNewNotifications(userName);
                if(ans.equals("gotFanNotification")){//fan
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have a new Notification about a game you are following !");
                    chooseFile.show();
                }
                else if(ans.equals("gotRFAnotification")){//rfa
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have a new team to approve !");
                    chooseFile.show();
                }
                else if(ans.equals("gotRefereeNotification")){//referee
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have a new notification about your match !");
                    chooseFile.show();
                }
                else if(ans.equals("multipleNotifications")){//referee
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have multiple new notifications!");
                    chooseFile.show();
                }
               
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }


    @FXML
    public void fanDetailsEventClick(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("FanDetails.fxml"));
        Parent root=loader.load();
        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        FanDetailsController fanDetailsController=loader.getController();
        fanDetailsController.initUserDetails(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }


    @FXML
    public void registrationForGamesAlertsOnlickHandling(ActionEvent event) throws IOException {

        /*
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationGamesAlerts.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);
        */

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("RegistrationGamesAlerts.fxml"));
        Parent root=loader.load();
        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        RegistrationGamesAlertsController registrationGamesAlertsController=loader.getController();
        registrationGamesAlertsController.initUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();

    }


    @FXML
    public void fanAllertsEventClick(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("MyAlerts.fxml"));
        Parent root=loader.load();
        //Scene scene = new Scene(root, 900, 600);
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        MyAlertsControllr myRolesController=loader.getController();
        myRolesController.initAllertsUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }


//    @FXML
//    //!!!!!!!!!!!!!!!!!!!!!!
//    public void MyAlertsMouseClickHandling(MouseEvent mouseEvent) throws IOException {
///*
//        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("MyAlerts.fxml"));
//        Scene scene = new Scene(root, 900, 600);
//        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
//        stageTheEventSourceNodeBelongs.setScene(scene);
//*/
//    }

    /**
     * show my roles button if fan is teamRule
     * @param // userName
     */
    @FXML
    public void initHomePage(){ // String userName
        this.userName=userName;
        String isTeamRole= fanApplication.fanIsTeamRole(userName);
        if(isTeamRole.equals("true")){
            buttonMyRoles.setVisible(true);
        }


    }

    @FXML
    public void myRolesEventClick(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("MyRoles.fxml"));
        Parent root=loader.load();
        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        MyRolesController myRolesController=loader.getController();
        myRolesController.initUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }
}
