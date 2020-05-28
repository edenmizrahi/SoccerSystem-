package Presentation;

import Service.RefereeApplication;
import Service.RfaApplication;
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

public class RfaNotificationController {

    private RfaApplication rfaApplication = new RfaApplication();
    private List<String> RfaRequestList=new LinkedList<>();
    public String userName ;
    private String role= "Rfa";

    @FXML
    private ComboBox<String > requestsCombo;


    @FXML
    public void initUser (String userName,String role) {
        this.userName=userName;
        this.role=role;
    }

    @FXML
    private void initComboBox(MouseEvent event) {
        RfaRequestList.clear();
        RfaRequestList.add("All my unread alerts about matches");
        String RfaAlertsAsRfa = rfaApplication.getTeamRequests(userName);
        //String allRefereeAlertsStr = ClientController.connectToServer("RfaApplication", "getTeamRequests", userName);

        List<String> allRfaRequests = Arrays.asList(RfaAlertsAsRfa.split(";"));
        for (String str:allRfaRequests) {
            RfaRequestList.add(str);
        }
        ObservableList<String> elementsCombo = FXCollections.observableArrayList(RfaRequestList);

        requestsCombo.setItems(elementsCombo);
        requestsCombo.getSelectionModel().selectFirst();
    }


    public void onDecline(ActionEvent actionEvent) {
        String teamName = requestsCombo.getSelectionModel().getSelectedItem().toString();
        this.rfaApplication.answerRequest(userName,teamName,"false");

    }

    public void onApprove(ActionEvent actionEvent) {
        String teamName = requestsCombo.getSelectionModel().getSelectedItem().toString();
        this.rfaApplication.answerRequest(userName,teamName,"true");
    }

    public void closeHandling(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent calcRoot = loader.load();
        HomePageController controller = loader.getController();
        controller.closeHandling(mouseEvent);
    }

    public void BackToRfa(ActionEvent actionEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RfaPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        RfaPageController controller = loader.getController();
        controller.initUser(userName,role);
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

}
