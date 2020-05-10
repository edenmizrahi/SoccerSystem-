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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TeamManagementUIController implements Initializable {
    @FXML
    private ChoiceBox teamNameCB = new ChoiceBox();
    @FXML
    private ChoiceBox coachCB = new ChoiceBox();
    @FXML
    private ListView playersListView = new ListView();
    @FXML
    private TextField fieldName;
    @FXML
    private TextField newTeamName;
    @FXML
    private Label playersLabel = new Label();
    @FXML
    private AnchorPane activateTeamPane;
    String userName = "Ilan";
    private TeamManagementController tMController = new TeamManagementController();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        activateScene();
    }
    @FXML
    public void changeToRequestScene(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = (Parent) loader.load(getClass().getResource("RequestNewTeam.fxml").openStream());
        //SecondController secondController = loader.getController();
        //secondController.setStage(mStage);
        //stage.setTitle("second scene");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void changeToActivateScene(ActionEvent actionEvent) throws Exception{
        LinkedList<String> approvedTeams = tMController.getMyApprovedTeams(userName);
        LinkedList<String> tempPlayers = tMController.getAllTeamRolesThatArentPlayerWithTeam();
        LinkedList<String> Coaches = tMController.getAllTeamRolesThatArentCoachWithTeam();
        if (approvedTeams == null || approvedTeams.size() == 0){
            alertError("You do not have any teams to activate.");
        }
        else if (tempPlayers.size() < 11){
            alertError("Cannot create a new team. There are less than 11 potential players in the system.");
        }
        else if (Coaches.size() < 1){
            alertError("Cannot create a new team. There aren't any potential coaches in the system.");
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("ActivateTeam.fxml").openStream());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    @FXML
    public void changeToTeamManagementScene(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("TeamManagementUI.fxml").openStream());
        //SecondController secondController = loader.getController();
        //secondController.setStage(mStage);
        //stage.setTitle("second scene");
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void requestNewTeam(ActionEvent event) throws Exception{

        String teamName = newTeamName.getText();
        String message = tMController.requestNewTeam(userName, teamName);

        if (message == "team name not unique, already exist in system"){
            alertError("This team name already exists. Please choose a different team name.");
        }
        else {
            alertInformation("Your request has been sent to the RFA. Once approved you may activate your team.");
            changeToTeamManagementScene(event);
        }
    }

    @FXML
    public void activateScene(){
        LinkedList<String> approvedTeams = tMController.getMyApprovedTeams(userName);
        teamNameCB.getItems().clear();
        for (String teamName : approvedTeams) {
            teamNameCB.getItems().add(teamName);
        }

        LinkedList<String> tempPlayers = tMController.getAllTeamRolesThatArentPlayerWithTeam();
        ObservableList<String> players = FXCollections.observableArrayList(tempPlayers);
        playersListView.getItems().clear();
        playersListView.setItems(players);
        playersListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        LinkedList<String> Coaches = tMController.getAllTeamRolesThatArentCoachWithTeam();
        coachCB.getItems().clear();
        for (String coachUserName : Coaches) {
            coachCB.getItems().add(coachUserName);
        }
    }
    @FXML
    public void activateButton(ActionEvent actionEvent) throws Exception {
        String teamName = (String) teamNameCB.getValue();

        String coachUserName = (String) coachCB.getValue();

        ObservableList<String> selectedPlayers = playersListView.getSelectionModel().getSelectedItems();
        HashSet<String> players = new HashSet<>(selectedPlayers);
        String field = fieldName.getText();

        if (teamName == null) {
            alertError("Please choose a team name.");
        }else if (selectedPlayers == null || selectedPlayers.size() < 11){
            alertError("Please choose at least 11 players.");
        }
        else if (coachUserName == null) {
            alertError("Please choose a coach.");
        } else if (field == null || field.equals("")) {
            alertError("Please insert a field name.");
        } else if(players == null || players.size() < 11){
            alertError("Please choose players.");
        }
        else {
            tMController.makeTeamActive(userName, teamName, players, coachUserName, field);
            changeToTeamManagementScene(actionEvent);
            alertInformation("Congratulations! Your new team has been activated.");
        }
    }
    @FXML
    public void initUser(String userName){
        this.userName = userName;
    }

    private void alertError(String message){
        Alert chooseFile = new Alert(Alert.AlertType.ERROR);
        chooseFile.setHeaderText("Error");
        chooseFile.setContentText(message);
        chooseFile.show();
    }
    private void alertInformation(String message){
        Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
        chooseFile.setContentText(message);
        chooseFile.show();
    }

}
