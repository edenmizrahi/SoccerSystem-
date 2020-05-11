package Presentation;

import Domain.LeagueManagment.Season;
import Service.RfaController;
import Service.SystemOperationsController;
import Service.TeamManagementController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class DefinePolicyController {

    @FXML
    public ComboBox<String> chooseCalcPolicyBtn;
    @FXML
    public ComboBox<String> chooseSchedPolicyBtn;
    @FXML
    public javafx.scene.control.TextField idOfSeason;
    @FXML
    public javafx.scene.control.Button defineCalcPolicyBtn;
    @FXML
    private RfaController RfaController = new RfaController();
    @FXML
    private SystemOperationsController sysOpController = new SystemOperationsController();


    private String userName;

    public void initialize() {
        //for comboBox of calculation policy
        LinkedList<String> allCalculationPolicies = RfaController.getAllCalculationPoliciesString();
        List<String> list = new LinkedList<>();
        for (String str:allCalculationPolicies) {
            list.add(str);
        }

        ObservableList<String> elements = FXCollections.observableArrayList(list);

        chooseCalcPolicyBtn.setItems(elements);
        chooseCalcPolicyBtn.getSelectionModel().selectFirst();


        //for comboBox of scheduling policy
        LinkedList<String> allSchedulingPolicies = RfaController.getAllschedulingString();
        List<String> listOfSched = new LinkedList<>();
        for (String str:allSchedulingPolicies) {
            listOfSched.add(str);
        }

        ObservableList<String> elementsOfScheduling = FXCollections.observableArrayList(listOfSched);

        chooseSchedPolicyBtn.setItems(elementsOfScheduling);
        chooseSchedPolicyBtn.getSelectionModel().selectFirst();
    }

    @FXML
    public void initUser (String userName) throws IOException {
        this.userName=userName;
    }


    //when press on define btn
    @FXML
    public void defineCalcPolicy(ActionEvent event){

        idOfSeason.setDisable(true);
        defineCalcPolicyBtn.setDisable(true);

        String seasonSelected = idOfSeason.getText();
        if (Pattern.matches("[0-9]+", seasonSelected) && seasonSelected.length() == 4) {

            String calcPolicySelected = chooseCalcPolicyBtn.getSelectionModel().getSelectedItem().toString();
            String schedPolicySelected = chooseSchedPolicyBtn.getSelectionModel().getSelectedItem().toString();
            this.RfaController.DefinePoliciesToSeason(seasonSelected, calcPolicySelected, schedPolicySelected);
        }
        else{
            //throw alert that the year of season incorrect syntax
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Incorrect Syntax in season field, please insert only numbers.");
            alert.show();
        }

        defineCalcPolicyBtn.setDisable(false);
        idOfSeason.setDisable(false);
    }

}
