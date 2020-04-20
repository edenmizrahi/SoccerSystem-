import java.util.HashMap;
import java.util.HashSet;

public interface CalculationPolicy {

    void calculate(HashMap<League, HashSet<Team >> teamsInSeason);
}
