import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BudgetControl {

    private List<TeamOwner> teamOwnerList;
    private List<Rfa> rfaList;
    private static final Logger LOG = LogManager.getLogger();

    public void manageBudget(){}

    public void removeRfa(Rfa rfa) {
        rfaList.remove(rfa);
    }
}
