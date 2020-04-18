import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class MainSystemTest {
    MainSystem ms= MainSystem.getInstance();


    @Test
    public void firstStartSystem() throws ParseException {
        ms.startSystem();
        Assert.assertTrue(ms.getUsers().size()==1);
        Assert.assertTrue(ms.getExtSystem()!=null);

    }

    @Test
    public void startSystem() throws ParseException {
        ms.startSystem();
        ms.startSystem();
        Assert.assertTrue(ms.getUsers().size() == 1);
        Assert.assertTrue(ms.getExtSystem() != null);
    }
}