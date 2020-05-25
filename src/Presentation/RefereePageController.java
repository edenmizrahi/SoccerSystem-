package Presentation;

import Service.RefereeApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RefereePageController extends HomePageController {


    @FXML
    Button viewSettings;
    @FXML
    Button registrationForGamesAlerts;
    @FXML
    Button myAlerts;

    @FXML
    public javafx.scene.control.Button addEvent;

    @FXML
    public javafx.scene.control.Button idReportbtn;

    @FXML
    private RefereeApplication refereeApplication = new RefereeApplication();

    private String userName = "dana123";


    @FXML
    public void initUser (String userName) {
        this.userName=userName;
    }


    @FXML
    public void addEventToMatch(ActionEvent actionEvent) throws IOException {

        String match = refereeApplication.displayAllMatches(userName);
        //String match = ClientController.connectToServer("RefereeApplication", "displayAllMatches", userName);

        if(! match.equals("")) {
            //display matches that still not take place
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("eventsPage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 900, 600);

            eventsPageController eventsPageController = loader.getController();
            eventsPageController.initUser(userName, match);

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(scene);
            stageTheEventSourceNodeBelongs.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You do not have a match that takes place right now so you can not add"
                    + " any events.");
            alert.show();
        }

    }


    public void createReport(ActionEvent actionEvent) throws IOException {
        String matchesStr = refereeApplication.getAllMatches(userName);
        //String match = ClientController.connectToServer("RefereeApplication", "getAllMatches", userName);

        List<String> matches = Arrays.asList(matchesStr.split(";"));

        if(matches.size() > 0){

            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("ReportOfMatch.fxml"));
            Parent root=loader.load();

            Scene scene = new Scene(root, 900, 600);

            ReportOfMatchController reportOfMatchController = loader.getController();
            reportOfMatchController.initPage(userName, matches);

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(scene);
            stageTheEventSourceNodeBelongs.show();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You do not have a match that ended and in which you were a main referee," +
                    " so you can not create a report");
            alert.show();
        }
    }

    @FXML
    public void MyAlertsFunction(MouseEvent mouseEvent) throws IOException {

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RefereePage.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);

    }

}
