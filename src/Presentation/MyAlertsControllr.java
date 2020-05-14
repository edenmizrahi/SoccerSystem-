package Presentation;

import Service.FanController;
import Service.SystemOperationsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MyAlertsControllr {

    @FXML
    private ComboBox<String > myNotifictionsCombo;

    private FanController fanController= new FanController();
    private SystemOperationsController syOpController =new SystemOperationsController();
    private List<String> fanNotificationsList=new LinkedList<>();
    private String userName; // is teamRole




    @FXML
    public void initAllertsUser (String userName) throws IOException {
        this.userName=userName;
        //update comoboxs
        updateNotificationsComoBox();

    }


    @FXML
    public void updateNotificationsComoBox(){
        fanNotificationsList.clear();
        fanNotificationsList.add("all my alerts on matches");
        LinkedList<String> allFanAllerts = fanController.getFanNotifications(userName);
        for (String str:allFanAllerts) {
            fanNotificationsList.add(str);
        }
        ObservableList<String> elementsCombo = FXCollections.observableArrayList(fanNotificationsList);

        myNotifictionsCombo.setItems(elementsCombo);
        myNotifictionsCombo.getSelectionModel().selectFirst();

    }

    @FXML
    public void HomePageMouseClickHandling(MouseEvent mouseEvent) throws IOException {

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);
    }




}
