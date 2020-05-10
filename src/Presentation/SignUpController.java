package Presentation;

import Domain.Main;
import Domain.MainSystem;
import Service.SystemOperationsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;


import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SignUpController {
    @FXML
    RadioButton rd_rfa;
    @FXML
    RadioButton rd_fan;
    @FXML
    RadioButton rd_player;
    @FXML
    RadioButton rd_coach;
    @FXML
    RadioButton rd_teamOwner;
    @FXML
    TextField txt_name;
    @FXML
    TextField txt_email;
    @FXML
    TextField txt_userName;
    @FXML
    TextField txt_password;
    @FXML
    TextField txt_passwordConfi;
    @FXML
    TextField  txt_phoneNumber;
    @FXML
    DatePicker dp_birthday;
    @FXML
    Label lbl_nameError;
    @FXML
    Label lbl_emailError;
    @FXML
    Label lbl_userNameError;
    @FXML
    Label lbl_PasswordError;
    @FXML
    Label lbl_PasswordError1;
    @FXML
    Label lbl_BirthdayError;
    @FXML
    Label lbl_RoleError;
    @FXML
    Label lbl_phoneError;

    SystemOperationsController soc=new SystemOperationsController();

    public void closeEvent(MouseEvent mouseEvent) {
        System.exit(0);
    }
    public void backHandling(MouseEvent mouseEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);
    }
    public void signUpHandling(MouseEvent mouseEvent) throws IOException {
        MainSystem ms= MainSystem.getInstance();
        boolean isValid=true;
        if(txt_name.getText().trim().isEmpty()){
           lbl_nameError.setText("Name required");
            isValid=false;
        }
        else{
            lbl_nameError.setText("");
        }
        //check that username in unique
        if(txt_userName.getText().trim().isEmpty() ){
            lbl_userNameError.setText("User name required");
            isValid=false;
        }
        else{
            lbl_userNameError.setText("");
        }
        //password length is 6 or more
        if(txt_password.getText().trim().isEmpty() ||txt_password.getText().length()<6){
            lbl_PasswordError.setText("Password required, more than 6 numbers or letters");
            isValid=false;
        }
        else{
            lbl_PasswordError.setText("");
        }
        //password length is 6 or more
        if(txt_passwordConfi.getText().trim().isEmpty() ||!txt_password.getText().equals(txt_passwordConfi.getText())){
            lbl_PasswordError1.setText("The passwords do not match");
            isValid=false;
        }
        else{
            lbl_PasswordError1.setText("");
        }
        // phone number is 10 digits
        if(!( txt_phoneNumber.getText().matches("^[0-9]*$"))){
            System.out.println("invalidPhone");
        }
        if(txt_phoneNumber.getText().trim().isEmpty() || txt_phoneNumber.getText().length()!=10||!( txt_phoneNumber.getText().matches("^[0-9]*$"))){
            lbl_phoneError.setText("Phone number is required - only numbers");
            isValid=false;
        }
        else{
            lbl_phoneError.setText("");
        }
        //email contains @
        if(txt_email.getText().trim().isEmpty() ||! txt_email.getText().contains("@")||(!(txt_email.getText().contains(".com")) && !txt_email.getText().contains(".co.il"))){
            lbl_emailError.setText("Email not valid");
            isValid=false;
        }
        else{
            lbl_emailError.setText("");

        }
        //birthday
        Date date=null;
        if(dp_birthday.getValue()==null ){
            lbl_BirthdayError.setText("birthday required");
            isValid=false;
        }
        else{
            lbl_BirthdayError.setText("");
            LocalDate localDate = dp_birthday.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);
        }
        String role=getRoleChoose();
        if(role==null){
            lbl_RoleError.setText("Role is required");
            isValid=false;
        }
        else{
            lbl_RoleError.setText("");
        }
        if(isValid){
            try {
                String ans=soc.signUp(role, txt_name.getText(), txt_phoneNumber.getText(), txt_email.getText(), txt_userName.getText(), txt_password.getText(), date);
                if(ans.contains("error")){//USER NAME ALREADY EXIST.
                    throw new Exception();
                }
                showAlert("Success , login now");
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root, 700, 400);
                scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
                stageTheEventSourceNodeBelongs.setScene(scene);
            }
            catch (Exception e){
                lbl_userNameError.setText("User name is already use");
            }
        }
    }

    private String getRoleChoose() {
        if(rd_rfa.isSelected()){
            return "RFA";
        }
        if(rd_fan.isSelected()){
            return "Fan";
        }
        if(rd_player.isSelected()){
            return "Player";
        }
        if(rd_coach.isSelected()){
            return "Coach";
        }
        if(rd_teamOwner.isSelected()){
            return "TeamOwner";
        }
        return null;
    }

    public void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        DialogPane dialogPane = alert.getDialogPane();
//        alert.setTitle("Warning");
        alert.setContentText(alertMessage);
        dialogPane.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");
        alert.show();
//        mazeDisplayer.requestFocus();
    }

}
