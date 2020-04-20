import Domain.*;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.Team;
import Domain.Users.Player;
import Domain.Users.TeamRole;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TeamTest {
    /**or**/
    MainSystem ms= MainSystem.getInstance();
    TeamRole teamOwner = new TeamRole(ms,"michael","0522150912","teamO@gmail.com","owner123","owner123",MainSystem.birthDateFormat.parse("09-12-1995"));
    TeamRole teamOwner1 = new TeamRole(ms,"r","0522150912","owner1O@gmail.com","r1234","r1234",MainSystem.birthDateFormat.parse("09-12-1995"));
    Team t= new Team();
    Team t1= new Team();
    HashSet<Player> players= new HashSet<>();
    Field f= new Field("field");
    Field f1= new Field("field1");
    TeamRole coach= new TeamRole(ms,"michael","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
    TeamRole coach1= new TeamRole(ms,"michael","0522150912","teamO@gmail.com","coach1132","coach1132",MainSystem.birthDateFormat.parse("09-12-1995"));
    HashSet<TeamManagerPermissions> per = new HashSet<>();
    /**players in team**/
    TeamRole player= new TeamRole(ms,"michael","0522150912","teamO@gmail.com","player4432","player4432",MainSystem.birthDateFormat.parse("09-12-1995"));
    TeamRole player1= new TeamRole(ms,"michael","0522150912","teamO@gmail.com","player7732","player7732",MainSystem.birthDateFormat.parse("09-12-1995"));
    TeamRole teamRole1 = new TeamRole(ms,"yarden1","0546260171","yarden@gmail.com","yarden123", "yarden123", MainSystem.birthDateFormat.parse("08-09-1995"));
    TeamRole teamRole2 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden234", "yarden234", MainSystem.birthDateFormat.parse("09-09-1995"));
    TeamRole teamRole3 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden345", "yarden345", MainSystem.birthDateFormat.parse("10-09-1995"));
    TeamRole teamRole4 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden456", "yarden456", MainSystem.birthDateFormat.parse("11-09-1995"));
    TeamRole teamRole5 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden567", "yarden567", MainSystem.birthDateFormat.parse("12-09-1995"));
    TeamRole teamRole6 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden678", "yarden678", MainSystem.birthDateFormat.parse("13-09-1995"));
    TeamRole teamRole7 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden789", "yarden789", MainSystem.birthDateFormat.parse("14-09-1995"));
    TeamRole teamRole8 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden012", "yarden012", MainSystem.birthDateFormat.parse("15-09-1995"));
    TeamRole teamRole9 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden112", "yarden112", MainSystem.birthDateFormat.parse("15-09-1995"));
    TeamRole teamRole10 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden612", "yarden612", MainSystem.birthDateFormat.parse("15-09-1995"));


    public TeamTest() throws ParseException {
    }


    /**adi+yarden**/
    @Test
    public void addAndRemoveTeamOwnerTest(){

        /****Add TeamOwner****/
        /**null check**/
        try {
            t.addTeamOwner(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("TeamOwner is null",e.getMessage());
        }

        /**ok**/
        try {
            teamOwner.becomeTeamOwner();
            t.addTeamOwner(teamOwner.getTeamOwner());
            Assert.assertTrue(t.getTeamOwners().contains(teamOwner.getTeamOwner()));
        }
        catch (Exception e){
            Assert.fail();
        }

        /****Remove TeamOwner****/
        /**TeamOwner doesnt exist in the team**/
        teamOwner1.becomeTeamOwner();
        try {
            t.removeTeamOwner(teamOwner1.getTeamOwner());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("TeamOwner doesn't exist in this team",e.getMessage());
        }

        /**ok**/
        try{
            t.removeTeamOwner(teamOwner.getTeamOwner());
            Assert.assertFalse(t.getTeamOwners().contains(teamOwner.getTeamOwner()));
        }
        catch (Exception e){
            Assert.fail();
        }

        /**null check**/
        try {
            t.removeTeamOwner(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Domain.TeamOwner is null",e.getMessage());
        }

    }

    /**adi+yarden**/
    @Test
    public void removeTeamManagerTest(){
        /**null check**/
        try {
            t.removeTeamManager(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("TeamManager is null",e.getMessage());
        }
        /**remove from team with permission**/
        try {
            per.add(TeamManagerPermissions.addRemoveEditTeamOwner);
            teamOwner.becomeTeamManager(t, per);
            Assert.assertTrue(t.getTeamManager().equals(teamOwner.getTeamManager()));//check the subscription
            t.removeTeamManager(teamOwner.getTeamManager());
            Assert.assertNull(t.getTeamManager());//remove from team
            Assert.assertNotNull(teamOwner.getTeamManager());//not remove from teamRole
        }
        catch (Exception e){
            Assert.fail();
        }

        /**TeamManager doesnt exist**/
        try {
            t1.removeTeamManager(teamOwner.getTeamManager());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("This TeamManager doesn't exist in this team",e.getMessage());
        }
    }

    /**adi+yarden**/
    @Test
    public void addAndRemoveCoachTest(){

        /****Add Coach****/
        /**null check**/
        try {
            t.addCoach(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Coach is null",e.getMessage());
        }

        /**ok**/
        coach.becomeCoach();
        try {
            t.addCoach(coach.getCoach());
            Assert.assertEquals(t.getCoach(),coach.getCoach());
        }
        catch (Exception e){
            Assert.fail();
        }

        /**There is already coach to team**/
        coach1.becomeCoach();
        try {
            t.addCoach(coach1.getCoach());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("There is coach to this team",e.getMessage());
        }

        /****Remove Coach****/
        /**null check**/
        try {
            t.removeCoach(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Coach is null",e.getMessage());
        }

        /**This coach isn't in the team**/
        try {
            t.removeCoach(coach1.getCoach());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("This Coach doesn't exist in this team",e.getMessage());
        }

        /**ok**/
        try {
            t.removeCoach(coach.getCoach());
            Assert.assertFalse(t.getCoach().equals(coach.getCoach()));
        }
        catch (Exception e) {
            Assert.fail();
        }

        /**There isn't coach to remove in this team**/
        try {
            t.removeCoach(coach1.getCoach());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("There isn't coach to remove in this team",e.getMessage());
        }
    }

    /**adi+yarden**/
    @Test
    public void addAndRemovePlayerTest(){
        /****Add player****/
        /**null check**/
        try {
            t.addPlayer(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Player is null",e.getMessage());
        }

        /**ok**/
        player.becomePlayer();
        try {
            t.addPlayer(player.getPlayer());
            Assert.assertTrue(t.getPlayers().contains(player.getPlayer()));
        }
        catch (Exception e){
            Assert.fail();
        }

        /**player is already in another team**/
        player1.becomePlayer();
//        try {
//            t1.addPlayer(player1.getPlayer());
//            Assert.assertTrue(t1.getPlayers().contains(player1.getPlayer()));
//        }
//       catch (Exception e) {
//            Assert.fail();
//        }
//
//        try {
//            t.addPlayer(player1.getPlayer());
//            fail();
//        }
//        catch (Exception e){
//            assertEquals(Exception.class, e.getClass());
//            assertEquals(" This player is already in another team",e.getMessage());
//        }

        /****Remove player****/
        /**null check**/
        try {
            t.removePlayer(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Player is null",e.getMessage());
        }

        /**player doesnt exist in team**/
        try {
            t.addPlayer(player1.getPlayer());
            t1.removePlayer(player.getPlayer());
            Assert.fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("This Player doesn't exist in this team",e.getMessage());
        }

        try {
            t.removePlayer(player.getPlayer());
            Assert.fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("The team has only 11 or less players",e.getMessage());
        }

        Assert.assertTrue(t.getPlayers().contains(player.getPlayer()));

        /**Ok**/
        teamRole1.becomePlayer();
        teamRole2.becomePlayer();
        teamRole3.becomePlayer();
        teamRole4.becomePlayer();
        teamRole5.becomePlayer();
        teamRole6.becomePlayer();
        teamRole7.becomePlayer();
        teamRole8.becomePlayer();
        teamRole9.becomePlayer();
        teamRole10.becomePlayer();

        try {
            t.addPlayer(teamRole1.getPlayer());
            t.addPlayer(teamRole2.getPlayer());
            t.addPlayer(teamRole3.getPlayer());
            t.addPlayer(teamRole4.getPlayer());
            t.addPlayer(teamRole5.getPlayer());
            t.addPlayer(teamRole6.getPlayer());
            t.addPlayer(teamRole7.getPlayer());
            t.addPlayer(teamRole8.getPlayer());
            t.addPlayer(teamRole9.getPlayer());
            t.addPlayer(teamRole10.getPlayer());

            t.removePlayer(teamRole8.getPlayer());
        }
        catch (Exception e){
            Assert.fail();
        }

    }

    /**adi+yarden**/
    @Test
    public void removeFieldTest() {

        /**null check**/
        try {
            t.removeField(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Field is null",e.getMessage());
        }

        /**field doesnt exist in team**/
        try {
            t.removeField(f1);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("There isn't field to remove",e.getMessage());
        }

        /**field doesn't exist in team**/
        t.setField(f);
        try {
            t.removeField(f1);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("This field doesn't exist in this team",e.getMessage());
        }

        /**ok**/
        try {
            t.removeField(f);
            Assert.assertNull(t.getField());
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    /**yarden**/
    @Test
    public void addAndRemoveRecordToOrFromPageTest() {

        String record = "what a beautiful day";
        /**private page of team is null**/
        try {
            t.addRecordToPage(record);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("The team hasn't private page",e.getMessage());
        }

        /**private page of team is null**/
        try {
            t.removeRecordFromPage(record);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("The team hasn't private page",e.getMessage());
        }

        /**ok**/
        t.setPrivatePage(new PrivatePage());
        try {
            t.addRecordToPage(record);
            Assert.assertTrue(t.getPrivatePage().getRecords().contains(record));
            t.addRecordToPage(record);
            Assert.assertTrue(t.getPrivatePage().getRecords().size()==2);
        }
        catch (Exception e){
            Assert.fail();
        }


        try {
            t.removeRecordFromPage(record);
            Assert.assertTrue(t.getPrivatePage().getRecords().size()==1);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void createPrivatePageTest() {

    }

    @Test
    public void addLeagueAndSeasonTest(){

    }

    @Test
    public void addIncomeTest(){

    }

    @Test
    public void addExpenseTest(){

    }

    @Test
    public void reopenTeamTest(){

    }

    @Test
    public void addMatchToHomeMatchesTest(){

    }

    @Test
    public void addMatchToAwayMatchesTest(){

    }


    /**or+yarden**/
    @Test
    public void becomeActive() throws ParseException {
        //init
        int counter=0;
        while(counter<11){
            TeamRole player= new TeamRole(ms,"player", "1234567890","email@gmail.com","player"+counter,"player"+counter,MainSystem.birthDateFormat.parse("09-12-1995"));
            player.becomePlayer();
            players.add(player.getPlayer());
            counter++;
        }
        coach.becomeCoach();


        Assert.assertFalse(t.isActive());
        try {
            t.becomeActive(players,coach.getCoach(),f);
        } catch (Exception e) {
            fail();
        }
        Assert.assertTrue(t.isActive());
        Assert.assertTrue(t.getPlayers().size()!=0);
    }

    /**or+yarden**/
    @Test
    public void deleteTeamByTeamOwner() throws ParseException {
        //init
        int counter=0;
        while(counter<11){
            TeamRole player= new TeamRole(ms,"player", "1234567890","email@gmail.com","player"+counter,"player"+counter,MainSystem.birthDateFormat.parse("09-12-1995"));
            player.becomePlayer();
            players.add(player.getPlayer());
            counter++;
        }
        coach.becomeCoach();
        try {
            t.becomeActive(players,coach.getCoach(),f);
        } catch (Exception e) {
            fail();
        }

        t.deleteTeamByTeamOwner();
        Assert.assertFalse(t.isActive());
        Assert.assertTrue(t.getCoach().getCoachTeam()==null);
    }


}