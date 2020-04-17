import org.junit.Assert;
import org.junit.Test;

public class MainSystemTest {
    MainSystem ms= MainSystem.getInstance();


    @Test
    public void firstStartSystem() {
        ms.startSystem();
        Assert.assertTrue(ms.getUsers().size()==1);
        Assert.assertTrue(ms.getExtSystem()!=null);

    }

    @Test
    public void startSystem() {
        ms.startSystem();
        ms.startSystem();
        Assert.assertTrue(ms.getUsers().size() == 1);
        Assert.assertTrue(ms.getExtSystem() != null);
    }
}