package Presentation;

import Domain.Users.Fan;
import Domain.Users.User;
import Service.FanController;
import javafx.scene.control.Label;

import java.util.List;

public class FanDetailsController {

    public Label nameLable;
    public Label colLabel;
    public Fan fan;
    public FanController fanController;



    private void showDetails() {
        List<String> userDetails=fanController.getPrivateDetails(fan);
        //rowLabel.textProperty()(this.characterPositionRow);
        //colLabel.textProperty().bind(this.characterPositionColumn);
    }

}
