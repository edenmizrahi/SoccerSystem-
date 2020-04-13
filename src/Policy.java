import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Policy {

    private Season season;//1
    private static final Logger LOG = LogManager.getLogger();


    public Policy( Season season) {
        this.season = season;
    }

    private int caculate(){
        return 0;
    }


    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}