import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class RfaTest {

    MainSystem ms = MainSystem.getInstance();
   // Rfa nadav = new Rfa(ms,"nadav","052","nadav@","nadavS", "nadav123");
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

    /**or**/
    @Test
    public void answerRequest() throws ParseException {
        TeamStub team = new TeamStub("name");
        Rfa rfa= new Rfa(ms,"nadav","052","nadav@gmail.com","nadavS", "nadav123", MainSystem.birthDateFormat.parse("01-02-1990"));
        rfa.getTeamRequests().add(team);

        try {
            rfa.answerRequest(team,true);
        } catch (Exception e) {
            fail();
        }
        Assert.assertTrue(rfa.getTeamRequests().size()==0);

        try {
            rfa.answerRequest(team,true);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("team not in request list",e.getMessage());
        }
    }
}