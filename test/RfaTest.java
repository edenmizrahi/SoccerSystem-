import org.junit.Test;

import static org.junit.Assert.*;

public class RfaTest {

    MainSystem ms = MainSystem.getInstance();
    Rfa nadav = new Rfa(ms,"nadav","052","nadav@","nadavS", "nadav123");
//    Referee moshe = new Referee(ms,"moshe","0546145795","moseh@gmail.com","moshe123","moshe123","a");

    @Test
    public void addReferee(){

//        nadav.addReferee("moshe","0546145795","moshe@gmail.com","moshe123","moshe123","a");
        assertEquals(2,ms.getUsers().size());
        //invalid details
//        nadav.addReferee("moshe","0546","moshe@gmail.com","moshe123","moshe123","a");
        assertEquals(2,ms.getUsers().size());
    }

    @Test
    public void deleteReferee(){


    }
}