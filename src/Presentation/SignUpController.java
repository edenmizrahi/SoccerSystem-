package Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

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

}
