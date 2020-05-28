package Presentation;

import Service.FanApplication;
import Service.SystemOperationsApplication;
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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyAlertsControllr {

    @FXML
    private ComboBox<String > myNotifictionsCombo;
    private FanApplication fanApplication = new FanApplication();
    private SystemOperationsApplication syOpApp =new SystemOperationsApplication();
    private List<String> fanNotificationsList=new LinkedList<>();
    private String userName; // is teamRole

    @FXML
    public void initAllertsUser (String userName) {
        this.userName=userName;
        //update comoboxs
        updateNotificationsComoBox();
    }

    @FXML
    public void updateNotificationsComoBox(){
        fanNotificationsList.clear();
        fanNotificationsList.add("all my alerts about matches");
        String allFanAllertsStr = fanApplication.getFanNotifications(userName);
        //String allFanAllertsStr = ClientController.connectToServer("FanApplication", "getFanNotifications", userName);

        List<String> allFanAllerts = Arrays.asList(allFanAllertsStr.split(";"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        HomePageController controller = loader.getController();
        controller.initHomePage(userName);
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    public void closeHandling(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent calcRoot = loader.load();
        HomePageController controller = loader.getController();
        controller.initHomePage(userName);
        controller.closeHandling(mouseEvent);
    }
}
