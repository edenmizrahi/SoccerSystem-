
import Domain.Enums.*;
import jdk.internal.org.objectweb.asm.commons.RemappingFieldAdapter;
import Domain.*;
import Stubs.*;
import Domain.Users.*;
import Domain.LeagueManagment.*;
import Domain.Notifications.*;
import Domain.Events.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class SystemManagerTest {

    MainSystem sysetm = MainSystem.getInstance();
    SystemManager sm=new SystemManager(sysetm,"avi","0545233681","a@gmail.com","avi123","1234567",MainSystem.birthDateFormat.parse("01-02-1990"));
    TeamRoleStub_A teamRoleStub=new TeamRoleStub_A(sysetm,"stabi","0523893418","atab@gmaol.com","stab","1234556",MainSystem.birthDateFormat.parse("01-02-1990"));
    SystemManagerStub_A smStub=new SystemManagerStub_A(sysetm,"shalom","0545233682","a@gmail.com","avi123","1234567",MainSystem.birthDateFormat.parse("02-02-1990"));
    TeamOwnerStub_A teamOwnerStub = new TeamOwnerStub_A(teamRoleStub);
    Team t = new Team();

    public SystemManagerTest() throws ParseException { }


    @Test
    /**avital**/
    public void removeUser() throws ParseException{
        /**want to dell use that is RFA**/

        /**have 1 rfa in the system-cant delete user**/
        MainSystem system =MainSystem.getInstance();

        Fan f1=new Fan(sysetm,"eden","ee","e","e","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan f2=new Fan(sysetm,"avital","ee","e","a","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa rfa1=new Rfa(f1,system);

        try{
            sm.removeUser(rfa1);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("There is only one RFA left, you cannot delete it ",ex.getMessage());
        }

        Rfa rfa2=new Rfa(f2,system);
        /** dell RFA**/
        try {
            sm.removeUser(rfa1);
            Assert.assertFalse(system.getUsers().contains(rfa1));
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }

        /**userToDelete instanceof SystemManager**/

        /**want to dell itself **/
        try{
            sm.removeUser(sm);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("You Cannot Delete Yourself!!!",ex.getMessage());
        }

        /**must have at least one system Manager at system**/
        /*try{
            sm.removeUser(sm);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("You Cannot Delete Yourself!!!",ex.getMessage());
        }
        */
        Fan f3=new Fan(sysetm,"or","ee","e","o","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        SystemManager systemManager1=new SystemManager(f3,system);
        /** dell systemManger**/
        try {
            sm.removeUser(systemManager1);
            Assert.assertFalse(system.getUsers().contains(systemManager1));
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /** delete referee**/
        Fan f4=new Fan(sysetm,"adi","ee","e","ad","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Referee ref1Pass=new Referee(f4,system);
        Fan f5=new Fan(sysetm,"yardi","ee","e","y","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Referee ref2NotPass=new Referee(f5,system);
        Team t1 = new Team();
        Team t2 = new Team();
        try {
            Match m1Pass = new Match(0, 0, t1, t2, new Field("f1"), new HashSet<Event>(), new HashSet<Referee>()
                    , ref1Pass, "17-04-2020 20:00:00");
            Match m2NotPass = new Match(0, 0, t1, t2, new Field("f1"), new HashSet<Event>(), new HashSet<Referee>()
                    , ref2NotPass, "17-06-2020 20:00:00");
            /** cannot remove referee , he has matches in this season **/
            sm.removeUser(ref2NotPass);
            fail("expected exception was not occurred");
        }catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("You cannot remove referee , he has matches in this season",ex.getMessage());
        }
        try{
            sm.removeUser(ref1Pass);
            Assert.assertFalse(system.getUsers().contains(ref1Pass));
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**delete TeamRole**/
        /**coach**/
        TeamRole coachConnect= new TeamRole(system,"michael","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
        coachConnect.becomeCoach();
        coachConnect.getCoach().setCoachTeam(t1);
        TeamRole coachNotConnect= new TeamRole(system,"michael","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
        coachNotConnect.becomeCoach();
        /**cant dell coach connect to team **/
        try{
            sm.removeUser(coachConnect);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("you have to subscribe another Domain.Users.Coach to "+(coachConnect.getCoach()).getCoachTeam().getName() +" Domain.LeagueManagment.Team first",ex.getMessage());
        }
        /**dell coach not connect to team **/
        try {
            sm.removeUser(coachNotConnect);
            Assert.assertFalse(system.getUsers().contains(coachNotConnect));
        } catch (Exception e) {
            fail("test fail");
            e.printStackTrace();
        }

        /**player belong to any Team which hasn't more than 11 player throws Exception**/
        TeamRole p1= new TeamRole(system,"mimi","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
        p1.becomePlayer();
        p1.getPlayer().setPlayerTeam(t);
        try{
            sm.removeUser(p1);
            Assert.fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("You Cannot Delete Domain.Users.Player From " + p1.getPlayer().getTeam().getName() + " Domain.LeagueManagment.Team ,any team have to be at least 11 Players!",ex.getMessage());
        }
        /**dell player belong to any Team which has more than 11 player s*/
        //team with 11 players
        Team fullTeam=new Team();
        HashSet<Player> players= new HashSet<>();
        //TeamRole coach= new TeamRole(system,"michael","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
        int counter=0;
        while(counter<11){
            TeamRole player= new TeamRole(system,"player", "1234567890","email@gmail.com","player"+counter,"player"+counter,MainSystem.birthDateFormat.parse("09-12-1995"));
            player.becomePlayer();
            player.getPlayer().setPlayerTeam(fullTeam);
            players.add(player.getPlayer());
            counter++;
        }
        p1.getPlayer().setPlayerTeam(fullTeam);
        players.add(p1.getPlayer());
        fullTeam.setPlayers(players);
        try {
            sm.removeUser(p1);
            Assert.assertFalse(system.getUsers().contains(p1));
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
/**delete teamOwner**/
        /**if is TeamOwner-> if he is a founder of any Team from Team list which he hold**/
        TeamRole teamOwner1= new TeamRole(system,"timi","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
        teamOwner1.becomeTeamOwner();
        teamOwner1.getTeamOwner().addNewTeam(t);//!!!!!!!!!!!!!!!!!!!!!!!! set becauese of bad pull
        t.setFounder(teamOwner1.getTeamOwner());
        try{
            sm.removeUser(teamOwner1);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals(teamOwner1.getName() + " is founder of: "+t.getName() +"please replace the team's fonder",ex.getMessage());
        }

        /**delete teamOwner and all his sub -not founder of teams**/
        //down :)

        /**dell team manager and all subs**/
        Fan f6=new Fan(sysetm,"f6","ee","e","f6","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole founder=new TeamRole(f6);
        founder.becomeTeamOwner();
        Fan f7=new Fan(sysetm,"f7","ee","e","f7","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        //TeamRole teamRoleTeamMange = new TeamRole(f7);
        //TeamManager teamMange=null;
        //TeamOwner subTeamOwner=null;
        TeamRole subTeamOwner=null;
        TeamRole teamRoleTeamMange=null;
        try {
            founder.getTeamOwner().addNewTeam(fullTeam);
            HashSet<TeamManagerPermissions> per=new HashSet<>();
            per.add(TeamManagerPermissions.addRemoveEditTeamOwner);
            teamRoleTeamMange = founder.getTeamOwner().subscribeTeamManager(f7, fullTeam, per);


            Fan f8=new Fan(sysetm,"f8","ee","e","f8","E",MainSystem.birthDateFormat.parse("02-11-1996"));
            subTeamOwner=teamRoleTeamMange.getTeamManager().subscribeTeamOwner(f8,fullTeam);

        } catch (Exception ex) {
            //Assert.fail("test fail");
            ex.printStackTrace();
        }
        /** test if teamManage dell from system and subTeamOwner- dell from team  **/
        try{
            sm.removeUser(teamRoleTeamMange);
            Assert.assertFalse(system.getUsers().contains(teamRoleTeamMange));
            Assert.assertNull(fullTeam.getTeamManager());
            Assert.assertFalse(fullTeam.getTeamOwners().contains(subTeamOwner));

        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**delete teamOwner and all his sub -not founder of teams**/
        Team closeTeam=new Team();
        TeamRole co= new TeamRole(system,"co","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
        co.becomeCoach();
        co.getCoach().setCoachTeam(closeTeam);
        closeTeam.setCoach(co.getCoach());
        Field field=new Field("f1");
        closeTeam.setField(field);
        founder.getTeamOwner().addNewTeam(closeTeam);
        Fan f12=new Fan(sysetm,"f12","ee","e","f12","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Rfa rfa=new Rfa(f12,system);
        TeamRole subTO=null;
        TeamRole subSubTeamManager=null;
        TeamRole subSubTeamOwner=null;
        try{
            Fan f9=new Fan(sysetm,"f9","ee","e","f9","E",MainSystem.birthDateFormat.parse("02-11-1996"));
            subTO = founder.getTeamOwner().subscribeTeamOwner(f7, fullTeam);
            subTO = founder.getTeamOwner().subscribeTeamOwner(f7, closeTeam);
            founder.getTeamOwner().deleteTeam(closeTeam);
            Fan f10=new Fan(sysetm,"f10","ee","e","f10","E",MainSystem.birthDateFormat.parse("02-11-1996"));
            Fan f11=new Fan(sysetm,"f11","ee","e","f11","E",MainSystem.birthDateFormat.parse("02-11-1996"));
            HashSet<TeamManagerPermissions> per=new HashSet<>();
            per.add(TeamManagerPermissions.addRemoveEditTeamOwner);
            //subSubTeamManager = subTO.getTeamOwner().subscribeTeamManager(f10, fullTeam, per);

            subSubTeamOwner=subTO.getTeamOwner().subscribeTeamOwner(f11,fullTeam);
            subTO.getTeamOwner().requestNewTeam("lililo_nameOfTeam");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try{
            sm.removeUser(subTO);
            Assert.assertFalse(system.getUsers().contains(subTO));
            Assert.assertFalse(fullTeam.getTeamOwners().contains(subTO));
            Assert.assertFalse(closeTeam.getTeamOwners().contains(subTO));
            Assert.assertNull(fullTeam.getTeamManager()); //dell subs
            Assert.assertFalse(fullTeam.getTeamOwners().contains(subSubTeamOwner)); //dell subs
            Assert.assertFalse(Rfa.teamRequests.contains("lililo_nameOfTeam"));
            Assert.assertFalse(system.getActiveTeams().contains("lililo_nameOfTeam"));

        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**remove fan from system , delete his all complaints-not relevant anyMore.**/

        try{
            Fan fumToDell=new Fan(sysetm,"fumToDell","ee","e","fumToDell","E",MainSystem.birthDateFormat.parse("02-11-1996"));
            Complaint c=new Complaint(fumToDell,system);
            fumToDell.addNewComplaint("aifffff");

            //test
            sm.removeUser(fumToDell);
            Assert.assertFalse(system.getUsers().contains(fumToDell));
            //Assert.assertFalse(SystemManager.complaints.contains(c)); //???
            //Assert.assertFalse(closeTeam.getTeamOwners().contains(subTO));

        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }





        /**need to delete the user**/
        //Domain.Users.SystemManager sm= new Domain.Users.SystemManager(system,"d","df","df","df","df");

        //system.removeUser(userToDelete); - check if remove from system
    }

    @Test
    public void removeTeamFromSystem()throws ParseException {


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
        HashSet<Complaint> complaints=sy.getComplaints();
        Assert.assertTrue(newSystemManager.getComplaints().contains(c));
        Assert.assertTrue(sy.getComplaints().contains(c));

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
    /**eden+avital**/
    @Test
    public void replaceTeamOwnerFounder() throws Exception {
//TeamOwner toAdd, TeamOwner toDelete, Team team
        /** check if teamOwner toAdd null**/
        try {
            sm.replaceTeamOwnerFounder(null, teamOwnerStub,t);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("null input",e.getMessage());
        }

        /** check if teamOwner toDelete is null**/
        try {
            sm.replaceTeamOwnerFounder(teamOwnerStub,null,t);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("null input",e.getMessage());
        }
        //MainSystem system=MainSystem.getInstance();

        Fan f2=new Fan(sysetm,"eden","ee","e","es","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan f3=new Fan(sysetm,"eden","ee","e","es","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        Fan f4=new Fan(sysetm,"lili","ee","e","lili","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        SystemManager sm=new SystemManager(f3,sysetm);

        TeamRole oldFounder=new TeamRole(f2);
        TeamRole newFounder=new TeamRole(f3);
        TeamRole teamOwner=new TeamRole(f4);
        teamOwner.becomeTeamOwner();
        oldFounder.becomeTeamOwner();
        //Team t = new Team();
        t.setFounder(oldFounder.getTeamOwner());
        t.getTeamOwners().add(oldFounder.getTeamOwner());
        oldFounder.getTeamOwner().addNewTeam(t);// //!!!!!!!!!!!!!!!!!!!!!!!! set becauese of bad pull - not me

        Fan f5=new Fan(sysetm,"f5","ee","e","f5","E",MainSystem.birthDateFormat.parse("02-11-1996"));
        TeamRole tr=new TeamRole(f5); // ? ?? ? ?
        /**get a user(old teamOwner) witch is not a TeamOwner**/
        tr.becomeTeamOwner();
        Team t = new Team();
        t.setFounder(tr.getTeamOwner());
        t.getTeamOwners().add(tr.getTeamOwner());
        tr.getTeamOwner().addNewTeam(t); // //!!!!!!!!!!!!!!!!!!!!!!!! set becauese of bad pull - not me

        /**get a user witch is not a Domain.Users.TeamOwner**/
        try{
            sm.replaceTeamOwnerFounder(newFounder.getTeamOwner(),teamOwner.getTeamOwner(),t);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("null input",ex.getMessage());
        }

        /**toAdd is already team owner of team**/
        try{
            sm.replaceTeamOwnerFounder(oldFounder.getTeamOwner(),oldFounder.getTeamOwner(),t);
            fail("expected exception was not occurred");
        }
        catch (Exception ex){
            Assert.assertEquals(Exception.class,ex.getClass());
            Assert.assertEquals("fail!The team owner you want to add already exist",ex.getMessage());
        }

        newFounder.becomeTeamOwner();
        sm.replaceTeamOwnerFounder(newFounder.getTeamOwner(),oldFounder.getTeamOwner(),t);

        /**check if founder changes **/
        Assert.assertTrue(t.getFounder()==newFounder.getTeamOwner());
        /**check if new founder exist in team owners **/
        Assert.assertTrue(t.getTeamOwners().contains(newFounder.getTeamOwner()));

        /**check if the new founder hold the team*/
        Assert.assertTrue(newFounder.getTeamOwner().getTeams().contains(t));
        /**check if previous founder hold team***/
        Assert.assertTrue(oldFounder.getTeamOwner().getTeams().contains(t));
        /**check if prec owner exist in team owners **/
        Assert.assertTrue(t.getTeamOwners().contains(oldFounder.getTeamOwner()));


    }

    @Test
    public void switchTeamOwnerFounder() throws ParseException {

        try {// teamOwner we ant to dell is null
            //sm.switchTeamOwnerFounder(teamRoleStub, );
            fail();
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("team role is not a team owner",e.getMessage());
        }
    }



}

