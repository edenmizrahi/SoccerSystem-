package Presentation;

import Service.TeamManagementApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class TeamManagementUIController { //implements Initializable {
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
    String userName;
    private TeamManagementApplication tMApp = new TeamManagementApplication();

//    @Override
//    public void initialize(URL location, ResourceBundle resources){
//            activateScene();
//    }

    @FXML
    public void initUser(String userName){
        this.userName = userName;
        activateScene();
    }

    /*@FXML
    public void changeToRequestScene(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = (Parent) loader.load(getClass().getResource("RequestNewTeam.fxml").openStream());
        //SecondController secondController = loader.getController();
        //secondController.setStage(mStage);
        //stage.setTitle("second scene");
        stage.setScene(new Scene(root));
        stage.show();
    }*/
    private void changeScene (ActionEvent event, String fxml) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Parent root=loader.load();
        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        TeamManagementUIController tMUICont = loader.getController();
        tMUICont.initUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }
    @FXML
    public void changeToRequestScene (ActionEvent event) throws IOException {
        changeScene(event, "RequestNewTeam.fxml");
    }
    @FXML
    public void changeToActivateScene(ActionEvent event) throws Exception{
        LinkedList<String> approvedTeams = tMApp.getMyApprovedTeams(userName);
        LinkedList<String> tempPlayers = tMApp.getAllTeamRolesThatArentPlayerWithTeam();
        LinkedList<String> Coaches = tMApp.getAllTeamRolesThatArentCoachWithTeam();
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
            changeScene(event, "ActivateTeam.fxml");
        }
    }

    @FXML
    public void changeToTeamManagementScene(ActionEvent actionEvent) throws Exception{
       changeScene(actionEvent, "TeamOwner.fxml");
    }
    @FXML
    public void changeToHomePage(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePage.fxml"));
        Parent root=loader.load();
        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        HomePageController homeCont = loader.getController();
        //TODO send username
        //homeCont.initHomePage(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }

    @FXML
    public void changeToMyRolesScene(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyRoles.fxml"));
        Parent root=loader.load();
        // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        MyRolesController myRolesCont = loader.getController();
        myRolesCont.initUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }
    @FXML
    public void requestNewTeam(ActionEvent event) throws Exception{

        String teamName = newTeamName.getText();
        if (teamName == null || teamName.equals("")){
            alertError("Please insert a team name.");
        }
        else {
            String message = tMApp.requestNewTeam(userName, teamName);

            if (message == "team name not unique, already exist in system") {
                alertError("This team name already exists. Please choose a different team name.");
            } else {
                alertInformation("Your request has been sent to the RFA. Once approved you may activate your team.");
                changeToTeamManagementScene(event);
            }
        }
    }

    @FXML
    public void activateScene(){
        if (tMApp.getMyApprovedTeams(userName) !=  null && tMApp.getMyApprovedTeams(userName).size() != 0) {
            LinkedList<String> approvedTeams = tMApp.getMyApprovedTeams(userName);
            teamNameCB.getItems().clear();
            for (String teamName : approvedTeams) {
                teamNameCB.getItems().add(teamName);
            }
        }

        LinkedList<String> tempPlayers = tMApp.getAllTeamRolesThatArentPlayerWithTeam();
        ObservableList<String> players = FXCollections.observableArrayList(tempPlayers);
        playersListView.getItems().clear();
        playersListView.setItems(players);
        playersListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        LinkedList<String> Coaches = tMApp.getAllTeamRolesThatArentCoachWithTeam();
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
            tMApp.makeTeamActive(userName, teamName, players, coachUserName, field);
            changeToTeamManagementScene(actionEvent);
            alertInformation("Congratulations! Your new team has been activated.");
        }
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
