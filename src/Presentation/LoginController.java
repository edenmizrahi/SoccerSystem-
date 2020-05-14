package Presentation;

import Domain.MainSystem;
import Domain.Users.Fan;
import Domain.Users.User;
import Service.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginController{
    @FXML
    TextField txt_userName;
    @FXML
    TextField txt_password;
    @FXML
    Label lbl_error;

    @FXML
    public void closeHandling(MouseEvent mouseEvent) {
        System.exit(0);
    }
    @FXML
    public void validationHandling(MouseEvent mouseEvent) {
        UserController uc=new UserController();
        try {
            String userName= uc.login(txt_userName.getText(),txt_password.getText());
            lbl_error.setText("");
            /**notification*/
            if (uc.haveUnreadNotifications(txt_userName.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("");
                DialogPane dialogPane = alert.getDialogPane();
                alert.setContentText("You have unread notifications!");
                dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
                dialogPane.getStyleClass().add("alert");
                alert.show();
            }
            if(userName.equals("RFA")){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("RfaPage.fxml"));
                Parent root=loader.load();
                Scene scene = new Scene(root, 900, 600);
                //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
                RfaPageController rfaC=loader.getController();
                rfaC.initUser(userName);
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(scene);
                stageTheEventSourceNodeBelongs.show();
            }
            else{
                if(userName.equals("Referee")){
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("RefereePage.fxml"));
                    Parent root=loader.load();
                    // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
                    Scene scene = new Scene(root, 900, 600);
                    //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
                    RefereePageController refereeController=loader.getController();
                    refereeController.initUser(userName);

                    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                    stageTheEventSourceNodeBelongs.setScene(scene);
                    stageTheEventSourceNodeBelongs.show();
                }
                else{
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("HomePage.fxml"));
                    Parent root=loader.load();
                    // Parent root = FXMLLoader.load(getClass().getResource("FanDetails.fxml"));
                    Scene scene = new Scene(root, 900, 600);
                    //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
                    HomePageController hpc=loader.getController();
//                    hpc.initUser(userName);

                    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                    stageTheEventSourceNodeBelongs.setScene(scene);
                    stageTheEventSourceNodeBelongs.show();
                }
            }
            //call to home page
        }
        catch (Exception e){
            lbl_error.setText("incorrect user name or password");
        }
    }
    @FXML
    public void signUpMouseClickHandling(MouseEvent mouseEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);
    }
}
