package Presentation;

import Service.FanController;
import Service.SystemOperationsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class FanDetailsController {


    @FXML
    private TextField fanUserName; //V
    @FXML
    private TextField currNameField; //V
    @FXML
    private TextField currPasswardField; //V
    @FXML
    private TextField currPhonNumberField; // V
    @FXML
    private TextField currEmailField; //V
    @FXML
    private TextField currDateOfBirthField; //V
    @FXML
    private Button updateMyDetailsButton; //V
    @FXML
    private TextField updateNameFeild; // V
    @FXML
    private Button fanNameUpdateButton; //V
    @FXML
    private Button fanPasswardUpdateButton; //V
    @FXML
    private Button FanPhonNumberUpdateButton; //V
    @FXML
    private Button fanEmailUpdateButton; //V
    @FXML
    private TextField updatePasswardField; //V
    @FXML
    private TextField updatePhonNumField; //V
    @FXML
    private TextField updateEmailField; //V
    @FXML
    private TextField welcomeUserNameField; //V
    @FXML
    private Button fanDateUpdateButton;
    @FXML
    private TextField updateDateField;




    private FanController fanController = new FanController();
    private SystemOperationsController syOpController =new SystemOperationsController();
    private String userName;//!!!!!!

    @FXML
    private void showDetails() {
        List<String> fanDetails= syOpController.getPrivateDetails(userName);
        //list : name, Password, PhoneNumber, Email, DateOfBirth
        currNameField.setText(fanDetails.get(0));
        currPasswardField.setText(fanDetails.get(1));
        currPhonNumberField.setText(fanDetails.get(2));
        currEmailField.setText(fanDetails.get(3));
        currDateOfBirthField.setText(fanDetails.get(4));
        fanUserName.setText(userName);
        welcomeUserNameField.setText(userName);

        //String desc = fanName.getText();
    }

    @FXML
    public void HomePageMouseClickHandling(MouseEvent mouseEvent) throws IOException {

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);

    }

    @FXML
    public void showAllhideFiels(MouseEvent mouseEvent) throws IOException {

        updateNameFeild.setVisible(true);
        fanNameUpdateButton.setVisible(true);
        fanPasswardUpdateButton.setVisible(true);
        FanPhonNumberUpdateButton.setVisible(true);
        fanEmailUpdateButton.setVisible(true);
        updatePasswardField.setVisible(true);
        updatePhonNumField.setVisible(true);
        updateEmailField.setVisible(true);
        fanDateUpdateButton.setVisible(true);
        updateDateField.setVisible(true);
        //updatetemp.setVisible(true);




    }

}
