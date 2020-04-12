import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main {

    private static final Logger LOG = LogManager.getLogger();
    public static void main(String [] args){
        //System.setProperty("log4j.configurationFile","resources/log4j2.xml");
// org.apache.logging.log4j.core.config.Configurator;
        //Configurator.setLevel("com.example.Foo", Level.DEBUG);

        // You can also set the root logger:
        Configurator.setRootLevel(Level.INFO);

        LOG.info(String.format("Logging level: %s", LOG.getLevel()));
        LOG.info("Server starter at %s!");
        LOG.info("try this");
        LOG.debug("try in debug");
    }
}