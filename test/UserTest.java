import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.Assert.*;

public class UserTest {
    MainSystem ms = new MainSystem();
    User user = new User(ms);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
    Date date;

    {
        try {
            date = sdf.parse("09-12-1995");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    Player player1=new Player(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123",date);
    Player player2=new Player(ms, "Or Hamalcha", "0542150912","oralf@gmail.com", "OrHamalcha", "Or1234",date);
    PrivatePage pp1=new PrivatePage();
    PrivatePage pp2= new PrivatePage();
    PrivatePage pp3= new PrivatePage();
    League league1 = new League("LIGAT HAALUFUT", ms);
    League league2 = new League("LIGAT HAAL",ms);
    Season season2020 = new Season(ms,null,2020);
    Season season2019 = new Season(ms,null,2019);
    Team hapoelBeerSheva= new Team("Hapoel Beer Sheva");
    Team hapoelKfarSaba= new Team("Hapoel Kfar Saba");
    Team macabiHaifa= new Team("Macabi Haifa");
    Team macabiTelAviv= new Team("Macabi Tel Aviv");
    Team beitarYerushalaim= new Team("Beitar Yerushalaim");
    Team hapoelRaanana= new Team ("Hapoel Raanana");

    @Before
    public void init() {
        ms.setCurrSeason(season2020);
        HashSet<Team> teamsForLeague1= new HashSet<Team>();
        Collections.addAll(teamsForLeague1,hapoelBeerSheva,hapoelKfarSaba,macabiHaifa);
        season2020.addLeagueWithTeams(league1,teamsForLeague1);
        hapoelBeerSheva.addPlayer(player1);
        beitarYerushalaim.addPlayer(player2);
        player1.setPrivatePage(pp1);
        pp1.setPageOwner(player1);
        player2.setPrivatePage(pp2);
        pp2.setPageOwner(player2);
        hapoelKfarSaba.setPrivatePage(pp3);
        pp3.setPageOwner(hapoelKfarSaba);

        HashSet<Team> teamsForLeague2= new HashSet<Team>();
        Collections.addAll(teamsForLeague2,beitarYerushalaim,macabiTelAviv,hapoelRaanana);
        season2019.addLeagueWithTeams(league2,teamsForLeague1);

    }

    /**Or**/
    @Test
    public void searchByName() {
        player1.setPrivatePage(pp1);
        pp1.setPageOwner(player1);
        player2.setPrivatePage(pp2);
        pp2.setPageOwner(player2);
        try {
            user.searchByName(null);
            fail("expected exception was not occurred.");
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("name null",e.getMessage());

        }
        try {
            user.searchByName("");
            fail("expected exception was not occurred.");
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("name empty",e.getMessage());
        }
        try {
            LinkedHashSet<PrivatePage> ans=new LinkedHashSet<PrivatePage>();
            Assert.assertEquals(ans,user.searchByName("tami"));
        } catch (Exception e) {
            fail("expected exception was not occurred.");
        }
        try {
            LinkedHashSet<PrivatePage> ans2=new LinkedHashSet<PrivatePage>();
            ans2.add(pp2);
            Assert.assertEquals(ans2,user.searchByName("Or"));
        } catch (Exception e) {
            fail("expected exception was not occurred.");
        }
    }

    /**Or**/
    @Test
    public void searchByKeyWord() throws Exception {
        player1.setPrivatePage(pp1);
        pp1.setPageOwner(player1);
        pp1.addRecords("record1");
        pp1.addRecords("record2");
        pp1.addRecords("record3");
        player2.setPrivatePage(pp2);
        pp2.setPageOwner(player2);
        try {
            user.searchByKeyWord(null);
            fail("expected exception was not occurred.");
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("keyWord null",e.getMessage());

        }
        try {
            user.searchByKeyWord("");
            fail("expected exception was not occurred.");
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("keyWord empty",e.getMessage());
        }
        try {
            LinkedHashSet<PrivatePage> ans=new LinkedHashSet<PrivatePage>();
            Assert.assertEquals(ans,user.searchByKeyWord("tami"));
        } catch (Exception e) {
            fail("expected exception was not occurred.");
        }
        try {
            LinkedHashSet<PrivatePage> ans2=new LinkedHashSet<PrivatePage>();
            ans2.add(pp1);
            Assert.assertEquals(ans2,user.searchByKeyWord("record2"));
        } catch (Exception e) {
            fail("expected exception was not occurred.");
        }
    }

    /**Or**/

    @Test
    public void searchByLeague() throws Exception {

        try {
            user.searchByLeague(null);
            fail("expected exception was not occurred.");
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("leagueName null",e.getMessage());

        }
        try {
            user.searchByLeague("");
            fail("expected exception was not occurred.");
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("leagueName empty",e.getMessage());
        }
        try {
            LinkedHashSet<PrivatePage> ans=new LinkedHashSet<PrivatePage>();
            Assert.assertEquals(ans,user.searchByLeague("tami"));
        } catch (Exception e) {
            fail("expected exception was not occurred.");
        }
        try {
            LinkedHashSet<PrivatePage> ans2=new LinkedHashSet<PrivatePage>();
            ans2.add(pp1);
            ans2.add(pp3);
            Assert.assertEquals(ans2,user.searchByLeague("LIGAT HAALUFUT"));
        } catch (Exception e) {
            fail("expected exception was not occurred.");
        }
    }
}