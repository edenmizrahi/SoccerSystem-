import Domain.*;
import Domain.Users.*;
import Stubs.*;
import Domain.Enums.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;


public class TeamRoleTest {

    MainSystem system = MainSystem.getInstance();
    Fan f1=new Fan(system,"f1","ee","e","f1","E",MainSystem.birthDateFormat.parse("02-11-1996"));
    TeamRole teamRole=new TeamRole(f1);
    TeamStub t1 = new TeamStub("team1");
    Fan f2=new Fan(system,"f2","ee","e","f1","E",MainSystem.birthDateFormat.parse("02-11-1996"));
    TeamRole fullTR=new TeamRole(f2);
    Boolean ansReturnFromFunc=null;

    public TeamRoleTest() throws ParseException {
    }

    @Test
    public void becomePlayer() {
        /**player null **/
        try{
            ansReturnFromFunc= teamRole.becomePlayer();
            Assert.assertEquals(ansReturnFromFunc,true);
            Assert.assertTrue(teamRole.isPlayer());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**player not null - already player**/
        try {
                ansReturnFromFunc= teamRole.becomePlayer();
                Assert.assertEquals(ansReturnFromFunc,false);
                Assert.assertTrue(teamRole.isPlayer());
        }catch (Exception e){
                Assert.fail("test fail");
                e.printStackTrace();
        }

    }


    @Test
    //avital
    public void becomeCoach() {
        /**coach is null **/
        try{
            ansReturnFromFunc= teamRole.becomeCoach();
            Assert.assertEquals(ansReturnFromFunc,true);
            Assert.assertTrue(teamRole.isCoach());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**coach not null - already coach**/
        try {
            ansReturnFromFunc= teamRole.becomeCoach();
            Assert.assertEquals(ansReturnFromFunc,false);
            Assert.assertTrue(teamRole.isCoach());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
    }

    @Test
    public void becomeTeamManager() {
        HashSet<TeamManagerPermissions> per=new HashSet<>();
        per.add(TeamManagerPermissions.addRemoveEditTeamOwner);
        /**teamManger is null **/
        try{
            ansReturnFromFunc= teamRole.becomeTeamManager(t1,per);
            Assert.assertEquals(ansReturnFromFunc,true);
            Assert.assertTrue(teamRole.isTeamManager());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**teamManger not null - already teamManger**/
        try {
            ansReturnFromFunc= teamRole.becomeTeamManager(t1,per);
            Assert.assertEquals(ansReturnFromFunc,false);
            Assert.assertTrue(teamRole.isTeamManager());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
    }


    @Test
    public void becomeTeamOwner() {
        /**TeamOwner is null **/
        try{
            ansReturnFromFunc= teamRole.becomeTeamOwner();
            Assert.assertEquals(ansReturnFromFunc,true);
            Assert.assertTrue(teamRole.isTeamOwner());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**TeamOwner not null - already TeamOwner**/
        try {
            ansReturnFromFunc= teamRole.becomeTeamOwner();
            Assert.assertEquals(ansReturnFromFunc,false);
            Assert.assertTrue(teamRole.isTeamOwner());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
    }

    @Test
    public void deletePlayer() {
        /**player is not null **/
        try{
            fullTR.becomePlayer();
            ansReturnFromFunc= fullTR.deletePlayer();
            Assert.assertEquals(ansReturnFromFunc,true);
            Assert.assertFalse(fullTR.isPlayer());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**player already null **/
        try{
            ansReturnFromFunc= fullTR.deletePlayer();
            Assert.assertEquals(ansReturnFromFunc,false);
            Assert.assertFalse(fullTR.isPlayer());
        }catch (Exception e){
            Assert.fail("test fail");
            e.printStackTrace();
        }

    }
}
/*
        try{
            sm.removeUser(sm);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("You Cannot Delete Yourself!!!",ex.getMessage());
            */

