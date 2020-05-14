package Presentation;

import Service.TeamManagementController;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MyRolesController implements Initializable {

    @FXML
    private ChoiceBox myRolesCB = new ChoiceBox();
    @FXML
    private ChoiceBox becomeRoleCB = new ChoiceBox();
    String userName = "Mike";
    private TeamManagementController tMController = new TeamManagementController();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        initScene();
    }
    @FXML
    public void initScene(){
        LinkedList<String> myRoles = tMController.getMyRoles(userName);
        myRolesCB.getItems().clear();
        for (String role : myRoles) {
            myRolesCB.getItems().add(role);
        }

        LinkedList<String> canBecome = tMController.getWhatICanBecome(userName);
        becomeRoleCB.getItems().clear();
        for (String role : canBecome) {
            becomeRoleCB.getItems().add(role);
        }
    }
    @FXML
    public void initUser(String userName){
        this.userName = userName;
    }

    @FXML
    public void addButton (ActionEvent actionEvent) throws Exception {
        String role = (String) becomeRoleCB.getValue();
        tMController.becomeRole(userName, role);
        initScene();
        Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
        chooseFile.setContentText("Congrats! You are now a " + role + "!" );
        chooseFile.show();
    }

    @FXML
    public void goToRoleButton (ActionEvent actionEvent) throws Exception{
        String role = (String) myRolesCB.getValue();
        if (role.equals("Team Owner")){
            changeToRoleScene(actionEvent, "TeamOwner");
        }
        else if(role.equals("Team Manager")){
            changeToRoleScene(actionEvent, "TeamManager");
        }
        else if (role.equals("Coach")){
            changeToRoleScene(actionEvent, "Coach");
        }
        else{
            changeToRoleScene(actionEvent, "Player");
        }
    }
    @FXML
    public void changeToHomePage(ActionEvent actionEvent) throws Exception{
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
    public void changeToRoleScene(ActionEvent actionEvent, String role) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(role + ".fxml").openStream());
        TeamManagementUIController tMUICont = loader.getController();
        tMUICont.initUser(userName);
        //secondController.setStage(mStage);
        //stage.setTitle("second scene");
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
