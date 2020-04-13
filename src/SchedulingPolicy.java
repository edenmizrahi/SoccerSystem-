import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SchedulingPolicy extends Policy{

    private static final Logger LOG = LogManager.getLogger();

    public SchedulingPolicy(League league, Season season) {
        super(league, season);
    }
}
