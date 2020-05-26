package Presentation;

import Service.RefereeApplication;
import Service.RfaApplication;
import Service.SystemOperationsApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RfaNotificationController {

    private RfaApplication rfaApplication = new RfaApplication();
    private List<String> RfaRequestList=new LinkedList<>();
    public String userName = "";

    @FXML
    private ComboBox<String > requestsCombo;


    @FXML
    public void initUser (String userName) {
        this.userName=userName;
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
}
