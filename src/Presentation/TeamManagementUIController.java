package Presentation;

import Service.TeamManagementController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamManagementUIController{
    @FXML
    private ChoiceBox incomeExpenseCB;
    @FXML
    private ChoiceBox chooseTeamCB;
    @FXML
    private TextField incomeExpenseDescription;
    @FXML
    private TextField incomeExpenseAmount;


    private TeamManagementController tMController = new TeamManagementController();


    @FXML
    public void reportIncomeExpense(){

        //add team to CB

        incomeExpenseCB.getItems().add("income");
        incomeExpenseCB.getItems().add("expense");
        String incomeOrExpense = (String) incomeExpenseCB.getValue();
        String desc = incomeExpenseDescription.getText();
        String amount = incomeExpenseAmount.getText();

        if (incomeOrExpense == "income"){
            //tMController.addIncomeToTeam();
        }
        else{
            //tMController.deleteTeam();
        }

    }
}
