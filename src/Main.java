import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String [] args){


        LOG.info(String.format("%s - %s", "try", "action?"));
//        User user= new User(new MainSystem());
//        Player p= new Player(new MainSystem(),"name","054","@","oral","021390");
//        System.out.println(user instanceof  User);
//        System.out.println(p instanceof Subscription);
    }
}