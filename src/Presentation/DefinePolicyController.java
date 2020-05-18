package Presentation;

import Service.RfaApplication;
import Service.SystemOperationsApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
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
    private RfaApplication rfaApplication = new RfaApplication();
    @FXML
    private SystemOperationsApplication sysOpApp = new SystemOperationsApplication();


    private String userName;

    public void initialize() {
        //for comboBox of calculation policy
        String allCalculationPoliciesStr = rfaApplication.getAllCalculationPoliciesString();
        List<String> allCalculationPolicies = Arrays.asList(allCalculationPoliciesStr.split(","));
        List<String> list = new LinkedList<>();
        for (String str:allCalculationPolicies) {
            list.add(str);
        }

        ObservableList<String> elements = FXCollections.observableArrayList(list);

        chooseCalcPolicyBtn.setItems(elements);
        chooseCalcPolicyBtn.getSelectionModel().selectFirst();


        //for comboBox of scheduling policy
        String allSchedulingPoliciesStr = rfaApplication.getAllschedulingString();
        List<String> allSchedulingPolicies = Arrays.asList(allSchedulingPoliciesStr.split(","));
        List<String> listOfSched = new LinkedList<>();
        for (String str:allSchedulingPolicies) {
            listOfSched.add(str);
        }

        ObservableList<String> elementsOfScheduling = FXCollections.observableArrayList(listOfSched);

        chooseSchedPolicyBtn.setItems(elementsOfScheduling);
        chooseSchedPolicyBtn.getSelectionModel().selectFirst();
    }

    @FXML
    public void initUser (String userName) {
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
            this.rfaApplication.DefinePoliciesToSeason(seasonSelected, calcPolicySelected, schedPolicySelected, userName);
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


    @FXML
    public void BackToRfaPage(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RfaPage.fxml"));
        Scene scene = new Scene(root, 900, 600);
        stageTheEventSourceNodeBelongs.setScene(scene);
    }
}
