import java.util.HashMap;
import java.util.HashSet;

public interface SchedulingPolicy {

    void assign(HashMap<League, HashSet<Team>> teamsInCurrentSeasonLeagues, HashSet<Referee> referees, Referee mainRef) throws Exception;

}
