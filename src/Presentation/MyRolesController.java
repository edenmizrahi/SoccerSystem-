package Presentation;

import Service.TeamManagementApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyRolesController { //implements Initializable {

    @FXML
    private ChoiceBox myRolesCB = new ChoiceBox();
    @FXML
    private ChoiceBox becomeRoleCB = new ChoiceBox();
    String userName; //= "Mike";
    private TeamManagementApplication tMApp = new TeamManagementApplication();

//    @Override
//    public void initialize(URL location, ResourceBundle resources){
//        initScene();
//    }

    @FXML
    public void initUser(String userName){
        this.userName = userName;
        initScene();
    }
    @FXML
    public void initScene(){
        String myRolesStr = tMApp.getMyRoles(userName);
        List<String> myRoles = Arrays.asList(myRolesStr.split(";"));
        myRolesCB.getItems().clear();
        for (String role : myRoles) {
            myRolesCB.getItems().add(role);
        }

        String canBecomeStr = tMApp.getWhatICanBecome(userName);
        List<String> canBecome = Arrays.asList(canBecomeStr.split(";"));
        becomeRoleCB.getItems().clear();
        for (String role : canBecome) {
            becomeRoleCB.getItems().add(role);
        }
    }

    @FXML
    public void addButton (ActionEvent actionEvent) {
        String role = (String) becomeRoleCB.getValue();
        if (role == null || role == ""){
            alertError("Please choose a role.");
        }
        else {
            tMApp.becomeRole(userName, role);
            initScene();
            Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
            chooseFile.setContentText("Congrats! You are now a " + role + "!");
            chooseFile.show();
        }
    }

    @FXML
    public void goToRoleButton (ActionEvent actionEvent) throws IOException{
        String role = (String) myRolesCB.getValue();
        if (role == null || role == ""){
            alertError("Please choose a role.");
        }
        else {
            if (role.equals("Team Owner")) {
                changeToRoleScene(actionEvent, "TeamOwner");
            } else if (role.equals("Team Manager")) {
                changeToRoleScene(actionEvent, "TeamManager");
            } else if (role.equals("Coach")) {
                changeToRoleScene(actionEvent, "Coach");
            } else {
                changeToRoleScene(actionEvent, "Player");
            }
        }
    }
    @FXML
    public void changeToHomePage(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("HomePage.fxml").openStream());
        HomePageController homeCont = loader.getController();
        //TODO add init user
        //homeCont.initUser(userName);
        //secondController.setStage(mStage);
        //stage.setTitle("second scene");
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void changeToRoleScene(ActionEvent event, String role) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(role + ".fxml"));
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


    private void alertError(String message){
        Alert chooseFile = new Alert(Alert.AlertType.ERROR);
        chooseFile.setHeaderText("Error");
        chooseFile.setContentText(message);
        chooseFile.show();
    }
}
