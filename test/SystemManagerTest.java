import org.junit.Test;

import static org.junit.Assert.*;

public class SystemManagerTest {

    @Test
    public void removeUser(){
        MainSystem system =MainSystem.getInstance();
        SystemManager sm= new SystemManager(system,"d","df","df","df","df");

    }
}