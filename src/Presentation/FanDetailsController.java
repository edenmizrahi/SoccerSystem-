package Presentation;

import Service.FanController;
import Service.SystemOperationsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class FanDetailsController {


    @FXML
    private TextField fanUserName;
    @FXML
    private TextField fanName;
    @FXML
    private TextField fanPassward;
    @FXML
    private TextField fanPhoneNumber;
    @FXML
    private TextField fanEmail;
    @FXML
    private TextField fanDateOfBirth;



    private FanController fanController = new FanController();
    private SystemOperationsController syOpController =new SystemOperationsController();
    private String fan;//!!!!!!

    @FXML
    private void showDetails() {
        List<String> fanDetails= syOpController.getPrivateDetails(fan);
        //list : name, Password, PhoneNumber, Email, DateOfBirth
        fanName.setText(fanDetails.get(0));
        fanPassward.setText(fanDetails.get(1));
        fanPhoneNumber.setText(fanDetails.get(2));
        fanEmail.setText(fanDetails.get(3));
        fanDateOfBirth.setText(fanDetails.get(4));


        //String desc = fanName.getText();
    }

    @FXML
    public void signInMouseClickHandling(MouseEvent mouseEvent) throws IOException {

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);

    }

}
