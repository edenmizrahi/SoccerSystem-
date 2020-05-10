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
        User user=new User(MainSystem.getInstance());
        UserController uc=new UserController();
        try {
            Fan f= uc.login(user,txt_userName.getText(),txt_password.getText());
            lbl_error.setText("");

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
