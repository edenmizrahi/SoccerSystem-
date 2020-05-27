package Presentation;

import Service.RefereeApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class eventsPageController {

    @FXML
    public ComboBox<String> idOfMatch;
    @FXML
    public ComboBox<String> playerForGoal;
    @FXML
    public ComboBox<String> playerForInjury;
    @FXML
    public ComboBox<String> playerForOffense;
    @FXML
    public ComboBox<String> playerForOffSide;
    @FXML
    public ComboBox<String> player1ForReplace;
    @FXML
    public ComboBox<String> player2ForReplace;
    @FXML
    public ComboBox<String> playerForRedCard;
    @FXML
    public ComboBox<String> playerForYellowCard;
    @FXML
    public TextField idNumOfMinute;

    @FXML
    private RefereeApplication refereeApplication = new RefereeApplication();

    private String userName;
    private String match;


    public void initUser(String userName, String match) {
        this.userName = userName;

        this.match = match;

        List<String> list = new LinkedList<>();
        list.add(match);
        ObservableList<String> elements = FXCollections.observableArrayList(list);

        idOfMatch.setItems(elements);
        idOfMatch.getSelectionModel().selectFirst();
        this.match = idOfMatch.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initComboBox(MouseEvent event) {

        String fullId = event.getSource().toString();
        String[]s = fullId.split("=");
        String[] s1 = s[1].split(",");
        String id =s1[0];

        String allPlayersStr = refereeApplication.displayPlayersAtMatch(userName, idOfMatch.getSelectionModel().getSelectedItem());
        //String allPlayersStr = ClientController.connectToServer("RefereeApplication", "displayPlayersAtMatch", userName, idOfMatch.getSelectionModel().getSelectedItem());

        List<String> allPlayers = Arrays.asList(allPlayersStr.split(";"));
        List<String> list = new LinkedList<>();
        for (String str:allPlayers) {
            list.add(str);
        }

        ObservableList<String> elements = FXCollections.observableArrayList(list);

        if(id.equals("playerForGoal")) {
            playerForGoal.setItems(elements);
            playerForGoal.getSelectionModel().selectFirst();
        }
        else{
            if(id.equals("playerForInjury")) {
                playerForInjury.setItems(elements);
                playerForInjury.getSelectionModel().selectFirst();
            }
            else{
                if(id.equals("playerForOffense")) {
                    playerForOffense.setItems(elements);
                    playerForOffense.getSelectionModel().selectFirst();
                }
                else{
                    if(id.equals("playerForOffSide")) {
                        playerForOffSide.setItems(elements);
                        playerForOffSide.getSelectionModel().selectFirst();
                    }
                    else{
                        if(id.equals("player1ForReplace")) {
                            player1ForReplace.setItems(elements);
                            player1ForReplace.getSelectionModel().selectFirst();
                        }
                        else{
                            if(id.equals("player2ForReplace")) {
                                player2ForReplace.setItems(elements);
                                player2ForReplace.getSelectionModel().selectFirst();
                            }
                            else{
                                if(id.equals("playerForRedCard")) {
                                    playerForRedCard.setItems(elements);
                                    playerForRedCard.getSelectionModel().selectFirst();
                                }
                                else{
                                    if(id.equals("playerForYellowCard")) {
                                        playerForYellowCard.setItems(elements);
                                        playerForYellowCard.getSelectionModel().selectFirst();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @FXML
    public void BackToRefereePage(ActionEvent mouseEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RefereePage.fxml"));
        Scene scene = new Scene(root, 900, 600);
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    //<editor-fold desc="on submit click">

    public void onClickGoalSubmit() {
        String player = playerForGoal.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createGoalEvent(userName,match,player);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("Goal Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createGoalEvent", userName,match,player);
    }

    public void onClickInjurySubmit() {
        String player = playerForInjury.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createInjuryEvent(userName,match,player);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("Injury Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createInjuryEvent", userName,match,player);
    }

    public void onClickOffenseSubmit() {
        String player = playerForOffense.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createOffenseEvent(userName,match,player);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("Offense Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createOffenseEvent", userName,match,player);
    }

    public void onClickOffsideSubmit() {
        String player = playerForOffSide.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createOffSideEvent(userName,match,player);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("Offside Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createOffSideEvent", userName,match,player);
    }

    public void onClickReplaceSubmit() {
        String player1 = player1ForReplace.getSelectionModel().getSelectedItem();
        String player2 = player2ForReplace.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createReplaceEvent(userName,match,player1,player2);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("Replacement Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createReplaceEvent", userName,match,player1,player2);
    }

    public void onClickRedCardSubmit() {
        String player = playerForRedCard.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createRedCardEvent(userName,match,player);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("RedCard Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createRedCardEvent", userName,match,player);
    }

    public void onClickYellowCardSubmit() {
        String player = playerForYellowCard.getSelectionModel().getSelectedItem();
        String answer = this.refereeApplication.createYellowCardEvent(userName,match,player);
        if(! answer.equals("ok")){
            ErrorAlertForCreateEvent(answer);
        }
        else{
            SuccessAlertForCreateEvent("YellowCard Event was created!");
        }
        //String ans = ClientController.connectToServer("RefereeApplication", "createYellowCardEvent", userName,match,player);

    }

    public void onClickExtraTimeSubmit() {
        idNumOfMinute.setDisable(true);
        String numOfMinute = idNumOfMinute.getText();
         if(Pattern.matches("[0-9]+", numOfMinute)){
             String answer = this.refereeApplication.createExtraTimeEvent(userName,match,numOfMinute);
             if(! answer.equals("ok")){
                 ErrorAlertForCreateEvent(answer);
             }
             else{
                 SuccessAlertForCreateEvent("ExtraTime Event was created!");
             }
             //String ans = ClientController.connectToServer("RefereeApplication", "createExtraTimeEvent", userName,match,numOfMinute);
         }
        idNumOfMinute.setDisable(false);
    }

    //</editor-fold>

    //<editor-fold desc="alerts">

    public void ErrorAlertForCreateEvent(String error){
        Alert chooseFile = new Alert(Alert.AlertType.ERROR);
        chooseFile.setHeaderText("Error");
        chooseFile.setContentText(error);
        chooseFile.show();
    }

    public void SuccessAlertForCreateEvent(String success){
        Alert chooseFile = new Alert(Alert.AlertType.CONFIRMATION);
        chooseFile.setHeaderText("Success");
        chooseFile.setContentText(success);
        chooseFile.show();
    }

    //</editor-fold>


}
