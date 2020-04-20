package Domain.BudgetControl;

import Domain.LeagueManagment.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class BudgetControl {
    private static final Logger LOG = LogManager.getLogger();
    private long balance;
    private LinkedList<Report> incomeAndExpenses;
    private Team team;

    public BudgetControl(Team team){
        incomeAndExpenses = new LinkedList<>();
        balance = 0;
        this.team=team;
    }

    /**OR**/
    //TODO test - V
    public void addIncome(String typeOfIncome, long amount) throws Exception {
        if(typeOfIncome ==null || typeOfIncome.length()==0){
            throw new Exception("type of income not valid");
        }
        if(amount<=0){
            throw new Exception("amount of income is negative");
        }
        incomeAndExpenses.add(new Report(typeOfIncome,amount));
        balance += amount;
        LOG.info(String.format("%s - %s", this.team.getName(), "add income to team budget, type: "+typeOfIncome+" amount: "+amount));
    }

    /**OR**/
    //TODO test - V
    public void addExpense(String typeOfExpense, long amount) throws Exception{
        if(typeOfExpense ==null || typeOfExpense.length()==0){
            throw new Exception("type of expense not valid");
        }
        if(amount<=0){
            throw new Exception("amount of expense is negative");
        }
        if (balance - amount >= 0) {
            amount = amount - amount * 2;// make the amount negative
            incomeAndExpenses.add(new Report(typeOfExpense, amount));
            balance = balance - amount;
        }
        else{
            throw new Exception("Budget cannot be less than 0");
        }
        LOG.info(String.format("%s - %s", this.team.getName(), "add expense to team budget, type: "+typeOfExpense+" amount: "+amount));
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public LinkedList<Report> getIncomeAndExpenses() {
        return incomeAndExpenses;
    }

    public void setIncomeAndExpenses(LinkedList<Report> incomeAndExpenses) {
        this.incomeAndExpenses = incomeAndExpenses;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
