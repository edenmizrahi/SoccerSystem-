import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

public class BudgetControl {
    private static final Logger LOG = LogManager.getLogger();
    private long budget;
    LinkedHashMap<String, Long> incomeAndExpenses;

    BudgetControl(){
        incomeAndExpenses = new LinkedHashMap<>();
        budget = 0;
    }

    public void addIncome(String typeOfIncome, long amount){
        incomeAndExpenses.put(typeOfIncome, amount);
        budget += amount;
    }
    public void addExpense(String typeOfExpense, long amount) throws Exception{
        if (budget - amount >= 0) {
            amount = amount - amount * 2;
            incomeAndExpenses.put(typeOfExpense, amount);
            budget = budget - amount;
        }
        else{
            throw new Exception("Budget cannot be less than 0");
        }
    }



}
