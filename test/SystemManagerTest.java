import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemManagerTest {

    @Test
    public void removeUser(){
        MainSystem system =MainSystem.getInstance();
        //SystemManager sm= new SystemManager(system,"d","df","df","df","df");

    }

    @Test
    public  void addNewSystemManager() throws Exception {
        MainSystem system=MainSystem.getInstance();
        Fan f1=new Fan(system,"eden","ee","e","e","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan f2=new Fan(system,"eden","ee","e","es","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan f3=new Fan(system,"eden","ee","e","esds","E",MainSystem.birthDateFormat.parse("02-11-1996"));

        SystemManager sy=new SystemManager(f1,system);
        Complaint c=new Complaint(f3,system);
        sy.addComplaint(c);

        SystemManager newSystemManager=sy.addNewSystemManager(f2);

        /**add to system*/
        Assert.assertTrue(system.getUsers().contains(newSystemManager));
        /**check if f1 and f2 removed from system*/
        Assert.assertTrue(!system.getUsers().contains(f1));
        Assert.assertTrue(!system.getUsers().contains(f2));
        /**complaint pass*/
//        Assert.assertTrue(newSystemManager.getComplaints().contains(c));

        /**null check*/
        try{
            sy.addNewSystemManager(null);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("user is null",ex.getMessage());
        }

    }

    @Test
    public void replaceTeamOwnerFounder() throws Exception {
        MainSystem system=MainSystem.getInstance();

        Fan f2=new Fan(system,"eden","ee","e","es","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan f3=new Fan(system,"eden","ee","e","es","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        SystemManager sm=new SystemManager(f3,system);

        TeamRole tr=new TeamRole(f2);
        TeamRole newFounder=new TeamRole(f3);
        tr.becomeTeamOwner();
        Team t = new Team();
        t.setFounder(tr.getTeamOwner());
        t.getTeamOwners().add(tr.getTeamOwner());
        tr.getTeamOwner().addNewTeam(t);

        /**get a user witch is not a TeamOwner**/
        try{
            sm.replaceTeamOwnerFounder(newFounder.getTeamOwner(),tr.getTeamOwner(),t);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("null input",ex.getMessage());
        }
        newFounder.becomeTeamOwner();
        sm.replaceTeamOwnerFounder(newFounder.getTeamOwner(),tr.getTeamOwner(),t);

        /**check if founder changes **/
        Assert.assertTrue(t.getFounder()==newFounder.getTeamOwner());
        /**check if new founder exist in team owners **/
        Assert.assertTrue(t.getTeamOwners().contains(newFounder.getTeamOwner()));

        /**check if the new founder hold the team*/
        Assert.assertTrue(newFounder.getTeamOwner().getTeams().contains(t));
        /**check if previous founder hold team***/
        Assert.assertTrue(tr.getTeamOwner().getTeams().contains(t));
        /**check if prec owner exist in team owners **/
        Assert.assertTrue(t.getTeamOwners().contains(tr.getTeamOwner()));


    }
}