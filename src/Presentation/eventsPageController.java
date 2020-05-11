package Presentation;

import Service.RefereeController;
import Service.RfaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.ParseException;
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
    private Service.RefereeController RefereeController = new RefereeController();

    private String userName;
    private String match;


    public void initUser(String userName) {
        this.userName = userName;

        String match = RefereeController.displayAllMatches(userName);
        List<String> list = new LinkedList<>();
        list.add(match);
        ObservableList<String> elements = FXCollections.observableArrayList(list);

        idOfMatch.setItems(elements);
        idOfMatch.getSelectionModel().selectFirst();
        this.match = idOfMatch.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initComboBox(MouseEvent event) throws ParseException {

        String fullId = event.getSource().toString();
        String[]s = fullId.split("=");
        String[] s1 = s[1].split(",");
        String id =s1[0];

        LinkedList<String> allPlayers = RefereeController.displayPlayersAtMatch(userName, idOfMatch.getSelectionModel().getSelectedItem());
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


    public void onClickGoalSubmit() throws Exception {
        String player = playerForGoal.getSelectionModel().getSelectedItem();
        this.RefereeController.createGoalEvent(userName,match,player);
    }

    public void onClickInjurySubmit() throws Exception {
        String player = playerForInjury.getSelectionModel().getSelectedItem();
        this.RefereeController.createInjuryEvent(userName,match,player);
    }

    public void onClickOffenseSubmit() throws Exception {
        String player = playerForOffense.getSelectionModel().getSelectedItem();
        this.RefereeController.createOffenseEvent(userName,match,player);
    }

    public void onClickOffsideSubmit() throws Exception {
        String player = playerForOffSide.getSelectionModel().getSelectedItem();
        this.RefereeController.createOffSideEvent(userName,match,player);
    }

    public void onClickReplaceSubmit() throws Exception {
        String player1 = player1ForReplace.getSelectionModel().getSelectedItem();
        String player2 = player2ForReplace.getSelectionModel().getSelectedItem();
        this.RefereeController.createReplaceEvent(userName,match,player1,player2);
    }


    public void onClickRedCardSubmit() throws Exception {
        String player = playerForRedCard.getSelectionModel().getSelectedItem();
        this.RefereeController.createRedCardEvent(userName,match,player);
    }

    public void onClickYellowCardSubmit() throws Exception {
        String player = playerForYellowCard.getSelectionModel().getSelectedItem();
        this.RefereeController.createYellowCardEvent(userName,match,player);
    }


    public void onClickExtraTimeSubmit() throws Exception {
        idNumOfMinute.setDisable(true);
        String numOfMinute = idNumOfMinute.getText();
         if(Pattern.matches("[0-9]+", numOfMinute)){
             this.RefereeController.createExtraTimeEvent(userName,match,numOfMinute);
         }

        idNumOfMinute.setDisable(false);
    }

}
