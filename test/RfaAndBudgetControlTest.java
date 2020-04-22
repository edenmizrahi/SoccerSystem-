import Domain.MainSystem;
import Domain.Users.Rfa;

import java.text.ParseException;

public class RfaAndBudgetControlTest {

    MainSystem ms = MainSystem.getInstance();
    Rfa rfa = new Rfa(ms,"rfa","052","rfa@","rfa123", "rfa123", MainSystem.birthDateFormat.parse("06-07-1992"));


    public RfaAndBudgetControlTest() throws ParseException {
    }
}
