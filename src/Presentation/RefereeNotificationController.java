package Presentation;

import Service.FanApplication;
import Service.RefereeApplication;
import Service.SystemOperationsApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class RefereeNotificationController {

    private RefereeApplication refereeApplication = new RefereeApplication();
    private List<String> RefereeNotificationsList=new LinkedList<>();
    public String userName = "";

    @FXML
    private ComboBox<String > myNotificationsCombo;


    public void initAlertsUser(String userName) {
        this.userName = userName;
    }

    @FXML
    private void initComboBox(MouseEvent event) {
        RefereeNotificationsList.clear();
        RefereeNotificationsList.add("All my unread alerts about matches");
        String RefereeAlertsAsReferee = refereeApplication.getRefereeUnreadNotifications(userName);
        //String allRefereeAlertsStr = ClientController.connectToServer("refereeApplication", "getRefereeUnreadNotifications", userName);

        List<String> allRefereeAlerts = Arrays.asList(RefereeAlertsAsReferee.split(";"));
        for (String str:allRefereeAlerts) {
            RefereeNotificationsList.add(str);
        }
        ObservableList<String> elementsCombo = FXCollections.observableArrayList(RefereeNotificationsList);

        myNotificationsCombo.setItems(elementsCombo);
        myNotificationsCombo.getSelectionModel().selectFirst();
    }


    public void ClickOnMarkAsRead(ActionEvent actionEvent) {
        String notification = myNotificationsCombo.getSelectionModel().getSelectedItem();
        this.refereeApplication.markNotificationAsRead(notification, this.userName);
    }

    public void closeHandling(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent calcRoot = loader.load();
        HomePageController controller = loader.getController();
        controller.closeHandling(mouseEvent);
    }

    public void BackToReferee(ActionEvent actionEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RefereePage.fxml"));
        Scene scene = new Scene(root);
        stageTheEventSourceNodeBelongs.setScene(scene);
    }
}
