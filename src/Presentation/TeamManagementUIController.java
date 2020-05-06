package Presentation;

import Domain.LeagueManagment.Team;
import Domain.Users.User;
import Service.TeamManagementController;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class TeamManagementUIController{
    @FXML
    private ChoiceBox teamNameCB;
    @FXML
    private ChoiceBox coachCB;
    @FXML
    private ListView playersListView;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField newTeamName;

    String userName;

    private TeamManagementController tMController = new TeamManagementController();


    @FXML
    public void requestNewTeam(ActionEvent event) throws Exception{
        String teamName = newTeamName.getText();
        tMController.requestNewTeam(userName, teamName);
    }

    @FXML
    public void activateTeam(){
        LinkedList<String> approvedTeams = tMController.getMyApprovedTeams(userName);
        for(String teamName : approvedTeams){
            teamNameCB.getItems().add(teamName);
        }

        LinkedList<String> tempPlayers = tMController.getAllTeamRolesThatArentPlayerWithTeam();
        ObservableList<String> players =  FXCollections.observableList(tempPlayers);
        playersListView.setItems(players);
        playersListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        LinkedList<String> Coaches = tMController.getAllTeamRolesThatArentCoachWithTeam();
        for(String coachUserName : Coaches){
            coachCB.getItems().add(coachUserName);
        }
    }
    @FXML
    public void activateButton() throws Exception{
        String teamName = (String) teamNameCB.getValue();

        ObservableList<String> selectedPlayers = playersListView.getSelectionModel().getSelectedItems();
        HashSet<String> players = new HashSet<>(selectedPlayers);

        String coachUserName = (String) coachCB.getValue();

        String field = fieldName.getText();

        tMController.makeTeamActive(userName, teamName, players, coachUserName, field);

    }
}
