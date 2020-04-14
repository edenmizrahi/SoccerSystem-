import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculationPolicy extends Policy {
    private static final Logger LOG = LogManager.getLogger();

    public CalculationPolicy(League league, Season season) {
            super( season);
    }
}
