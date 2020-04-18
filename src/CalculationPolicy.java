import java.util.HashMap;
import java.util.HashSet;

public interface CalculationPolicy {

    public void calculate(HashMap<League, HashSet<Team >> teamsInSeason);
}
